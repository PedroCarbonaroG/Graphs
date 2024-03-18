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

    private static final Random random = new Random();

    /**
     * @brief Create and fill with random values
     * all files used in system for manipulate 
     * graphs
     * @throws IOException if something goes wrong 
     * opening or manipulating the file
    */
    public static void createGraphs() throws IOException {
        graph100File = new File(GRAPH100_FILE_PATH);
        graph100File.createNewFile();
        createRandomGraph(graph100File, 100, 200);

        graph1KFile = new File(GRAPH1K_FILE_PATH);
        graph1KFile.createNewFile();
        createRandomGraph(graph1KFile, 1000, 2000);

        graph10KFile = new File(GRAPH10K_FILE_PATH);
        graph10KFile.createNewFile();
        createRandomGraph(graph10KFile, 10000, 20000);
        
        graph100KFile = new File(GRAPH100K_FILE_PATH);
        graph100KFile.createNewFile();
        createRandomGraph(graph100KFile, 100000, 200000);
    }
    /**
     * @brief Method for create an random graph 
     * and save the outs in a file
     * 
     * @param source File that will have the generated graph
     * @param vertexes maximum number of vertices
     * @param edges maximum number of edges
     * @throws IOException if something goes wrong with file manipulation
     */
    private static void createRandomGraph(File file, int vertexes, int edges) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        List[] adjacencyList = new List[vertexes];
        
        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new List();
        }

        int numEdges = (vertexes * (vertexes - 1)) / 2;
        int numEdgesPerVertex = numEdges / vertexes;
        int edgesPreenched = 0;

        for (int i = 0, index = 1; i < adjacencyList.length; i++, index++) {
            adjacencyList[i].add(index);

            for (int j = 0; j < random.nextInt(1, numEdgesPerVertex); j++) {
                
                int ale2 = random.nextInt(1, vertexes);
                
                while (adjacencyList[i].contains(ale2)) {
                    ale2 = random.nextInt(1, vertexes);
                }

                adjacencyList[i].add(ale2);
                edgesPreenched++;
            }
        }

        while (edgesPreenched < numEdges) {

            int ale1 = random.nextInt(1, vertexes);
            int ale2 = random.nextInt(1, vertexes);

            while (adjacencyList[ale1].contains(ale2)) {
                ale2 = random.nextInt(1, vertexes);
            }

            adjacencyList[ale1].add(ale2);
            edgesPreenched++;
        }

        for (List list : adjacencyList) {
            System.out.println(list);
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
