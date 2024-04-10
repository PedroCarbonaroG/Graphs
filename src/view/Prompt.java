//Dependencys
package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import entities.GraphGenerator;

import util.Utility;

/*
 * Prompt class, User iteration layer
 * 
 * @brief.: Deal with all the user's needs, just taking the inputs 
 * and taking them to the backend and then returning the results.
*/
public class Prompt {
    
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

    private static Scanner sc = new Scanner(System.in, "UTF-8");

    public void startPrompt() {

        try {

            //---------------------------------------------------------------------------------------------------------//

            //Cleaning the prompt last messages
            Utility.clearScreen();

            //Creating all files for graphs and starting them with random graphs with x vertexes.
            graph100File = new File(GRAPH100_FILE_PATH);
            graph100File.createNewFile();

            graph1KFile = new File(GRAPH1K_FILE_PATH);
            graph1KFile.createNewFile();

            graph10KFile = new File(GRAPH10K_FILE_PATH);
            graph10KFile.createNewFile();

            graph100KFile = new File(GRAPH100K_FILE_PATH);
            graph100KFile.createNewFile();

            GraphGenerator.createGraph(graph100File, 100);
            GraphGenerator.createGraph(graph1KFile, 100);
            GraphGenerator.createGraph(graph10KFile, 100);
            GraphGenerator.createGraph(graph100KFile, 100);
            
            //---------------------------------------------------------------------------------------------------------//
            
            short userChoice = 1;

            do {
            
                System.out.println("================================== HUB ==================================");
                System.out.println("How graph file will be tested, Options:");
                System.out.println("Note: All three algorithms'll be tested and them show the answers.");
                System.out.println("(1) -> Graph with 100 vertexes.");
                System.out.println("(2) -> Graph with 1.000 vertexes.");
                System.out.println("(3) -> Graph with 10.000 vertexes.");
                System.out.println("(4) -> Graph with 100.000 vertexes.");
                System.out.println("(5) -> All graphs above.");
                System.out.println("");
                System.out.println("(0) -> Finish program.");
                System.out.println("================================== HUB ==================================");
                System.out.print("Answer: ");
                userChoice = sc.nextShort();

                while (!validChoice(userChoice, 6)) {
                    System.out.println("\nInvalid choice, try again!");
                    System.out.print("New Choice: "); userChoice = sc.nextShort();
                }

                //---------------------------------------------------------------------------------------------------------//

                Utility.clearScreen();

                //Algorithms here
                System.out.println("Algorithm anwsers comes here!");

                //---------------------------------------------------------------------------------------------------------//

                if (userChoice != 0) {
                    System.out.print("\nPress enter to finish the overview!");
                    sc.nextLine();
                    sc.nextLine();
                    Utility.clearScreen();
                }

            } while (validChoice(userChoice));
        }

        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }

        finally {

            if (sc != null) { sc.close(); }

            GraphGenerator.deleteGraph(graph100File);
            GraphGenerator.deleteGraph(graph1KFile);
            GraphGenerator.deleteGraph(graph10KFile);
            GraphGenerator.deleteGraph(graph100KFile);

            Utility.clearScreen();
            System.out.println("Program Finished!");
        }
    }

    /**
     * @brief Used to check if user choice was valid
     * 
     * @param int userChoice used to check if that option
     * is valid for the application.
     * @param int limit used to set the limit
    */
    private boolean validChoice(int userChoice, int limit) { return userChoice >= 0 && userChoice < limit; }

    /**
     * @brief Used for check if user wants to finish
     * the program.
     * 
     * @param int userChoice used to check if that option
     * is valid for the application.
    */
    private boolean validChoice(int userChoice) { return userChoice == 0 ? false : true; }
}
