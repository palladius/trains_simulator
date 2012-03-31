/**
 * This is a particular IPlace where ONLY ONE TRAIN can exist at a given time
 * 
 * 
 * 
 * @author Riccardo_Carlesso
 *
 */
public class Railway extends APlace {
	Train train_here; // whether or not we have a train here
	int position;     // 0..7

	// constructor: a railway is initially empty
	public Railway(int pos) {
		position = pos;
		train_here = null ;
	}
	
	public void addTrain(Train newTrain) throws Exception {
		if (train_here != null) {
			throw new Exception("a train is already present: " + train_here);
		}
		train_here = newTrain;
	}
	
	public void removeTrain(Train oldTrain)  throws Exception {
		if (train_here == null) {
			throw new Exception("no train was present!");
		}
		
		if (train_here != oldTrain) {
			throw new Exception("trains dont correspond: " + train_here + " vs " + oldTrain);
		}
		train_here = null;
	}
	
	public boolean isEmpty() {
		return train_here == null;
	}

	public String toString() {
		return "R"+position+"("+ train_here +")";
	}
	public int absolutePosition() {
		// 2N for stations and 2N+1 for railways
		return 2 * position + 1;
	}
}
