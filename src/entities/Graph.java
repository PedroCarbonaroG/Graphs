//Dependencys
package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;

import util.List;

public class Graph {

    /**
     * Area for processing test files and correct instantiation of them,
     * pre-established graph size concepts
    */
    private static final String GRAPH100_FILE_PATH = "./lib/Graph100.txt";
    private static final String GRAPH1K_FILE_PATH = "./lib/Graph1K.txt";
    private static final String GRAPH10K_FILE_PATH = "./lib/Graph10K.txt";
    private static final String GRAPH100K_FILE_PATH = "./lib/Graph100K.txt";
    public static File graph100File;
    public static File graph1KFile;
    public static File graph10KFile;
    public static File graph100KFile;

    private static Random random = new Random();

    /*
     * Creating a static block when classe is instantiated
     * to create all files that'll be used on system.
    */
    static {
        try {
            graph100File = new File(GRAPH100_FILE_PATH);
            graph100File.createNewFile();

            graph1KFile = new File(GRAPH1K_FILE_PATH);
            graph1KFile.createNewFile();

            graph10KFile = new File(GRAPH10K_FILE_PATH);
            graph10KFile.createNewFile();

            graph100KFile = new File(GRAPH100K_FILE_PATH);
            graph100KFile.createNewFile();
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * @brief Create and fill with random values
     * all files used in system for manipulate 
     * graphs
     * @throws IOException if something goes wrong 
     * opening or manipulating the file
    */
    public static void createGraphs() throws IOException {
        createRandomGraph(graph100File, 100);
        createRandomGraph(graph1KFile, 1000);
        createRandomGraph(graph10KFile, 10000);
        createRandomGraph(graph100KFile, 100000);
    }
    /**
     * @brief Method for create an random graph 
     * and save the outs in a file
     * 
     * @param source File that will have the generated graph
     * @param vertexes maximum number of vertices
     * @throws IOException if something goes wrong with file manipulation or opening
     */
    private static void createRandomGraph(File source, int vertexes) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(source));
        List[] adjacencyList = new List[vertexes];

        for (int i = 0; i < adjacencyList.length; i++) { adjacencyList[i] = new List(); }

        /*
         * For didactic purposes i'll do each step of
         * initiation of all variables.
         * 
         * Considerate that graph will be ever connected
         * so it components'll be always one.
         * 
         * Here the focus is not on efficiency or well-structured code.
        */

        //Mathematic expression to calculate minimun adges in graph
        // G = (V, E) n->Vertexes k->Components, minEdges = n-k;
        int minEdges = vertexes - 1;

        //Mathematic expression to calculate maximum adges in graph
        // G = (V, E) n->Vertexes k->Components, maxEdges = ((n-k) * (n-k+1)) / 2;
        // FOR EFFICIENCY REASONS I'LL DIVIDE FOR 20.
        int maxEdges = ((vertexes - 1) * (vertexes - 1 + 1)) / 20;

        // Generating the number of edges for graph
        int edges = random.nextInt(minEdges, maxEdges);

        //Mathematic expression to calculate maximum adges for a vertex
        // G = (V, E) v->Vertexes e->Edges, maxEdgesPerVertex = (2*e) / v;
        int edgesPerVertex = (2 * edges) / vertexes;

        int edgesPreenched = 0;

        for (int i = 0, j = 1; i < adjacencyList.length; i++, j++) {
            adjacencyList[i].add(j);

            for (int k = 0; k < random.nextInt(1, edgesPerVertex); k++) {
                int randomVertex;
                do {
                    randomVertex = random.nextInt(1, vertexes);
                } while (adjacencyList[i].contains(randomVertex));

                adjacencyList[i].add(randomVertex);
                edgesPreenched++;
            }
        }

        while (edgesPreenched < edges) {
            int randomSourceVertex = random.nextInt(1, vertexes);
            int randomTargetVertex = random.nextInt(1, vertexes);

            do {
                randomTargetVertex = random.nextInt(1, vertexes);
            }
            while (adjacencyList[randomSourceVertex].contains(randomTargetVertex));

            adjacencyList[randomSourceVertex].add(randomTargetVertex);
            edgesPreenched++;
        }

        writer.write(vertexes + " " + edges);
        writer.newLine();
        for (List list : adjacencyList) {
            String line = list.toString();
            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * @brief Delete all files used in system 
     * for manipulate graphs
    */
    public static void deleteGraphs() {
        graph100File.delete();
        graph1KFile.delete();
        graph10KFile.delete();
        graph100KFile.delete();
    }
}
