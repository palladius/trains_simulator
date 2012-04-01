/** 
 * Very simply, it tells the state of the system every second
 * 
 * */
import java.io.*;

public class Visualizator extends AVerboseThread {
	public final static     int sleepTimeSecs        = 10;     // seconds
	public final static boolean log_only_on_changes = false ; // if false logs always
	int timer = 0; // for some kind of absolute timing, like modern dmesgs
	
	public void run() {
		setName("Visualizator");
		log("Thread started");
		String previous_status = "";
		String current_status  = "";
		String current_status_long;
		try {
			while(true) {
				current_status      = Country.getInstance().currentState(false);
				current_status_long = Country.getInstance().currentState(true);
				if (log_only_on_changes) {
					if (current_status != previous_status) {
						//System.out.println("Status changed: " + current_status);
						log("Status changed: "+current_status);
					}					
				} else {
					log("Status: "+current_status);
				}
				previous_status = current_status;
				
				// write also status to file
				try {
					File f = new File("status_long.txt");
					FileWriter fstream = new FileWriter(f);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(current_status_long);
					out.close(); // Im going to use it a lot! No sense in closing
				} catch (Exception e) {
					log("Some error writing..");
				}
				Thread.sleep(sleepTimeSecs * 1000);
				timer++;
			}
		} catch (InterruptedException e) {
			log("Interrupted, dying.");
		}
	}

	
}