import java.util.Arrays;

/**
 *  this contains the main of the exercise and all the relevant variables.
 *  This models a hypotethical country where there are 8 stations and 4 trains.
 *  
 *  This should comntain the state of all the app.
 *
 *
 */

/**
 * @author Riccardo_Carlesso
 *
 */
public class Country {
	
	public static Country singleton_instance; // making it singleton
	
	public static final int NumberOfStations = 8 ;
	public static final int NumberOfTrains   = 4 ;
	
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
