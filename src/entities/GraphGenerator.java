//Dependencys
package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;

import algorithms.GraphAlgorithms;

import util.List;

public abstract class GraphGenerator extends GraphAlgorithms {

    /**
     * @brief Create and fill with random values
     * all files used in system for manipulate 
     * graphs
     * @throws IOException if something goes wrong 
     * opening or manipulating the file
    */
    public static void createGraph(File target, int vertexes) throws IOException {
        createRandomGraph(target, vertexes);
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

        Random random = new Random();
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
         * Here the focus isn't on efficiency or well-structured code.
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

        /*
         * Describe the method here
        */
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

        /*
         * Describe the method here
        */
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

        /*
         * Writting all graph into target file
        */
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
    public static void deleteGraph(File target) {
        target.delete();
    }
}
