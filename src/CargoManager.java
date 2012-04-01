/* 
	Thread that manages the random Cargo creation.

	Manages the 8 stations and creates at random times cargos for the cities.
	Ideally, it should produce 8 stocastic independent events with poissonian
	distribution with an Average of 'µ' (Greek mu).
	
	Ideally, I should use 8 threads and have each a poissonian generator (that's what we
	used in Operation Research to simulate stocastic events). For the purposes of this
	exercise, we think a single linear generator will do.
	
*/
import java.util.Random;
	
class CargoManager extends AVerboseThread {
	public static Random random = new Random();

	/** 
	 * This thread generates every "Country.RandomCargoPeriodMs" seconds
	 * a new random instance of Cargo and delivers it to the appropriate station.
	 * See up for some mathematical disquisitions.
	 */
	public void run() {
		setName("CargoManager");
		log("started");
		try {
			while (true) {
				Thread.sleep(random.nextInt( Country.RandomCargoPeriodMs )); 
				Cargo recurrent = Cargo.getRandomCargo(); // creates a random Cargo instance (factory)
				Country.getInstance().deliverCargoToAppropriateStation(recurrent);
				log("New cargo available: "+recurrent);
			}
		} catch (InterruptedException e) {
			log("Something wrong happened");
		}
	}
	
	
}