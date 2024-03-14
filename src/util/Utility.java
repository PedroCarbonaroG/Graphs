package util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

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

    /*
     * Additional classes for creating graphs
    */
    private static Random rd = new Random();

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
        createRandomGraph(graph100File, 100);

        graph1KFile = new File(GRAPH1K_FILE_PATH);
        graph1KFile.createNewFile();
        createRandomGraph(graph1KFile, 1000);

        graph10KFile = new File(GRAPH10K_FILE_PATH);
        graph10KFile.createNewFile();
        createRandomGraph(graph10KFile, 10000);
        
        graph100KFile = new File(GRAPH100K_FILE_PATH);
        graph100KFile.createNewFile();
        createRandomGraph(graph100KFile, 100000);
    }
    /**
     * @brief.: Method for create an random graph 
     * and save the outs in a file
     * 
     * @param source File that will have the generated graph
     * @param limit maximum number of vertices
     */
    private static void createRandomGraph(File source, int limit) {
        //To implementate an algoritmn that creates a random graph with limit of vertexes
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
