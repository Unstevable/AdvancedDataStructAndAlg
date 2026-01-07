# AdvancedDataStructAndAlg
A simple repo to store my old programs from my Advanced Data Structures and Algorithms course.

- DynamicSubsequence.java, which accepts an array of integers as input and outputs the maximum length subsequence of the numbers.
Example: 0 8 4 12 2 10 6 14 1 9 5 13 3 11 7 15 would yield the output 0 4 6 9 13 15.

- MoveToFront.java, which performs either move-to-front encoding or decoding based on a char's ASCII value. Accepts either '+'
(decode) or '-' (encode) as an input argument, followed by a '.txt' file which is either encoded or decoded.

- DegreesOfSeparationBFS.java, which accepts a '.txt' file as input that contains movie names with corresponding actors, a delimiter
(say, '/' if that is how the .txt file is organized) an actor's name as the "center" (say, Kevin Bacon, accepted as "Bacon, Kevin") and
another actor's name in the same format where we want to see how far away they are from our actor at the center.
Example: with Kevin Bacon as our center, Nicole Kidman's "Bacon number" is 2.

- Three programs: Usage.java, LineUsage.java and LineReport.java. LineReport.java accepts a '.txt' file as input which contains the
information regarding the usage of 500 terminals in a computer lab, and which user was logged into which terminal at different times.
LineReport.java outputs a report of the terminal number, the user who used that terminal the most, and the count of how much that
user used the terminal. Usage.java is a simple class which is used to hold a username and its corresponding count, and LineUsage.java
is a class which holds all the data needed for a particular terminal.
