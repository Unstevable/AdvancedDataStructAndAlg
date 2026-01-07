// Author: Steven Carr
// Last Edited: April 2023
// Accepts an array of integers as input and outputs the maximum length subsequence of the numbers.
// Example: 0 8 4 12 2 10 6 14 1 9 5 13 3 11 7 15 would yield the output 0 4 6 9 13 15.

package pa3;
import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
public class DynamicSubsequence {
    private ArrayList<Integer> input; // instance variable for ArrayList
    public DynamicSubsequence(ArrayList<Integer> input) {
        // If the ArrayList input is null, throw error message
        if (input == null) {
            throw new IllegalArgumentException("ArrayList is empty.");
        }

        // Otherwise, simply make the ArrayList equal to this constructor's ArrayList
        this.input = input;
    }

    // Does all the work of computing the maxSubsequence, and returns it
    public ArrayList<Integer> maxSubsequence () {
        // Initialize arrays for the predecessors, the size of the subsequence ending at
        // the given index, and a duplicate array to keep track of the current previous
        // numbers
        int[] predecessor = new int[input.size()];
        int [] sizeOfIndexSub = new int[input.size()];
        int[] previousNumbers = new int[input.size()];

        // Initialize the starting size of each beginning subsequence to 1
        for (int i = 0; i < input.size(); i++) {
            sizeOfIndexSub[i] = 1;
        }

        for (int currNum = 0; currNum < input.size(); currNum++) {
            // if it's the beginning, set the predecessor to itself
            if (currNum == 0) {
                predecessor[currNum] = input.get(currNum);
                previousNumbers[currNum] = input.get(currNum);
            }


            // Set a variable for the next number
            int nextNum = currNum+1;

            // If the next number is equal to/greater than the input size...
            if (nextNum >= input.size()) {
                // Set the current position in the duplicate array to the current number,
                // and break the loop
                previousNumbers[currNum] = input.get(currNum);
                break;
            }

            // Set a variable for the previous number
            int prevNum = currNum-1;

            // If the current number is less than the next number...
            if (input.get(currNum) < input.get(nextNum)) {
                // Set the next number's predecessor to the current number
                predecessor[nextNum] = input.get(currNum);
                // Set the size of the current subsequence for the next number equal to
                // the current number's subsequence plus 1
                sizeOfIndexSub[nextNum] = sizeOfIndexSub[currNum] + 1;

                // If this isn't the first number in the list...
                if (currNum > 0) {
                    // Starting from the previous number until the first number...
                    for (int j = prevNum; j >= 0; j--) {
                        // If the current previous number is less than the next number...
                        if (input.get(j) < input.get(nextNum)) {
                            // AND the size of the previous number's subsequence plus 1
                            // is greater than the next number's current subsequence
                            if (sizeOfIndexSub[j] + 1 > sizeOfIndexSub[nextNum]) {
                                // Then this is the more optimal subsequence; the next
                                // number's subsequence size is now this previous number's
                                // plus 1, and it's predecessor is the previous number
                                sizeOfIndexSub[nextNum] = sizeOfIndexSub[j] + 1;
                                predecessor[nextNum] = input.get(j);
                            }
                        }
                    }
                    // Keep track of which numbers we have already covered for future
                    // comparisons
                    previousNumbers[currNum] = input.get(currNum);
                }
                // If the current number is NOT less than the next number, and this is
                // NOT the first number in the list
            } else if (prevNum >= 0) {
                // Same loop as above, checking all previous numbers against the next one
                for (int j = prevNum; j >= 0; j--) {
                    if (input.get(j) < input.get(nextNum)) {
                        // If the previous number is less than, same as above
                        if (sizeOfIndexSub[j] + 1 > sizeOfIndexSub[nextNum]){
                            sizeOfIndexSub[nextNum] = sizeOfIndexSub[j] + 1;
                            predecessor[nextNum] = input.get(j);
                        }
                    }
                }
                // Keep track of the numbers we have already covered
                previousNumbers[currNum] = input.get(currNum);
            }
        }
        // Set a variable for the last number, which is not covered in the above loop
        int lastNumber = input.size() - 1;

        // From the second to last number until the beginning of the list...
        for (int j = input.size() - 2; j >= 0; j--) {
            // Same as above, if the current number is less AND if the current number's
            // subsequence + 1 is greater than the last number's current subsequence
            if (input.get(j) < input.get(lastNumber)) {
                if (sizeOfIndexSub[j] + 1 > sizeOfIndexSub[lastNumber]) {
                    // Update the last number's subsequence
                    sizeOfIndexSub[lastNumber] = sizeOfIndexSub[j] + 1;
                    predecessor[lastNumber] = input.get(j);
                }
            }
        }
        // For accountability purposes, set the last number in clone array
        previousNumbers[lastNumber] = lastNumber;

        // Set variables for the length of the maximum subsequence and the maximum
        // subsequence's index in the size array
        int maxSubLength = 1;
        int maxSubIndex = 0;

        // For the entirety of the list...
        for (int i = 0; i < input.size(); i++) {
            // If the current maximum subsequence length is less than the current
            // index's max sub length, update the max sub length and index
            if (maxSubLength < sizeOfIndexSub[i]) {
                maxSubLength = sizeOfIndexSub[i];
                maxSubIndex = i;
            }
        }

        // Create a new ArrayList to hold the maximum subsequence
        ArrayList<Integer> maxSub = new ArrayList<>(maxSubLength);

        // Create a MinPQ to put the values of the subsequence in (we are going to
        // put the numbers in reverse, then put them into the new ArrayList via
        // deleting the smallest number each time)
        MinPQ<Integer> subsequencePQ = new MinPQ<>(maxSubLength);

        // While the maximum subsequence length is still greater than 0
       while (maxSubLength > 0) {
           // Insert the current maximum subsequence index number from the ArrayList
            subsequencePQ.insert(input.get(maxSubIndex));

            // Now check the list for the predecessor of the previous inserted number
            for (int i = 0; i < input.size(); i++) {
                if (input.get(i) == predecessor[maxSubIndex]) {
                    // Once the predecessor is found, update the max subsequence index
                    // (this is now the new max subsequence), and break the for loop
                    maxSubIndex = i;
                    break;
                }
            }
            // Decrement the maximum subsequence length to reflect the new max sub length
            maxSubLength--;
        }

       // While the PQ is not empty
       while (!subsequencePQ.isEmpty()) {
           // Take the smallest Integer and put it into the new Array List for the max sub
           maxSub.add(subsequencePQ.delMin());
       }

        // Return an ArrayList of the max subsequence in the ArrayList
        return maxSub;
    }

    public static void main(String[] args) {
        // Read in the list of numbers
        In in = new In(args[0]);
        int[] arr1 = in.readAllInts();

        // Initialize a new ArrayList with the given numbers
        ArrayList<Integer> input = new ArrayList<>(arr1.length);
        for (int number : arr1) {
            input.add(number);
        }

        // Print test to ensure proper ArrayList initialization
        for (int i : input) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        // Initialize a Dynamic Subsequence with the ArrayList
        DynamicSubsequence dynamSub = new DynamicSubsequence(input);

        // Find the maximum subsequence of the ArrayList
        dynamSub.maxSubsequence();

        // Test the maximum subsequence
        for (int num : dynamSub.maxSubsequence()) {
            StdOut.print(num + " ");
        }
    }
}
