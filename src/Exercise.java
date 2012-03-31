

//import java.util.Random;
import java.util.Arrays;

public class Exercise {
	//public static Random random = new Random();
	
	/** 
	 * Deploys the 8 threads for trains, and a Visualizator thread
	 * 
	 * 
	 * */
	public static void main(String[] args) throws InterruptedException {
		// initialization
		final   Train[] trains   = new   Train[Country.NumberOfTrains];   // thread
		
		//Country hypothetical_country = 
		Country.getInstance(); // force instatiation of the singleton
		
		System.out.println("Exercise.Main: Starting simulation with "+ Country.NumberOfTrains 
				+" trains and "+ Country.NumberOfStations +" stations");

		CargoManager cargo_thread = new CargoManager();
		cargo_thread.start();
		Thread visualizatorThread = new Visualizator();
		visualizatorThread.start();

		// initializing trains
		for(int i = 0; i < trains.length; i++) {
			trains[i] = new Train("train"+(i+1) , 4+i, 0 ); // they all start in same position with different speed
			trains[i].start();
		}
		System.out.println("Exercise.Main: Trains started");
		
				
		System.out.println("Exercise.Main: exiting thread (TODO gather threads info before quitting)");
	}
}
