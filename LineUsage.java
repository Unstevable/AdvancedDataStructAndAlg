/* Author: Steven Carr
   Last Edited: January 2023
   Holds all the data for a terminal, such as the user's who access it, their count, and which user has the
   maximum count on the terminal.
 */

package pa0;
// Import packages as needed
import java.util.HashMap;

// LineUsageData.java: Handle one line's data, using a Map
public class LineUsage {
	// Variables here
	private HashMap<String, Integer> lines;

	// Constructor
	public LineUsage() {
		// Initialize the HashMap
		this.lines = new HashMap<String, Integer>();
	}

	// add one sighting of a user on this line
	public void addObservation(String username) {
		// if the given username is contained within the HashMap already, re-put the
		// given username, but increment the total count of the username by 1
		if (lines.containsKey(username)) {
			lines.put(username, lines.get(username) + 1);
		} else {
			// Otherwise, the username is not in the HashMap, so add it with a count
			// of 1
			lines.put(username, 1);
		}
	}

	// find the user with the most sightings on this line
	public Usage findMaxUsage() {
		// Initialize an empty string for max
		String max = "";
		// Make max's current count 0 to start
		lines.put(max, 0);
		// For each user that is in the Hashmap
		for (String user : lines.keySet()) {
			// if the count for the user is greater than the current max count
			if (lines.get(user) > lines.get(max)) {
				// Then the maximum usage is from that user
				max = user;
			}
		}
		// After this for loop, if the count for max is still 0, there was nobody
		// on this terminal at all
		if (lines.get(max) == 0) {
			return new Usage("<NONE>", 0);
		}
		// Otherwise, return the username and count of the person with max use
		return new Usage(max,lines.get(max));
	}
}
