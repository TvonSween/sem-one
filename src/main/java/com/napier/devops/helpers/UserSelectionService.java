package com.napier.devops.helpers;

import com.napier.devops.Reports;
import com.napier.devops.reports.CountriesList;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Manages the user selection and proceed the request to the mapped method
 * Methods, is a map of question id - and report class.
 */
public class UserSelectionService {
    private final Map<Integer, IUserSelectionProcessor> processors = new HashMap<>();

    /**
     * Mapping the main questionId by pushing key-value data to the processors Map
     */
    public UserSelectionService() {
        processors.put(1, new CountriesList("PopulationOfCountries", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put(4, new CountriesList("TopCountriesPerPopulation", QueryConstants.COUNTRIES_POPULATION_DESC));
    }
    /**
     * Extract the results to a .csv file.
     * @param questionId The questionId which is the question id related to assessment.
     * @param report The instance of the Report.
     * @param stmt The sql statement that will be executed in the reports.
     * @param userInput The extra user input needed for specific questions.
     */
    public void processUserSelection(Integer questionId, Reports report, Statement stmt, Integer userInput) throws SQLException {
        IUserSelectionProcessor processor = processors.get(questionId);
        if (processor != null) {
            processor.processUserSelection(report, stmt, userInput);
        } else {
            throw new IllegalArgumentException("Unsupported questionId: " + questionId);
        }
    }
    /**
     * Get the user selection for the question id.
     * @return Map of question and userInput - N
     */
    public Map<String, Integer> getUserInput()
    {
        // Set of question ids related to questions with extra user input
        final Set<Integer> questionsExtraUserInput = Set.of(
                4,5,6
        );
        int questionSelected = 0;
        int userInput = 0;

        Scanner keyboard = new Scanner(System.in);

        System.out.println("What type of report do you want to export?\n");
        System.out.println("1. All the countries in the world organised by largest population to smallest");
        System.out.println("2. All the countries in a continent organised by largest population to smallest");
        System.out.println("3. All the countries in a region organised by largest population to smallest");
        System.out.println("4. The top N populated countries in the world where N is provided by the user.");
        // Add the rest of the questions
        System.out.println("\n");

        questionSelected = read_range(keyboard, 1, 32);

        // Get the response of the extra question - N
        if(questionsExtraUserInput.contains(questionSelected))
        {
            userInput = getN();
        }
        return Map.of(
            "question", questionSelected,
            "userInput", userInput
        );
    }

    public boolean shouldSelect() {
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Do you want to see other data? (Y/N)");
            return keyboard.nextLine().equalsIgnoreCase("y");
        }
    }

    private static int getN()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many rows do you want to show? -upper limit-");
        return read_range(keyboard, 1, 100);
    }

    /**
     * Get the user selection and check if the value is between the range.
     * @return the user input
     */
    private static int read_range (Scanner scanner, int low, int high) {
        int value;
        System.out.print("Please enter a value between " + low + " and " + high + ": ");
        value = scanner.nextInt();
        while (value < low || value > high) {
            System.out.print("Please enter a value between " + low + " and " + high + ": ");
            value = scanner.nextInt();
        }
        return value;
    }

}
