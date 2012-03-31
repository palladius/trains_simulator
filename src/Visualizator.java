/** 
 * Very simply, it tells the state of the system every second
 * 
 * */

public class Visualizator extends AVerboseThread {
	public final static int sleepTime = 2000;
	public final static boolean log_only_on_changes = false ;
	//public final String verbose_thread_label = 'Visualizator';
	int timer = 0;
	
	
	public void run() {
		log("Thread started");
		String previous_status = "";
		String current_status = "";
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