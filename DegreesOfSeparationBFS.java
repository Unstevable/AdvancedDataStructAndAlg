/* Author: Steven Carr
   Last Edited: March 2023
   Accepts a text file as input that contains movie names with corresponding actors, a delimiter, an actor's name
    as the "center" (say, Kevin Bacon, accepted as "Bacon, Kevin") and another actor's name in the same format
    where we want to see how far away they are from our actor at the center.
    Example: with Kevin Bacon as our center, Nicole Kidman's "Bacon number" is 2.
 */
package pa2;
import edu.princeton.cs.algs4.*;

public class DegreesOfSeparationBFS {
    // Symbol Graph instance variable
    private SymbolGraph symgraph;
    // BreadthFirstPaths instance variable
    private BreadthFirstPaths bfs;
    // String source instance variable
    private String source;
    // Stack actorpath instance variable
    Stack<Integer> actorpath;

    public DegreesOfSeparationBFS(String filename, String delimiter, String source) {
        // Initialize the Symbol Graph to a new Symbol Graph with arguments from the
        // filename and delimiter
        this.symgraph = new SymbolGraph(filename, delimiter);
        this.source = source;

        // Initialize a graph using the Symbol Graph
        Graph G = symgraph.graph();

        // If the Symbol Graph doesn't contain our source, output "error" message
        if (!symgraph.contains(source)) {
            StdOut.println(source + "is not in the given database.");
        }

        // Initialize an int variable for the index of the source (source actor)
        int sourceindex = symgraph.indexOf(source);
        // Initialize a BreadthFirstPath for every vertex from source (actor) vertex
        this.bfs = new BreadthFirstPaths(G, sourceindex);

    }

    public SymbolGraph getSymbolGraph() {
        // Return the Symbol Graph
        return this.symgraph;
    }

    public BreadthFirstPaths getBreadthFirstPaths() {
        // Return the BreadthFirstPaths
        return this.bfs;
    }

    public int baconNumber(String sink) {
        // If the Symbol Graph doesn't contain this actor (sink), return -1
        if (!symgraph.contains(sink)) {
            return -1;
        }

        // Find index of the current actor (sink)
        int sinkindex = symgraph.indexOf(sink);

        // If there is no path from the source actor to the current actor, return -1
        if (!bfs.hasPathTo(sinkindex)) {
            return -1;
        }

        // Otherwise, return the shortest distance between the source actor and the
        // current actor
        // We divide by two because when going along the path, there are also movies
        // that are counted.  In terms of output, there will always be 1 movie paired
        // with 1 actor as we connect the two.  Therefore, we divide by 2 to account for
        // the movies.
        return bfs.distTo(sinkindex) / 2;
    }

    public Stack<Integer> graphPath(String sink) {
        // If the actor isn't in the graph, output "error" message
        if (!symgraph.contains(sink)) {
            StdOut.println("Actor doesn't exist in the graph.");
            return null;
        }
        // Find the index of the current actor (sink)
        int sinkindex = symgraph.indexOf(sink);

        // If there is no path from the source actor to the current actor (sink),
        // return null
        if (!bfs.hasPathTo(sinkindex)) {
            return null;
        }

        // Initialize a Stack path between actors
        this.actorpath = new Stack<Integer>();

        // If there is a path between the source actor and the given actor...
        if (bfs.hasPathTo(sinkindex)) {
            // For each vertex in the path between the source actor and the given actor...
            for (int v : bfs.pathTo(sinkindex)) {
                // Push the vertex onto the stack
                actorpath.push(v);
            }
        }

        // Return the path between the actors
        return actorpath;
    }

    public void printPath(Stack<Integer> path) {
        // Print the path between the two actors
        StdOut.println("Starting path:");
        while (!actorpath.isEmpty()) {
            StdOut.println(actorpath.pop());
            StdOut.println("Next Path:");
        }
        StdOut.println("Done.");
    }

    public static void main(String[] args) {
        // Accept a filename (file), a delimiter, a source vertex and another
        // actor to test the bacon number for as command-line arguments
        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];
        String testactor = args[3];

        // Initialize a new DegreesOfSeparationBFS object
        DegreesOfSeparationBFS degofsep = new DegreesOfSeparationBFS(filename, delimiter, source);

        // Initialize an int to the bacon number of the test actor
        int bn = degofsep.baconNumber(testactor);
        // Test the bacon number
        StdOut.println("'Bacon' number is: " + bn);

        // Calculate the path between actors
        degofsep.graphPath(testactor);
        // Print the path
        degofsep.printPath(degofsep.actorpath);
    }
}
