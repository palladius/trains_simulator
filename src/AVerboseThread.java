
public abstract class AVerboseThread extends Thread {
	public static boolean Debug   = true;
	public static boolean Verbose = true;
	
	public void dlog(String message); //logs a generic message if Debug is set
	public void vlog(String message); //logs a generic message if Verbose is set
	public void  log(String message) {
		//logs a generic message
		
	
	
	}
	
	private void print(String message) {
		System.out.println("VThread{"+getClass()+"}" + message);
	}
}
