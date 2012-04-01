/**
 * 
 */

/**
 * @author Riccardo_Carlesso
 *
 * models a position, a circular number from 0 to 2N-1, where N =  Country.NumberOfStations
 * Every position corresponds to a place:
 * 0: Station0
 * 1: Railway0
 * 2: Station1
 * 3: Railway1
 * ...
 * 2N-2: Station N-1 (which is the Nth)
 * 2N-1: Railway N-1 (which is the Nth)
 * 
 */
public class Position {
	final int absolutePosition ;

	Position(int pos) {
		absolutePosition = pos;
	}
	
	public static int nextPos(int pos) {
		int next = pos+1;
		if (next == Country.NumberOfStations * 2)
			next = 0;
		return next;
	}
	public static int prevPos(int pos) {
		int next = pos-1;
		if (next == -1)
			next = Country.NumberOfStations * 2 - 1;
		return next;
	}
}
