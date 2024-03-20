package util;

public class Utility {

    // ==========================================================================================================================================
    // Utility Methods

    /**
     * @brief Clear the screen every interation 
     * with user, good for cleaner usage of system.
    */
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
