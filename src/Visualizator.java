/** 
 * Very simply, it tells the state of the system every second
 * 
 * */

public class Visualizator extends AVerboseThread {
	public final static     int sleepTime           = 1000; // milliseconds
	public final static boolean log_only_on_changes = false ; // if false logs always
	int timer = 0; // for some kind of absolute timing, like modern dmesgs
	
	public void run() {
		setName("Visualizator (Visualize the Country Status from time to time)");
		log("Thread started");
		String previous_status = "";
		String current_status  = "";
		try {
			while(true) {
				current_status = Country.getInstance().toString();
				if (log_only_on_changes) {
					if (current_status != previous_status) {
						//System.out.println("Status changed: " + current_status);
						log("Status changed: "+current_status);
					}					
				} else {
					log("Status: "+current_status);
				}
				previous_status = current_status;
				Thread.sleep(sleepTime);
				timer++;
			}
		} catch (InterruptedException e) {
			log("Interrupted, dying.");
		}
	}

	
}