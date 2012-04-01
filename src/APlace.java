/**
 * A APlace (A is for Abstract Class) is an interface common to both Railways and Stations
 */

/**
 * @author Riccardo_Carlesso
 *
 */
public abstract class APlace {
	
	public abstract void addTrain(Train t) throws Exception;
	public abstract void removeTrain(Train t) throws Exception;
	public abstract boolean isEmpty();
	public abstract int absolutePosition() ; // 2N for stations and 2N+1 for railways
	
	public boolean isBusy () {
		return ! isEmpty() ;
	}
	
	public int getNextPosition() {
		int current_position = absolutePosition() + 1; // increment
		if (current_position >= 2 * Country.NumberOfStations ) {
			current_position = 0;
		}
		return current_position;
	}
	
	
}
