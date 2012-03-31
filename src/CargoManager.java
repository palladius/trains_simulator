/* 
	Thread that manages the Cargo creation

	Manages the 8 stations and creates at random times cargos for the cities.
	Ideally, it should produce 8 stocastic independent events with poissonian
	distribution with an Average of 'µ' (Greek mu).
	
	Ideally, I should use 8 threads and have each a poissonian generator (that's what we
	used in Operation Research to simulate stocastic events). For the purposes of this
	exercise, we think a single linear generator will do.
	
*/

class CargoManager extends AVerboseThread {

	// Uses Country.RandomCargoPeriodSecs 
	public void run() {
		vlog("started");
	}
	
	
}