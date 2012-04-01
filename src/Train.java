/**
 * 
 * A train is a Thread which goes to stations.
 * 
 * 
 * Every train has a:
 *	- speed (modelled through slowness)
 *	- position (might be in a station[0..7], might be a track[0..7]). I could model position thru an object but id rather keep it simple

 * The status of the train is (provided the system has NumberOFStations):
 * - arriving at a station
 * - loading
 * - unloading, etc
 * - departing
 * 
 * an interesting refactor could be to use a Position object and so forget about integers (it's 
 * quite easy to confuse positions (16) and railway/station indexes (8). Ive tried to be as
 * strict as possible with names in order not to introduce strange bugs..
 * 
*/

import java.util.ArrayList;
import java.lang.Math;


public class Train extends AVerboseThread {
    int slowness ;              // models speed = 1/rail_delay (time in seconds to reach next station)
    int position ;              // a number representing the position 0..2N
	ArrayList<Cargo> cargos;    // array of Cargos for this train, can grow indefinitely
    int occurrence;             // cardinal number for better observation (name)
    final int cargoCapacity;
    TrainStatus mystatus;
    
    public enum TrainStatus { STATION_START, STATION_UNLOAD_CARGO, STATION_LOAD_CARGO, STATION_END };

    public Train(int n, int railway_period, int initial_position, int cargo_capacity) {
        this.slowness      = railway_period;
        this.position      = 2*initial_position;
        this.cargos        = new ArrayList<Cargo>();
        this.occurrence    = n;
        this.cargoCapacity = cargo_capacity;
        this.mystatus      = TrainStatus.STATION_START; // initial status
    }
    
    public void run() {
    	System.out.println("Train started: "+ toString() );
		setName("Train."+occurrence);  // i.e. Train.0
    	log("started: "+ toString() );
    	
    	// here we simulate the train status
		// a Train always starts in a station
    	myStation().addTrain(this); // first time I need this or Im in inconsistent state
    	while (true) {
    		set_status(TrainStatus.STATION_START);
    		set_status(TrainStatus.STATION_UNLOAD_CARGO);  // takes small amount of time
    		set_status(TrainStatus.STATION_LOAD_CARGO);    // takes a bit of time
    		set_status(TrainStatus.STATION_END); // registers for next track
    	}
    }
    
    // on creation, register that you are in station #station_number
    public synchronized void  registerToStation(int station_number) {
    	Station myStation = Country.getInstance().getStation(station_number);
    	log("Train entering station: "+ myStation.toStringMini() );
    	myStation.addTrain(this);
    }
    
    /**
     * This is where a train waits in the Railway
     * 
     * @param railway_number
     */
    public synchronized void registerToRailway(int railway_number) {
    	Railway myRailway = Country.getInstance().getRailway(railway_number);
    	Station myStation = Country.getInstance().getStation(railway_number); // Apparent bug. Note that the station has the same number as the Railway

    	myStation.removeTrain(this); // deregistering Train from the station which has the same number as the RW

    	//dlog("4. Registering '"+this+"' on Railway: " + myRailway);
    	
    	// this synchronizes all trains on a "single track" railway
    	while(myRailway.isBusy()) {
    		try {
    			wait();
    		} catch (InterruptedException e) {
    			log("DEBUG seems like I've been waken up!");
    		}
    	}
 
    	myRailway.addTrain(this); // occupying the resource!
    	
    	// now that I have my resource Railway, I can go into it. 
    	// I simulate the time spent here with different speed as a sleep = slowness in seconds
    	try {
    		log("Train entering railway track: " + myRailway.toStringMini() );
        	Thread.sleep(slowness * 1000);    		
    	} catch (InterruptedException e) {
    		log("TrainThread interrupted when on Railway: "+myRailway);
    	}
    	
    	myRailway.removeTrain(this);
    }
    
    /** 
     * Status transitions (trivial in this case: 1-2-3-4-1-2-3-4-...)
     * 
     * */    
    public synchronized void set_status(TrainStatus new_status) {
    	mystatus = new_status;

    	switch(new_status) {
    		case STATION_START:
    	        registerToStation(position/2);  // should be exactly N/2 (even)
    			break ;
    		case STATION_UNLOAD_CARGO:
    			unloadCargo(); // second move
    			break;
    		case STATION_LOAD_CARGO: // third move
    			loadCargo();
    			break;
    		case STATION_END:
    	        increment_position(); // moving from station to next railway
    	        registerToRailway(position/2);  // should be (N-1)/2 (odd). Possibly Ill wait here
    	        increment_position();
    	        notifyAll();                    // SYNC waiting objects now that Im out of the Railway
    	        break;
    	}
    }

    private void increment_position() {
		position = Position.nextPos(position);
	}

    // does action with cargo which might magically disappear...
	void unloadCargo() {
		synchronized(cargos) {
	    	dlog("2. UnLoadCargo for "+this+" at position: " + position);
	    	for(int k=0; k < cargos.size(); k++) {
	    		Cargo c = cargos.get(k);
	    		if (c.destination == myStation().getIndex() ) {
	    			Cargo tmpCargo = cargos.remove(k);
	    			vlog("Cargo correctly unloaded in station "+myStation().toStringMini()+": " + tmpCargo );
	    		}
	    		try {
	        		Thread.sleep(Country.UnloadTimeMilliSecs); // simulates small time wait    			
	    		} catch (Exception e) {
	    			log("Interrupted while unloading some cargo..");
	    		}
	    	}
		}
	}
	
	// can only call this internally, its a shortcut to a typcast of the station from current position
	private Station myStation() {
		// should make sure position is an even number..
		return (Station) APlace.getCountryPlace(position);
	}

	public synchronized void loadCargo() {
		// load cargo based on cargoCapacity
		Station myStation = myStation();
		synchronized(myStation.depotLock) {
			int nCargosToGet = Math.min(
					cargoCapacity - cargos.size(),  // max my Train can get
					myStation.getCargos().size()    // not more than available in station (dynamic)
				);
			// get Cargo 
			for (int i=0 ; i < nCargosToGet ; i++) {
				cargos.add( 
						myStation.removeAndGetCargo() // this is guaranteed to be reentrant on the station side
				);
			}
    		try {
        		Thread.sleep(Country.LoadTimeMilliSecs); // simulates small time wait    			
    		} catch (Exception e) {
    			log("Interrupted while loading some cargo..");
    		}
		} // synchronized on the station
	}

    public String toStringVerbose() {
        return "Train."+occurrence
        		+ "("
        		+ "pos => " + position
        		+ ",cs => "  + cargos.size()
        		+ ")";
    }
	@Override
	public String toString() {
		return toStringMini();
	}
	public String toStringMini() {
		return "Tr"+occurrence+"(P"+position+",C"+cargos.size()+")";
	}
}