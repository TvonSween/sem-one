package com.napier.devops;

import com.napier.devops.helpers.UserSelectionService;

import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    /**
     * The Main app.
     */
    public static void main(String[] args)
    {
        boolean shouldQuestion = true;
        // If args not provided, the default port+url
        String databaseUrl = args.length < 1 ? "localhost:3306" : args[0];
        try {
             // Connect to the db.
            Connection con = DBconnector.connect(databaseUrl, 10000);
            if (con == null) {
                System.exit(0);
            }
            // Create an SQL statement
            // Create an instance for the reports
            Reports report = new Reports();
            // Get user inputs
            UserSelectionService userSelectionService = new UserSelectionService();
            // Prompt the user till he presses N, n
            while (shouldQuestion) {
                Map<String, Integer> userInputs = userSelectionService.getUserInput();
                // Process the user selections and extract report and query
                userSelectionService.processUserSelection(userInputs.get("question"), report, con, userInputs.get("userInput"));
                shouldQuestion = userSelectionService.shouldSelect();
            }
            // Disconnect form db
            DBconnector.disconnect();
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred " + e.getMessage());
        }
    }

}
