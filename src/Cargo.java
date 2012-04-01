/* 
	This models cargos
	
	A cargo is delivered (randomly) to a station and has to be delivered to another station.
	It doesn't matter what train picks it up, so only "destination" is strictly necessary.
	For maintenance of code (possible future changes) and better encapsulation, I prefer to put the source
	info here, though. 
	
	A station holds a Cargo (thus a cargo doesn't need to know where it is, just where its going)
*/

//import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Cargo {
	private static Random random = new Random();
	
	int source;       // 0..N station number (it's NOT a position)
	int destination;  // 0..N station number (it's NOT a position)
	Date birthDate;   // might be interesting to make statistics about the lifespan of a cargo object

	public Cargo(int src,int dest) {
		source      = src;
		destination = dest;
        birthDate = new Date(); // simulates a label/name: I may have more than one cargo with same src/dst
		//if (source==destination) {
		//	throw Exception("src and destination for a Cargo shouldn't be the same!");
		//}
	}
	
	public String toString() {
		//return "C("+source+"->"+destination+" - "+ birthDate +")";
		return "C("+source+"->"+destination+")";
	}
	
	// creates a random Cargo. It's important that destination and source are not the same.
	public static Cargo getRandomCargo() {
		int srcStation = random.nextInt( Country.NumberOfStations );   // random 0..7, say 3
		int dstStation = random.nextInt( Country.NumberOfStations -1); // random 0..6
		if (dstStation >= srcStation) // 0..2 => ok ; 3..6 => increment
			dstStation++;
		return new Cargo(srcStation,dstStation);
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}

}