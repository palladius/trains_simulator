/*
	8 stations.
	Each station can have any number of trains inside and any number of cargos to be delivered to
	some other station.
*/

//import java.util.Arrays;
import java.util.ArrayList;

public class Station extends APlace {
	private ArrayList<Train> trains_here; // array of Trains currently here
	private ArrayList<Cargo> cargos;      // array of Cargos parked here and to be picked up
	private              int position;    // 0..7
	
	// a station is born with no trains there
	public Station(int label) {
		position = label;
		trains_here = new ArrayList<Train>();
		cargos      = new ArrayList<Cargo>();
	}
	
	public void addTrain(Train newTrain) throws Exception {
		//if (train_here != null) {
		//	throw new Exception("this train is already present in this station: " + train_here);
		//}
		trains_here.add(newTrain);
	}
	
	public void removeTrain(Train train)  throws Exception {
		// TODO check exception
		//if (train_here != null) {
		//	throw new Exception("this train wasnt in this station before!");
		//}
		trains_here.remove(train);
	}
	
	public boolean isEmpty() {
		return trains_here.isEmpty();
	}
	
	public String toString() {
		return "S"+position+"("+ cargos.size() +")";
	}
	public int absolutePosition() {
		// 2N for stations and 2N+1 for railways
		return 2 * position;
	}

}