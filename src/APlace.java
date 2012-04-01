/**
 * A APlace (A is for Abstract Class) is an "interface" common to both Railways and Stations.
 * 
 * I chose an Abstract Class to be able to add code. In fact, I wanted this to be part of a 
 * bigger refactoring effort that would allow our Country to be a mixture of Railways and Stations
 * (not necessarily one and one). First step would be defining a Position object around this and a
 * very simple getNext() for it.
 * 
 *                     ----------
 *                     | APlace |
 *                     ----------
 *                          |
 *                          |
 *            ------------------------------
 *            |                            |
 *      -----------                    -----------              
 *      | Station |                    | Railway |
 *      -----------                    -----------              
 * 
 */

/**
 * @author Riccardo_Carlesso
 *
 */
public abstract class APlace {
	
	public abstract void addTrain(Train t) ; // throws Exception;
	public abstract void removeTrain(Train t); // throws Exception;
	public abstract boolean isEmpty();
	public abstract int getPosition() ;       // 2N for stations and 2N+1 for railways
	
	public boolean isBusy () {
		return ! isEmpty() ;
	}
	
	public static APlace getCountryPlace(int position) {
		if (position % 2 == 0) { // even, station
			return Country.getInstance().getStation(position/2);
		} else { // odd, railway
			return Country.getInstance().getRailway(position/2);
		}
	}

}
