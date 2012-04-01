//import java.util.Arrays;

/**
 * This ''Hypothetical country'' is the main class (and only singleton).
 * 
 *  This contains the main of the exercise and all the relevant variables.
 *  This models a hypothetical country where there are 8 stations and 4 trains.
 *  
 *  This should contain the state of all the app.
 *  
 *  An interesting possibility for the Stations/Railways taxonomy would be to
 *  refactor them into an array of 2N "APlaces", like:
 *  
 *    APlace[] places ; // will hold the 2*N positions,
 *    
 *  that point alternatively to stations and railways.
 *
 *
 *
 * @author Riccardo_Carlesso
 *
 */

import java.util.Date;

public class Country {
	
	public static Country singleton_instance; // making it singleton
	
	// this describes parameters which are true in my Hypothetical Country
	
	// TODO_IF_TIME move these to ARGV
	public static final int NumberOfStations      = 8 ;
	public static final int NumberOfTrains        = 4 ;
	public static final int RandomCargoPeriodSecs = 5 ; // seconds to randomize the arrive of a new cargo
	public static final int UnloadTimeMilliSecs   = 200 ; // small amount of time to unload any ONE piece of cargo
	public static final int LoadTimeMilliSecs     = 100 ; // bit of time to unload any ONE piece of cargo
	public static final int CargoCapacity         = 10 ;  // number of cargo units a train can have
	
	final Station[] stations = new Station[Country.NumberOfStations]; // complex obj, easy monitor
	final Railway[] railways = new Railway[Country.NumberOfStations]; // easy obj, shared resource
	final   Train[] trains   = new   Train[Country.NumberOfTrains];   // thread objects
	
	//APlace[] placesTmpReplaceStationAndRailway ; // will hold the 2*N positions, that point alternatively to stations and railways
	
	// my private Country singleton
	private Country() {
		System.out.println("Country.singleton_initializer");
		// initializing the Infrastructure
		for(int i = 0; i < Country.NumberOfStations; i++) {
			stations[i] = new Station(i);
			railways[i] = new Railway(i);
			//placesTmpReplaceStationAndRailway[2*i]   = new Station(i);
			//placesTmpReplaceStationAndRailway[2*i+1] = new Railway(i);
		}
		for(int i = 0; i < Country.NumberOfTrains ; i++) {
			// they all start in same position with different speed
			//Train train = trains[i];
 			trains[i] = new Train(
					i ,                      // cardinal
					4+i,                     // time to traverse a Track (simulates speed)
					2*i,                     // initial position
					Country.CargoCapacity 
			); 
 			trains[i].start(); // it's a thread
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
	
	public String currentState(boolean verbose) {
		// dont use verbose for now
		String ret = "";
		if (verbose) {
			// verbose multiline print
			ret += "== Current Country Status ==\n";
			ret += "Date: "+ (new Date()) +"\n";
			for(int i=0;i<NumberOfStations;i++) {
				ret += " Station["+i+"]: ";
				ret += stations[i].toString();
				ret += '\n';
				ret += " Railway["+i+"]: ";
				ret += railways[i].toString();
				ret += '\n';
			}
			ret += "== Trains ==\n";
				for(int j=0; j < NumberOfTrains; j++) {
					ret += " Train["+j+"]: ";
					ret += trains[j].toString();
					ret += '\n';
				}
			//} catch (Exception e) {
			//	ret += "Train visualization Exception ("+e+") :(";
			//}
		} else {
			for(int i=0;i<NumberOfStations;i++) {
				ret += '[';
				ret += stations[i].toStringMini();
				ret += ']';
				ret += "  ";
				ret += "»";
				ret += railways[i].toStringMini();
				ret += "»";
			}			
		}
		return ret;
	}
	/**
	 * This method takes a Cargo and places it in the appropriate station
	 * 
	 * @param cargo
	 */
	public void deliverCargoToAppropriateStation(Cargo cargo) {
		int src = cargo.getSource();
		stations[src].addCargo(cargo);
	}
	
	public String toString() {
		return currentState(false);
	}
	
	public Station getStation(int which) {
		return stations[which]; // make sure its from 1 to N
	}
	public Train getTrain(int which) {
		return trains[which]; // make sure its from 1 to N
	}
	public Railway getRailway(int railway_number) {
		return railways[railway_number];
	}
}
