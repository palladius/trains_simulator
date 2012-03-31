import java.util.Arrays;

/**
 *  this contains the main of the exercise and all the relevant variables.
 *  This models a hypothetical country where there are 8 stations and 4 trains.
 *  
 *  This should contain the state of all the app.
 *
 *
 */

/**
 * @author Riccardo_Carlesso
 *
 */
public class Country {
	
	public static Country singleton_instance; // making it singleton
	
	// this describes parameters which are true in my Hypotethical Country
	// TODO move these to ARGV
	public static final int NumberOfStations     = 8 ;
	public static final int NumberOfTrains        = 4 ;
	public static final int RandomCargoPeriodSecs = 5 ; // seconds to randomize the arrive of a new cargo
	public static final int UnloadTimeMilliSecs   = 200 ; // small amount of time to unload any ONE piece of cargo
	public static final int LoadTimeMilliSecs     = 100 ; // bit of time to unload any ONE piece of cargo
	public static final int CargoCapacity         = 10 ;  // number of cargo units a train can have
	
	final Station[] stations = new Station[Country.NumberOfStations]; // complex obj, easy monitor
	final Railway[] railways = new Railway[Country.NumberOfStations]; // easy obj, shared resource
	APlace[] places ; // will hold the 2*N positions

	//final Station[] stations;
	
	//Train trains[World.NumberOFtrains];
	
	// my private Country singleton
	private Country() {
		
		System.out.println("Country.singleton_initializer");

		places = new APlace[ 2*NumberOfStations ]; // cant make it final for this 2* :)
		
		// initializing the Infrastructure
		for(int i = 0; i < Country.NumberOfStations; i++) {
			stations[i] = new Station(i);
			//stations[i].start();
			railways[i] = new Railway(i);
			//railways[i].start();
			
			places[2*i]   = new Station(i);
			places[2*i+1] = new Railway(i);
		}
	}
	
	// singleton invocation
	public static Country getInstance() {
		if (singleton_instance == null) {
			singleton_instance = new Country();
		}
		return singleton_instance;
	}
	

	/**
	 * Id like two visualizations like:
	 * 
	 * { T1 T2 }»»T3»»T4»{T5}»»»»»{}
	 * 
	 * @param verbose: 
	 * 	yes: multiline description
	 *  no:  single line description, like
	 * @return
	 */
	
	public String CurrentState(boolean verbose) {
		// dont use verbose for now
		String ret = "";
		if (verbose) {
			// ret += "=========== Current Country Status ========\n";
			for(int i=0;i<NumberOfStations;i++) {
				ret += '[';
				ret += stations[i].toString();
				ret += ']';
				ret += "  ";
				ret += "»»";
				ret += railways[i].toString();
				ret += "»»";
			}
		} else {
			
		}
		return ret;
	}
	
	public String toString() {
		return CurrentState(true);
	}
}
