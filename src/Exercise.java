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

public class Exercise {
	public static final String ProgramVersion       = "1.0.02" ;
	
	public static void main(String[] args) throws InterruptedException {
		// initialization
		Country.getInstance(); // force instatiation of the singleton
		
		System.out.println("Exercise.Main(v"+ProgramVersion+"): Starting simulation with "+ 
				Country.NumberOfTrains +" trains and "+ Country.NumberOfStations +" stations");

		CargoManager cargoThread = new CargoManager();
		cargoThread.start();
		Thread visualizatorThread = new Visualizator();
		visualizatorThread.start();

		System.out.println("Exercise.Main: exiting main thread"); // it would be nice to gather threads info before quitting
	}
}
