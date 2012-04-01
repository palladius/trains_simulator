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

import java.util.Date;


public class Train extends AVerboseThread {
    //private int balance;
    private int slowness ; // models speed = 1/rail_delay (time in seconds to reach next station)
    private int position ;    // a number representing the position
    private Cargo[] cargos ;
    private int occurrence;             // cardinal number for better observation
    private final int cargoCapacity;
    private Date birthDate;
    private TrainStatus status;
    
    public enum TrainStatus { STATION_START, STATION_UNLOAD_CARGO, STATION_LOAD_CARGO, STATION_END };

    public Train(int n, int railway_period, int initial_position, int cargo_capacity) {
        this.slowness = railway_period;
        this.position = initial_position;
        this.cargos = null ;
        this.occurrence = n;
        this.cargoCapacity = cargo_capacity;
        this.birthDate = new Date();
        this.status    = TrainStatus.STATION_START;
    }
    
    public void run() {
    	System.out.println("Train started: "+ toString() );
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
    
    /** 
     * Status transitions
     * 
     * 
     * */
    
    public void set_status(TrainStatus new_status) {
    	//dlog("Status Change for "+this+": "+ status +" => "+ new_status );
    	status = new_status;
    	// Manage status change todo
    	switch(new_status) {
    		case STATION_START:
    			
    			
    			break ;
    		case STATION_UNLOAD_CARGO:
    			
    			break;
    			
    		case STATION_LOAD_CARGO:
    			// assert(position.instanceOf(Station));
    			
    			break;
    		case STATION_END:
    			
    			break;
    	}
    }

    @Override
    public String toString() {
        return "Train '"+occurrence
        		+ "' (time: " + slowness 
        		+ ", position: "+position
        		+ ", cargos: "+ cargos // (cargos != null ? cargos : '-')
        		+ ")";
    }
}