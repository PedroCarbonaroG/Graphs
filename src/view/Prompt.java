//Dependencys
package view;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import util.Utility;

/*
 * Prompt class, User iteration layer
 * 
 * @brief.: Deal with all the user's needs, just taking the inputs 
 * and taking them to the backend and then returning the results.
*/
public class Prompt {
    
    private Scanner userSc = new Scanner(System.in, "UTF-8");
    private Scanner fileSc;

    public void startPrompt() {

        try {

            Utility.clearScreen();
            Utility.createGraphFiles();

            System.out.println("================================== HUB ==================================");
            System.out.println("How graph file will be tested, Options:");
            System.out.println("(1) -> Graph with 100 vertexes.");
            System.out.println("(2) -> Graph with 1.000 vertexes.");
            System.out.println("(3) -> Graph with 10.000 vertexes.");
            System.out.println("(4) -> Graph with 100.000 vertexes.");
            System.out.println("================================== HUB ==================================");
            System.out.print("Answer: "); short userChoice = userSc.nextShort();

        }

        catch(FileNotFoundException e) { e.printStackTrace(); }
        catch(IOException e) { e.printStackTrace(); }

        finally {
            Utility.deleteGraphFiles(); 
            if (fileSc != null && userSc != null) { 
                fileSc.close(); 
                userSc.close();
            }

            // Include at the end of the project->
            // Utility.clearScreen();
            // System.out.println("Program Finished!");
        }
    }
}
