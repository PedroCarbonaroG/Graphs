package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Utility {
    
    /**
     * Area for processing test files and correct instantiation of them
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

    // ==========================================================================================================================================
    // Utility Methods

    /**
     * @brief.: Create and fill with random values
     * all files used in system for manipulate 
     * graphs
     * @throws IOException if something goes wrong 
     * opening or manipulating the file
    */
    public static void createGraphFiles() throws IOException {
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
     * @brief.: Method for create an random graph 
     * and save the outs in a file
     * 
     * @param source File that will have the generated graph
     * @param vertexes maximum number of vertices
     * @param edges maximum number of edges
     * @throws IOException if something goes wrong with file manipulation
     */
    private static void createRandomGraph(File file, int vertexes, int edges) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(vertexes + " " + edges);
        writer.newLine();

        Set<String> generatedEdges = new HashSet<>();

        for (int i = 1; i <= vertexes; i++) {
            int target = random.nextInt(vertexes) + 1;
            while (target == i) {
                target = random.nextInt(vertexes) + 1;
            }
            generatedEdges.add(i + " " + target);
        }

        while (generatedEdges.size() < edges) {
            int entry = random.nextInt(vertexes) + 1;
            int target = random.nextInt(vertexes) + 1;
            if (entry != target) {
                generatedEdges.add(entry + " " + target);
            }
        }

        List<String> sortedEdges = generatedEdges.stream()
                .sorted((a, b) -> {
                    int vertexA1 = Integer.parseInt(a.split(" ")[0]);
                    int vertexB1 = Integer.parseInt(b.split(" ")[0]);
                    return Integer.compare(vertexA1, vertexB1);
                })
        .collect(Collectors.toList());

        for (String edge : sortedEdges) {
            writer.write(edge);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * @brief.: Delete all files used in system 
     * for manipulate graphs
    */
    public static void deleteGraphFiles() {
        graph100File.delete();
        graph1KFile.delete();
        graph10KFile.delete();
        graph100KFile.delete();
    }

    /**
     * @brief.: ClearScreen method, clear the
     * screen every interation with user, good
     * for clean usage of system
    */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
