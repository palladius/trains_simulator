/*
	A train is a Thread which goes to stations.
	
	Every train has a:
	- speed (modelled through slowness)
	- position (might be in a station[0..7], might be a track[0..7]). I could model position thru an object but id rather keep it simple

The status of the train is (provided the system has NumberOFStations):
 - arriving at a station
 - departing
 - loading
 - unloading, etc

*/

import java.util.ArrayList;

public class Train extends AVerboseThread {
    int slowness ; // models speed = 1/rail_delay (time in seconds to reach next station)
    int position ;    // a number representing the position 0..2N
    //private Position position2 ;    // a number representing the position
	ArrayList<Cargo> cargos;    // array of Cargos for this train, can grow indefinitely
    int occurrence;             // cardinal number for better observation (name)
    final int cargoCapacity;
    TrainStatus mystatus;
    
    public enum TrainStatus { STATION_START, STATION_UNLOAD_CARGO, STATION_LOAD_CARGO, STATION_END };

    public Train(int n, int railway_period, int initial_position, int cargo_capacity) {
        this.slowness      = railway_period;
        this.position      = 2*initial_position;
        //this.position2     = new Position(2*initial_position);
        this.cargos        = new ArrayList<Cargo>();
        this.occurrence    = n;
        this.cargoCapacity = cargo_capacity;
        this.mystatus      = TrainStatus.STATION_START; // initial status
    }
    
    public void run() {
    	System.out.println("Train started: "+ toString() );
		setName("Train."+occurrence+"");
    	log("started: "+ toString() );
    	
    	// here we simulate the train status
    	while (true) {
    		// a Train always starts in a station
    		set_status(TrainStatus.STATION_START);
    		set_status(TrainStatus.STATION_UNLOAD_CARGO);  // takes small amount of time
    		set_status(TrainStatus.STATION_LOAD_CARGO);    // takes a bit of time
    		set_status(TrainStatus.STATION_END); // registers for next track
    	}
    }
    
    // on creation, register that you are in station #station_number
    public void registerToStation(int station_number) {
    	Station myStation = Country.getInstance().getStation(station_number);
    	dlog("1a. Registering '"+this+"' on Station: "+myStation);
    	myStation.addTrain(this);
    	dlog("1b. Registered  '"+this+"' on Station: "+myStation);
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

    	dlog("4. Registering '"+this+"' on Railway: " + myRailway);
    	
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
    		vlog("4b. Occupying railway for some time..");
        	Thread.sleep(slowness * 1000);    		
    	} catch (InterruptedException e) {
    		log("TrainThread interrupted when on Railway: "+myRailway);
    	}
    	
    	myRailway.removeTrain(this);
    }
    
    /** 
     * Status transitions
     * 
     * */
    
    public synchronized void set_status(TrainStatus new_status) {
    	//dlog("Status Change for "+this+": "+ status +" => "+ new_status );
    	mystatus = new_status;

    	switch(new_status) {
    		case STATION_START:
    	        registerToStation(position/2);  // should be exactly N/2 (even)
    			break ;
    		case STATION_UNLOAD_CARGO:
    			unloadCargo(); // second move
    			break;
    		case STATION_LOAD_CARGO: // third move
    			// assert(position.instanceOf(Station));
    			loadCargo();
    			break;
    		case STATION_END:
    	        increment_position(); // moving from station to next railway
    	        registerToRailway(position/2);  // should be (N-1)/2 (odd). Possibly Ill wait here
    	        increment_position();
    	        notifyAll();                    // SYNC waiting objects
    	        break;
    	}
    }

    private void increment_position() {
		position = Position.nextPos(position);
		//position2 = position2.next(); // something like this
	}

    // does action with cargo which might magically disappear...
	synchronized void unloadCargo() {
    	dlog("2. UnLoadCargo for "+this+" at position: " + position);
    	for(int k=0; k < cargos.size(); k++) {
    		Cargo c = cargos.get(k);
    		boolean matches = c.destination == position / 2 ;
    		dlog("Lets see if my cargo '"+c+"' matches this position: " + position + ": " + matches);
    		if (c.source == myStation().getIndex() ) {
    			cargos.remove(k);
    			log("Station cargo ");
    		}
    	}
	}
	
	// can only call this internally, its a shortcut to a typcast of the station from current position
	private Station myStation() {
		return (Station) APlace.getCountryPlace(position);
	}

	public synchronized void loadCargo() {
		// load cargo based on cargoCapacity
		Station myStation = myStation();
		synchronized(myStation.depotLock) {
	
	    	dlog("3a. TODO easy LoadCargo at position, lets see what cargos are available now: " + position);
			dlog("3b. Station has following cargos (all are good):\n");
			//assert_station();
			//available_cargos = myPlace().cargos;
			dlog("3c. Station '"+myStation+"' has " +myStation.getCargos().size()+ " cargos available");
			dlog("3d. '"+this+"' has " + cargos.size()+ "/"+cargoCapacity + " in use");
			int cargo_size =  cargos.size(); // if i don't put it here, it takes only half :)
			// get Cargo 
			for (int i=0 ; 
					i < myStation.getCargos().size() && // not more than available in station (dynamic)
					i < (cargoCapacity - cargo_size )   // not more I (train) can get (static)
					; i++ ) {
				//dlog("3e - "+myStation+", adding " + myStation.getCargos().get(i));
				cargos.add( 
						myStation.removeAndGetCargo() // this is guaranteed to be reentrant
				);
			}
			//dlog("3e. Station '"+myStation+"' has " +myStation.getCargos().size()+ " cargos available");
			//dlog("3f. '"+this+"' has " + cargos.size()+ "/"+cargoCapacity + " in use");
			
		} // synchronized on the station
	}

	@Override
    public String toString() {
        return "Train."+occurrence
        		+ "("
        		+ "pos: " + position
        		+ ",c: "  + cargos
        		//+ ",sl:"  + slowness 
        		+ ")";
    }
	public String toStringMini() {
		return "T"+occurrence+"(p="+position+")";
	}
}