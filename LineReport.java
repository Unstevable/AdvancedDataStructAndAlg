/* Author: Steven Carr
   Last Edited: January 2023
   Accepts a text file with information regarding the usage of terminals in a computer lab.  A report is generated
   with the terminal numbers, the user who used that specific terminal the most and how much they used that terminal.
 */

package pa0;
// Import here as needed
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LineReport {
	// Variables here
	private LineUsage[] arraylu;

	// Constructor
	public LineReport() {
		// Initialize an array of LineUsage objects, with the index being at 501
		// because we will be using terminals 1-500.
		this.arraylu = new LineUsage[501];
		// For each entry in this array, properly initialize it as a LineUsage object
		for (int x = 1; x < 501; x++) {
			arraylu[x] = new LineUsage();
		}
	}

	// read input data, put facts into lines array
	void loadData(String fname) throws FileNotFoundException{
		// Use the given absolute path string for the file
		File readFile = new File(fname);

		// Initialize a new Scanner to read the file
		Scanner readdata = new Scanner(readFile);

		// While the file has a next line and is not empty
		while (readdata.hasNextLine()) {

			// Store the line in a String
			String line = readdata.nextLine();

			// Split the current line into 2 strings, one being the terminal number
			// and the other being the username, separated by a space
			String[] words = line.split(" ");

			// Convert the String terminal number into an integer
			int terminal = Integer.parseInt(words[0]);

			// Search the Line Report array until we find the correct location
			// corresponding to the given terminal number
			for (int i = 1; i < arraylu.length; i++) {

				if (i == terminal) {
					// Once at the correct terminal, add an observation using the
					// given username from the file
					arraylu[i].addObservation(words[1]);
				}
			}
		}

	}

	// given loaded lines array, generate report on lines
	void generateReport() {
		// For each terminal
		for (int i = 1; i < arraylu.length; i++) {
			// Find their individual max usage
			Usage use = arraylu[i].findMaxUsage();
			// And Print the terminal number, the user with the max uses and their count
			System.out.println(i + " " + use.getUser() + " " + use.getCount());
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// Read the file from command line argument
		File newFile = new File(args[0]);

		// Convert the file's absolute path to a String
		String data = newFile.getAbsolutePath();

		// Initialize a new Line Report
		LineReport report = new LineReport();

		// Properly load the data of the terminal and username
		report.loadData(data);

		// Generate the report for the terminals
		report.generateReport();
	}
}
