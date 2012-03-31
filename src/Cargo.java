/* 
	This models cargos
	
	A cargo is delivered (randomly) to a station and has to be delivered to another station.
	It doesnt matter what train picks it up.
	
	A station holds a Cargo (thus a cargo doesnt need to know where it is, just where its going)
*/

import java.util.Arrays;

public class Cargo {
	int destination;
	
	public Cargo(int dest) {
		destination = dest;
	}
	
	public String toString() {
		return "C("+destination+")";
	}
	
}