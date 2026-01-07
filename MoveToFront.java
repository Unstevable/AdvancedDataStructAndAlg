// Author: Steven Carr
// Last Edited: April 2023
// Performs either move-to-front encoding or decoding based on a char's ASCII value. Accepts either '+'
// (decode) or '-' (encode) as an input argument, followed by a '.txt' file which is either encoded or decoded.

package pa3;
import edu.princeton.cs.algs4.*;

public class MoveToFront {

    public static void encode(String f) {
        // Initialize the list of ASCII characters
        // ASCII characters are simply the char versions of ints 0-255
        char[] ascii = new char[256];
        for (int i = 0; i < 256; i++) {
            ascii[i] = (char) i;
        }

        // Start new BinaryIn stream via the given file
        BinaryIn input = new BinaryIn(f);
        // Start new BinaryOut stream via the given file
        BinaryOut output = new BinaryOut(f);

        // While the input stream is not empty, perform move to front encoding based
        // on a true print value with the current character
        while (!input.isEmpty()) {
            mtf(output, true, ascii, input.readChar());
        }
        // Close the output stream
        output.close();
        output.flush();
    }

    public static void decode(String f) {
        // Initialize the list of ASCII characters
        // ASCII characters are simply the char versions of ints 0-255
        char[] ascii = new char[256];
        for (int i = 0; i < 256; i++) {
            ascii[i] = (char) i;
        }
        // Start new BinaryIn stream via the given file
        BinaryIn input = new BinaryIn(f);
        // Start new BinaryOut stream via the given file
        BinaryOut output = new BinaryOut(f);

        // While the input stream isn't empty
        while (!input.isEmpty()) {
            // Get the 8 bits of binary
            int bin = input.readInt(8);
            // MUST system print before doing BinaryOut.write
            System.out.print(ascii[bin]);
            // Write the given char with the corresponding binary
            output.write(ascii[bin]);
            // Perform move to front decoding, based on the print value being false
            mtf(output, false, ascii, ascii[bin]);
        }
        // Close the output stream
        output.close();
        output.flush();
    }

    public static void mtf(BinaryOut output, boolean printValue, char[] ascii, char curr){
        // Set a variable for the original beginning value in the ascii array currently
        char original = ascii[0];
        // Checking the ascii array for the given character
        for (int i = 0; i < 256; i++) {
            // If the found ascii character and the current character are the same
            if (ascii[i] == curr) {
                // Make the current character the beginning of the array (move to front)
                ascii[0] = curr;
                // Make the original character take the place of the found character in the array
                ascii[i] = original;

                // If the print value is true (encoding)
                if (printValue == true) {
                    // MUST put a System.out.print before a BinaryOut.write
                    System.out.print((char) i);
                    // Write the character of the number
                    output.write((char) i);
                }
                break;
            }
            char temp = ascii[i];
            ascii[i] = original;
            original = temp;
        }
    }

    public static void main(String[] args) {
        String arg = new String(args[0]);
        // If the first argument is '+', do decode
        if (arg.equals("+")) {
            MoveToFront.decode(args[1]);
            // If the first argument is '-', do encode
        } else if (arg.equals("-")) {
            MoveToFront.encode(args[1]);
        }
    }

}
