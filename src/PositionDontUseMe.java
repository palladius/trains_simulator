///**
// *  A position is a wrapper around a number:
// *  - 0: station 0
// *  - 1: railway 0 (between station 0 and station 1)
// *  - 2: station 1
// *  - 3: railway 1 (between 1 and 2)
// *  - ...
// *  - 2N:   station N
// *  - 2N+1: railway N (between station N and station N+1) 
// *  
// *  There are EXACTLY (2*Country.NumberOFStations) positions, which are an equivalency class
// *  (so 0 is equivalent to 2N, and 3 is equivalent to 8N+3, but probably not to 7N+3)
// */
//
///**
// * @author Riccardo_Carlesso
// *
// */
//public class Position {
//	private int current_position ;
//	
//	public Position() {
//		current_position = Position.GetInitial() ;
//	}
//	
//	public boolean IsStation() {
//		return current_position % 2 == 0 ;
//	}
//	public boolean IsRailway() {
//		return current_position % 2 == 1 ;
//	}
//	public void setToNext() {
//		current_position+=1;
//		if (current_position >= 2 * Country.NumberOfStations ) {
//			current_position = 0;
//		}
//	}
//	
//	/**
//	 * 
//	 * @return the initial position (to be developed maybe in the future)
//	 */
//	// class methods
//	public static int GetInitial() {
//		return 0; // always station 0
//	}
//
//}
