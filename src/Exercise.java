

import java.util.Arrays;

public class Exercise {
	public static final String ProgramVersion       = "0.9.2" ;

	
	/** 
	 * Deploys the 8 threads for trains, a Visualizator thread to print output 
	 * and a CargoManager to 'produce' cargo objects.
	 * 
	 * A train
	 * 
	 * 
	 * */
	public static void main(String[] args) throws InterruptedException {
		// initialization
		final   Train[] trains   = new   Train[Country.NumberOfTrains];   // thread
		
		//Country hypothetical_country = 
		Country.getInstance(); // force instatiation of the singleton
		
		System.out.println("Exercise.Main(v"+ProgramVersion+"): Starting simulation with "+ 
				Country.NumberOfTrains +" trains and "+ Country.NumberOfStations +" stations");

		CargoManager cargo_thread = new CargoManager();
		cargo_thread.start();
		Thread visualizatorThread = new Visualizator();
		visualizatorThread.start();

		// initializing trains
		for(int i = 0; i < trains.length; i++) {
			// they all start in same position with different speed
			trains[i] = new Train(
					i ,                    // cardinal
					4+i,                   // time to traverse a Track (simulates speed)
					0,                     // initial position
					Country.CargoCapacity 
			); 
			trains[i].start();
		}
		System.out.println("Exercise.Main: Trains started");
		
				
		System.out.println("Exercise.Main: exiting thread (TODO gather threads info before quitting)");
	}
}
