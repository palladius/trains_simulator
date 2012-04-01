	/**
	 * This is the exercise main, decoupled with the business logic (as far as it can).
	 * 
	 * Deploys the 8 threads for trains, a Visualizator thread to print output 
	 * and a CargoManager to 'produce' cargo objects.
	 * 
	 * A train
	 * 
	 * 
	 * @author Riccardo_Carlesso
	 *
	 */

//import java.util.Arrays;

public class Exercise {
	public static final String ProgramVersion       = "0.9.9" ;
	
	public static void main(String[] args) throws InterruptedException {
		// initialization
		
		//Country hypothetical_country = 
		Country.getInstance(); // force instatiation of the singleton
		
		System.out.println("Exercise.Main(v"+ProgramVersion+"): Starting simulation with "+ 
				Country.NumberOfTrains +" trains and "+ Country.NumberOfStations +" stations");

		CargoManager cargo_thread = new CargoManager();
		cargo_thread.start();
		Thread visualizatorThread = new Visualizator();
		visualizatorThread.start();

		// initializing trains
		// Note. Conceptually, the train should be born in country constructor...
		//for(int i = 0; i < Country.NumberOfTrains ; i++) {
			// they all start in same position with different speed
			//Train train = Country.getInstance().getTrain(i);
// 			train = new Train(
//					i ,                      // cardinal
//					4+i,                     // time to traverse a Track (simulates speed)
//					4*i,                     // initial position
//					Country.CargoCapacity 
//			); 
			//train.start();
		//}
		System.out.println("Exercise.Main: All trains started");		
		System.out.println("Exercise.Main: exiting thread (TODO gather threads info before quitting)");
	}
}
