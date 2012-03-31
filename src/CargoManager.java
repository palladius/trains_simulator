/* 
	Thread that manages the Cargo creation

	Manages the 8 stations and creates at random times cargos for the cities.
	Ideally, it should produce 8 stockastic independent events with poissonian
	distribution with an Average of 'µ'.
*/

class CargoManager extends Thread {

	public void run() {
		System.out.println("CargoManager: started");
	}
	
	
}