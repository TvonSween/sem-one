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
        DBconnector db;
        Connection con;
        // If args not provided, the default port+url
        String databaseUrl = args.length < 1 ? "localhost:33060" : args[0];
        try {
            // Connect to the db.
            db = new DBconnector();
            // Create an SQL connection
            con = db.connect(databaseUrl, 0);
            if (con == null) {
                throw new RuntimeException("Failed to connect to database");
            }
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
            db.disconnect(con);
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred " + e.getMessage());
        }
    }

}
