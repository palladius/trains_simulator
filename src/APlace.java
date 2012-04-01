/**
 * A APlace (A is for Abstract Class) is an interface common to both Railways and Stations
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
