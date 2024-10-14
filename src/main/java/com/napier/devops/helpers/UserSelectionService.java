package com.napier.devops.helpers;

import com.napier.devops.Reports;
import com.napier.devops.reports.CountriesList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * The {@code UserSelectionService} class manages user interactions for selecting
 * report types and processing their requests. It maps question IDs to their
 * corresponding report processing methods and facilitates user input.
 */
public class UserSelectionService {

    /**
     * A map that associates question IDs with their respective report processors.
     */
    private final Map<Integer, IUserSelectionProcessor> processors = new HashMap<>();

    /**
     * Constructs a {@code UserSelectionService} instance and initializes the
     * mapping of question IDs to report processors.
     */
    public UserSelectionService() {
        processors.put(1, new CountriesList("PopulationOfCountries", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put(2, new CountriesList("PopulationOfContinents", QueryConstants.CONTINENTS_POPULATION_DESC));
        processors.put(4, new CountriesList("TopCountriesPerPopulation", QueryConstants.COUNTRIES_POPULATION_DESC));
    }

    /**
     * Processes the user selection based on the given question ID.
     *
     * @param questionId The ID of the question corresponding to the user's selection.
     * @param report     The instance of the {@link Reports} class used to generate the report.
     * @param con       The SQL connection to execute queries against the database.
     * @param userInput  The additional user input required for specific questions.
     *
     * @throws SQLException If an error occurs while executing the SQL query.
     * @throws IllegalArgumentException If the provided question ID is not supported.
     */
    public void processUserSelection(Integer questionId, Reports report, Connection con, Integer userInput) throws SQLException {
        IUserSelectionProcessor processor = processors.get(questionId);
        if (processor != null) {
            processor.processUserSelection(report, con, userInput);
        } else {
            throw new IllegalArgumentException("Unsupported questionId: " + questionId);
        }
    }

    /**
     * Prompts the user to select a question ID and returns the user's selection along
     * with any additional input needed.
     *
     * @return A {@link Map} containing the selected question ID and any extra user input.
     */
    public Map<String, Integer> getUserInput() {
        // Set of question IDs that require extra user input
        final Set<Integer> questionsExtraUserInput = Set.of(2, 4, 5, 6);
        int questionSelected = 0;
        int userInput = 0;

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please select the type of report you would like to export:\n");
        System.out.println("1. All the countries in the world organised by largest population to smallest");
        System.out.println("2. All the countries in a continent organised by largest population to smallest");
        System.out.println("3. All the countries in a region organised by largest population to smallest");
        System.out.println("4. The top N populated countries in the world where N is provided by the user.");
        // Add the rest of the questions
        System.out.println("\n");

        try {
            questionSelected = read_range(keyboard, 1, 32);
            // Get the response for the extra question - N
            if (questionsExtraUserInput.contains(questionSelected)) {
                if (questionSelected == 2) {
                    userInput = getContinent();
                }
                if (questionSelected == 4) {
                    userInput = getN();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.\n");
        }

        return Map.of(
                "question", questionSelected,
                "userInput", userInput
        );
    }

    /**
     * Asks the user if they want to see more data.
     *
     * @return {@code true} if the user wants to continue, {@code false} otherwise.
     */
    public boolean shouldSelect() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Would you like to view additional data? (Y/N)");
        try {
            return keyboard.nextLine().equalsIgnoreCase("y");
        } catch (InputMismatchException e) {
            return false;
        }
    }

    /**
     * Prompts the user for the number of rows they wish to display.
     *
     * @return The number of rows specified by the user.
     */
    private static int getN() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please specify the maximum number of rows to display (upper limit):");
        return read_range(keyboard, 1, 100);
    }

    /**
     * Prompts the user for the continent they are interested in.
     *
     * @return The id of the continent specified by the user.
     */
    private static int getContinent() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please specify the number of the continent you're interested in:");
        System.out.println("1: Asia");
        System.out.println("2: Africa");
        System.out.println("3: Europe");
        System.out.println("4: North America");
        System.out.println("5: South America");
        System.out.println("6: Oceania");
        System.out.println("7: Antarctica");
        return read_range(keyboard, 1, 7);
    }

    /**
     * Reads a number from the user within a specified range.
     *
     * @param scanner The {@link Scanner} instance for reading user input.
     * @param low     The lower bound of the acceptable range.
     * @param high    The upper bound of the acceptable range.
     * @return The validated user input within the specified range.
     */
    private static int read_range(Scanner scanner, int low, int high) {
        int value = low - 1;
        while (value < low || value > high) {
            System.out.print("Please enter a value between " + low + " and " + high + ": ");
            value = scanner.nextInt();
        }
        return value;
    }
}
