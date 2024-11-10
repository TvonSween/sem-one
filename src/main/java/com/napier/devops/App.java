package com.napier.devops;

import com.napier.devops.helpers.UserSelectionService;

import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code App} class serves as the entry point for the application.
 * It connects to a database, interacts with the user for input, and generates
 * reports based on the user's selections.
 */
public class App {

    /**
     * A logger instance used to log messages for the {@code App} class.
     */
    private static final Logger logger = Logger.getLogger(App.class.getName());

    /**
     * The main method that launches the application.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Establishes a connection to the database using the {@link DBconnector}.</li>
     *   <li>Uses {@link UserSelectionService} to prompt the user for input.</li>
     *   <li>Processes the user selections to generate and extract reports.</li>
     *   <li>Repeats the process until the user opts to stop.</li>
     *   <li>Finally, disconnects from the database.</li>
     * </ol>
     *
     * @param args Command line arguments. The first argument is used as the database URL.
     *             If not provided, the default URL is "localhost:33060".
     *
     * <p>Example usage:</p>
     * <pre>
     * {@code
     * java -jar myapp.jar localhost:3306
     * }
     * </pre>
     */
    public static void main(String[] args)
    {
        boolean shouldQuestion = true;
        DBconnector db;
        Connection con;

        // If args not provided, use the default database URL
        String databaseUrl = args.length < 1 ? "localhost:33060" : args[0];

        try {
            // Connect to the database
            db = new DBconnector();
            con = db.connect(databaseUrl, 0);
            if (con == null) {
                throw new RuntimeException("Failed to connect to database");
            }

            // Create an instance of the Reports class for generating reports
            Reports report = new Reports();

            // Continuously prompt the user until they choose to exit (N/n)
            while (shouldQuestion) {
                // Create an instance of the UserSelectionService to interact with the user
                UserSelectionService userSelectionService = new UserSelectionService();
                // Get user inputs
                Map<String, String> userInputs = userSelectionService.getUserInput();

                // Process the user selection and extract the report
                userSelectionService.processUserSelection(
                        userInputs.get("question"), report, con, userInputs.get("userInput"), userInputs.get("limit")
                );

                // Ask if the user wants to continue or exit
                shouldQuestion = userSelectionService.shouldSelect();
            }

            // Disconnect from the database
            db.disconnect(con);
        }
        catch (Exception e) {
            // Log any exceptions that occur
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
        }
    }
}
