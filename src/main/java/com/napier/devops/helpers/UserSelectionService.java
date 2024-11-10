package com.napier.devops.helpers;

import com.napier.devops.Reports;
import com.napier.devops.reports.*;

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
    private final Map<String, IUserSelectionProcessor> processors = new HashMap<>();

    /**
     * Constructs a {@code UserSelectionService} instance and initializes the
     * mapping of question IDs to report processors.
     */
    public UserSelectionService() {
        processors.put("1", new CountriesList("PopulationOfCountries", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put("2", new CountriesList("PopulationOfContinentsByCountry", QueryConstants.CONTINENT_POPULATION_DESC));
        processors.put("3", new CountriesList("PopulationOfRegionsByCountry", QueryConstants.REGION_POPULATION_DESC));
        processors.put("4", new CountriesList("TopCountriesPerPopulation", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put("5", new CountriesList("PopulationOfContinentsByCountryWithLimit", QueryConstants.CONTINENT_POPULATION_DESC));
        processors.put("6", new CountriesList("PopulationOfRegionsByCountryWithLimit", QueryConstants.REGION_POPULATION_DESC));
        processors.put("7", new CitiesList( "PopulationOfCities", QueryConstants.CITY_POPULATION_DESC));
        processors.put("8", new CitiesList( "CitiesByContinent", QueryConstants.CITY_POPULATION_CONTINENT_DESC));
        processors.put("9", new CitiesList( "CitiesByRegion", QueryConstants.CITY_POPULATION_REGION_DESC));
        processors.put("10", new CitiesList( "CitiesByCountry", QueryConstants.CITY_POPULATION_COUNTRY_DESC));
        processors.put("11", new CitiesList( "CitiesByDistrict", QueryConstants.CITY_POPULATION_DISTRICT_DESC));
        processors.put("12", new CitiesList( "TopCitiesPerPopulation", QueryConstants.CITY_POPULATION_DESC));
        processors.put("13", new CitiesList( "TopCitiesByContinent", QueryConstants.CITY_POPULATION_CONTINENT_DESC));
        processors.put("14", new CitiesList( "TopCitiesByRegion", QueryConstants.CITY_POPULATION_REGION_DESC));
        processors.put("15", new CitiesList( "TopCitiesByCountry", QueryConstants.CITY_POPULATION_COUNTRY_DESC));
        processors.put("16", new CitiesList( "TopCitiesByDistrict", QueryConstants.CITY_POPULATION_DISTRICT_DESC));
        processors.put("17", new CapitalCitiesList( "PopulationOfCapitalCities", QueryConstants.CITY_CAPITALS_POPULATION_DESC));
        processors.put("18", new CapitalCitiesList( "CapitalCitiesInContinentByPopulation", QueryConstants.CITY_CAPITALS_CONTINENT_POPULATION_DESC));
        processors.put("19", new CapitalCitiesList( "CapitalCitiesInRegionByPopulation", QueryConstants.CITY_CAPITALS_REGION_POPULATION_DESC));
        processors.put("20", new CapitalCitiesList( "TopCapitalCitiesPerPopulation", QueryConstants.CITY_CAPITALS_POPULATION_DESC));
        processors.put("21", new CapitalCitiesList( "CapitalCitiesInContinentWithLimit", QueryConstants.CITY_CAPITALS_CONTINENT_POPULATION_DESC));
        processors.put("22", new CapitalCitiesList( "CapitalCitiesInRegionWithLimit", QueryConstants.CITY_CAPITALS_REGION_POPULATION_DESC));
        processors.put("23", new PopulationInCitiesList( "PopulationLivingNotLivingInCitiesPerContinent", QueryConstants.CONTINENT_LIVING_NOTLIVING_IN_CITY_DESC));
        processors.put("24", new PopulationInCitiesList("PopulationLivingNotLivingInCitiesPerRegion", QueryConstants.REGION_LIVING_NOTLIVING_IN_CITY_DESC));
        processors.put("25", new PopulationInCitiesList("PopulationLivingNotLivingInCitiesPerCountry", QueryConstants.COUNTRY_LIVING_NOTLIVING_IN_CITY_DESC));
        processors.put("26", new PopulationList("TotalPopulationInWorld", QueryConstants.TOTAL_POPULATION_WORLD));
        processors.put("27", new PopulationList("TotalPopulationInContinent", QueryConstants.TOTAL_POPULATION_CONTINENT));
        processors.put("28", new PopulationList("TotalPopulationInRegion", QueryConstants.TOTAL_POPULATION_REGION));
        processors.put("29", new PopulationList("TotalPopulationInCountry", QueryConstants.TOTAL_POPULATION_COUNTRY));
        processors.put("30", new PopulationList("TotalPopulationInDistrict", QueryConstants.TOTAL_POPULATION_DISTRICT));
        processors.put("31", new PopulationList("TotalPopulationInCity", QueryConstants.TOTAL_POPULATION_CITY));
        processors.put("32", new LanguageWorldList("LanguageWorldPerPopulation", QueryConstants.LANGUAGE_PERCENTAGE_WORLD));
    }

    /**
     * Processes the user selection based on the given question ID.
     *
     * @param questionId The ID of the question corresponding to the user's selection.
     * @param report     The instance of the {@link Reports} class used to generate the report.
     * @param con        The SQL connection to execute queries against the database.
     * @param userInput  The additional user input required for specific questions.
     * @param limit      The limit on the number of rows for queries that require it.
     *
     * @throws SQLException If an error occurs while executing the SQL query.
     * @throws IllegalArgumentException If the provided question ID is not supported.
     */
    public void processUserSelection(String questionId, Reports report, Connection con, String userInput, String limit) throws SQLException {
        IUserSelectionProcessor processor = processors.get(questionId);
        if (processor != null) {
            processor.processUserSelection(report, con, userInput, limit);
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
    public Map<String, String> getUserInput() {
        // Set of question IDs that require extra user input
        final Set<String> questionsWithoutAnyUserInput = Set.of("1", "7", "17", "20","23","24","25","26", "32");
        final Set<String> questionsWithLimit = Set.of("4","5","6","12","13","14","15","16","20", "21","22");
        final Set<String> questionsForContinentCategory = Set.of("2","5","8","13", "18", "21","27");
        final Map<String, String> categories = new HashMap<>() {
            {
                put("3", "region");
                put("6", "region");
                put("9", "region");
                put("10", "country");
                put("11", "district");
                put("14", "region");
                put("15", "country");
                put("16", "district");
                put("19", "region");
                put("22", "region");
                put("28", "region");
                put("29", "country");
                put("30", "district");
                put("31", "city");
            }
        };
        String questionSelected = "";
        String userInput = "";
        String limit = "";

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please select the type of report you would like to export:\n");
        System.out.println("1. All the countries in the world organised by largest population to smallest");
        System.out.println("2. All the countries in a continent organised by largest population to smallest");
        System.out.println("3. All the countries in a region organised by largest population to smallest");
        System.out.println("4. The top N populated countries in the world where N is provided by the user.");
        System.out.println("5. The top N populated countries in a continent where N is provided by the user.");
        System.out.println("6. The top N populated countries in a region where N is provided by the user.");
        System.out.println("7. All cities in the world organised by largest population to smallest");
        System.out.println("8. All the cities in a continent organised by largest population to smallest");
        System.out.println("9. All the cities in a region organised by largest population to smallest");
        System.out.println("10. All the cities in a country organised by largest population to smallest");
        System.out.println("11. All the cities in a district organised by largest population to smallest");
        System.out.println("12. The top N populated cities in the world where N is provided by the user.");
        System.out.println("13. The top N populated cities in a continent where N is provided by the user.");
        System.out.println("14. The top N populated cities in a region where N is provided by the user.");
        System.out.println("15. The top N populated cities in a country where N is provided by the user.");
        System.out.println("16. The top N populated cities in a district where N is provided by the user.");
        System.out.println("17. All capital cities in the world organised by largest population to smallest");
        System.out.println("18. All capital cities in a continent organised by largest population to smallest.");
        System.out.println("19. All capital cities in a region organised by largest population to smallest.");
        System.out.println("20. The top N populated capital cities in the world where N is provided by the user.");
        System.out.println("21. The top N populated capital cities in a continent where N is provided by the user.");
        System.out.println("22. The top N populated capital cities in a region where N is provided by the user.");
        System.out.println("23. The population of people, people living in cities, and people not living in cities in each continent.");
        System.out.println("24. The population of people, people living in cities, and people not living in cities in each region.");
        System.out.println("25. The population of people, people living in cities, and people not living in cities in each country.");
        System.out.println("26. The population of the world.");
        System.out.println("27. The population of a continent.");
        System.out.println("28. The population of a region.");
        System.out.println("29. The population of a country.");
        System.out.println("30. The population of a district.");
        System.out.println("31. The population of a city.");
        System.out.println("32. The number of people who speak 'Chinese', 'English', 'Hindi', 'Spanish', 'Arabic' from greatest number to smallest, including the percentage of the world population.");

        System.out.println("\n");

        try {
            questionSelected = read_range(keyboard, 1, 32);
            if (!questionsWithoutAnyUserInput.contains(questionSelected)) {
                if(questionsForContinentCategory.contains(questionSelected)) {
                    userInput = getContinent();
                } else {
                    userInput = getInput(categories.get(questionSelected));
                }
            }
            // Get the response for the extra question - N
            if (questionsWithLimit.contains(questionSelected)) {
                limit = getN();
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.\n");
        }

        return Map.of(
                "question", questionSelected,
                "userInput", userInput,
                "limit", limit
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
    private static String getN() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please specify the maximum number of rows to display (upper limit):");
        return read_range(keyboard, 1, 100);
    }

    /**
     * Prompts the user to select a continent from a predefined list.
     *
     * @return The name of the selected continent.
     */
    private String getContinent() {
        String[] continents = {"Asia", "Africa", "Europe", "North America", "South America", "Oceania", "Antarctica"};
        System.out.println("Please specify the number of the continent you're interested in:");
        Scanner keyboard = new Scanner(System.in);
        for (int i = 0; i < continents.length; i++) System.out.println((i+1) + ": " + continents[i]);
        return continents[Integer.parseInt(read_range(keyboard, 1, 7))-1];
    }

    /**
     * Reads a number from the user within a specified range.
     *
     * @param scanner The {@link Scanner} instance for reading user input.
     * @param low     The lower bound of the acceptable range.
     * @param high    The upper bound of the acceptable range.
     * @return The validated user input within the specified range.
     */
    private static String read_range(Scanner scanner, int low, int high) {
        int value = low - 1;
        while (value < low || value > high) {
            System.out.print("Please enter a value between " + low + " and " + high + ": ");
            try {
                value = Integer.parseInt(scanner.next());
            }
            catch (NumberFormatException e) {
                value = 0;
            }
        }
        return String.valueOf(value);
    }

    /**
     * Prompts the user to provide input for a specific category.
     *
     * @param category The name of the category for which input is required.
     * @return The user-provided input.
     */
    private static String getInput (String category) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the " + category + " you want to retrieve data for: ");
        return scanner.nextLine();
    }
}
