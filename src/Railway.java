/**
 * This is a particular IPlace where ONLY ONE TRAIN can exist at a given time.
 * 
 * In the problem this is called 'Track'.
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
	
	public synchronized void addTrain(Train newTrain)  { // throws Exception
		if (train_here == newTrain) {
			//throw new Exception("a train is already present: " + train_here);
			System.err.println("Fatal BUG! I'm trying to register for a Railway I already belong:\n"
				+ " ME:     " + newTrain   + '\n'
				+ " MYSELF: " + train_here + '\n'
			);
			System.exit(67);
		}
		if (train_here != null) {
			//throw new Exception("a train is already present: " + train_here);
			System.err.println("Kaboom! These two train collided:\n"
				+ " NEW:  " + newTrain   + '\n'
				+ " HERE: " + train_here + '\n'
			);
			System.exit(66);
		}
		// MUST BE SYNCHRONIZED
		train_here = newTrain;
	}
	
	// must be synchronized, although only one process should run at a given time..
	public synchronized void removeTrain(Train oldTrain) { //  throws Exception
		//if (train_here == null) {
		//	throw new Exception("no train was present!");
		//}
		
		if (train_here != oldTrain) {
			//throw new Exception("trains dont correspond: " + train_here + " vs " + oldTrain);
			System.err.println("Trying to remove a train which is not there:\n"
					+ " - THIS: " + oldTrain   + '\n'
					+ " - HERE: " + train_here + '\n'
				);
			System.exit(99);
		}
		train_here = null;
	}
	
	public boolean isEmpty() {
		return train_here == null;
	}

	public String toString() {
		return "R"+position+"("+ (train_here == null ? '-' : train_here)+")";
	}
	public String toStringMini() {
		return "R"+position + (train_here == null 
					? "" 
					: "{" + train_here.toStringMini() + "}"
				);
	}
	public int getPosition() {
		// 2N for stations and 2N+1 for railways
		return 2 * position + 1;
	}

}
