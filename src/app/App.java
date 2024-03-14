//Dependencys
package app;

import java.util.Locale;

import view.Prompt;

//-----------------------------------------------------
/*
 * @File.:      TrabalhoPratico01 - Graphs
 * @Author.:    Pedro Carbonaro
 * @Version.:   1.0
 * @Date.:      2024-03-14
 * @Copyright.: Copyright (c) 2024
*/
//-----------------------------------------------------

/*
 * Main class for Application
 * 
 * @brief.: Just initiate the application
*/
public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        new Prompt().startPrompt();
    }
}
