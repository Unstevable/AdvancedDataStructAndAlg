/* Author: Steven Carr
   Last Edited: January 2023
   Simple class that holds a username and its corresponding count
 */
package pa0;
// One user's record on one line: how many times
// this user has been seen on this line
import java.util.HashMap;
public class Usage {
	// Put variables here.
	private String user;
	private int count;
	private HashMap<String, Integer> usage;

	public Usage(String user, int count) {
		// Initialize the Hashmap for the user and their count
		HashMap<String, Integer> usage = new HashMap<String, Integer>();
		this.user = user;
		this.count = count;
		// Put the given user anc count into the HashMap
		usage.put(user, count);
	}

	public String getUser() {
		// Simply return the given user
		return user;
	}

	public int getCount() {
		// Simply return the user's count
		return count;
	}
}
