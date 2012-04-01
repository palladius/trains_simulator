	/**
	 * This is the exercise main, decoupled with the business logic (as far as it can).
	 * 
	 * Deploys the 8 threads for trains, a Visualizator thread to print output 
	 * and a CargoManager to 'produce' cargo objects.
	 * 
	 * 
	 * @author Riccardo_Carlesso
	 *
	 */

//import java.util.Arrays;

public class Exercise {
	public static final String ProgramVersion       = "1.0.01alpha" ;
	
	public static void main(String[] args) throws InterruptedException {
		// initialization
		Country.getInstance(); // force instatiation of the singleton
		
		System.out.println("Exercise.Main(v"+ProgramVersion+"): Starting simulation with "+ 
				Country.NumberOfTrains +" trains and "+ Country.NumberOfStations +" stations");

		CargoManager cargo_thread = new CargoManager();
		cargo_thread.start();
		Thread visualizatorThread = new Visualizator();
		visualizatorThread.start();
		
		while(true) {
			try{ 
				Country.getInstance().wait(); 
			} catch (InterruptedException e) {
				System.out.println("Exercise.Main: Something woke me up");
			}
		}
		//System.out.println("Exercise.Main: exiting thread (TODO gather threads info before quitting)");
	}
}
