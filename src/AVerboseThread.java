/**
 * This is a Thread on steroids (or actually it helps visualizing log messages).
 *
 * I feel Java is a wee too verbose without these utilities...
 * 
 * @author Riccardo_Carlesso
 *
 */
public abstract class AVerboseThread extends Thread {
	public static boolean Debug   = false;
	public static boolean Verbose = true;
	
	public void dlog(String message) {
		//logs a generic message if Debug is set
		if (Debug) {
			print("DEB " + message);
		}
	}
	public void vlog(String message) {
		//logs a generic message if Verbose is set
		if (Verbose) {
			print(message);
		}
	}
	public void  log(String message) {		//logs a generic message
		print(message);
	}
	
	// Note that the substring removes "class " from beginning of GetClass()
	private void print(String message) {
		System.out.println("Th{"+getName()+"} " + message);
		//System.out.println("AVT{"+getClass().toString().substring(6)+"} " + message);
	}
}
