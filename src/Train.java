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

public class Train extends Thread {
    //private int balance;
    private int slowness ; // models speed = 1/rail_delay (time in seconds to reach next station)
    private int position ;    // a number representing the position
    private Cargo cargo ;
    private String name;      // TODO remove me..

    public Train(String name, int period, int initial_position) {
        this.slowness = period;
        this.position = initial_position;
        this.cargo = null ;
        this.name = name;
    }

    public void loadCargo(Cargo c) {
        this.cargo = c;
    }
    
    public void run() {
    	System.out.println("Train started: "+ toString() );
    	
    	while (true) {
    		// a Train always starts in a station
    	}
    }

    @Override
    public String toString() {
        return "Train '"+name
        		+ "' (time: " + slowness 
        		+ ", position: "+position
        		+ ", cargo: "+ (cargo != null ? cargo : '-')
        		+ ")";
    }
}