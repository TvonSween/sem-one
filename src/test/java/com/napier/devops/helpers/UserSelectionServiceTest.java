package com.napier.devops.helpers;

import com.napier.devops.reports.*;
import com.napier.devops.helpers.QueryConstants;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.napier.devops.helpers.UserSelectionService.getInput;
import static org.junit.jupiter.api.Assertions.*;

class UserSelectionServiceTest {


    private final Map<String, IUserSelectionProcessor> processors = new HashMap<>();
    public Map<String, String> inputs = new HashMap<>();
    String categoryTest;

    @Test
    void UserSelectionTest() {

        processors.put("1", new CountriesList("PopulationOfCountries", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put("2", new CountriesList("PopulationOfContinentsByCountry", QueryConstants.CONTINENT_POPULATION_DESC));
        processors.put("3", new CountriesList("PopulationOfRegionsByCountry", QueryConstants.REGION_POPULATION_DESC));
        processors.put("4", new CountriesList("TopCountriesPerPopulation", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put("5", new CountriesList("PopulationOfContinentsByCountryWithLimit", QueryConstants.CONTINENT_POPULATION_DESC));
        processors.put("6", new CountriesList("PopulationOfRegionsByCountryWithLimit", QueryConstants.REGION_POPULATION_DESC));
        processors.put("7", new CitiesList("PopulationOfCities", QueryConstants.CITY_POPULATION_DESC));
        processors.put("8", new CitiesList("CitiesByContinent", QueryConstants.CITY_POPULATION_CONTINENT_DESC));
        processors.put("9", new CitiesList("CitiesByRegion", QueryConstants.CITY_POPULATION_REGION_DESC));
        processors.put("10", new CitiesList("CitiesByCountry", QueryConstants.CITY_POPULATION_COUNTRY_DESC));
        processors.put("11", new CitiesList("CitiesByDistrict", QueryConstants.CITY_POPULATION_DISTRICT_DESC));
        processors.put("12", new CitiesList("TopCitiesPerPopulation", QueryConstants.CITY_POPULATION_DESC));
        processors.put("13", new CitiesList("TopCitiesByContinent", QueryConstants.CITY_POPULATION_CONTINENT_DESC));
        processors.put("14", new CitiesList("TopCitiesByRegion", QueryConstants.CITY_POPULATION_REGION_DESC));
        processors.put("15", new CitiesList("TopCitiesByCountry", QueryConstants.CITY_POPULATION_COUNTRY_DESC));
        processors.put("16", new CitiesList("TopCitiesByDistrict", QueryConstants.CITY_POPULATION_DISTRICT_DESC));
        processors.put("17", new CapitalCitiesList("PopulationOfCapitalCities", QueryConstants.CITY_CAPITALS_POPULATION_DESC));
        processors.put("18", new CapitalCitiesList("CapitalCitiesInContinentByPopulation", QueryConstants.CITY_CAPITALS_CONTINENT_POPULATION_DESC));
        processors.put("19", new CapitalCitiesList("CapitalCitiesInRegionByPopulation", QueryConstants.CITY_CAPITALS_REGION_POPULATION_DESC));
        processors.put("20", new CapitalCitiesList("TopCapitalCitiesPerPopulation", QueryConstants.CITY_CAPITALS_POPULATION_DESC));
        processors.put("21", new CapitalCitiesList("CapitalCitiesInContinentWithLimit", QueryConstants.CITY_CAPITALS_CONTINENT_POPULATION_DESC));
        processors.put("22", new CapitalCitiesList("CapitalCitiesInRegionWithLimit", QueryConstants.CITY_CAPITALS_REGION_POPULATION_DESC));
        processors.put("23", new PopulationInCitiesList("PopulationLivingNotLivingInCitiesPerContinent", QueryConstants.CONTINENT_LIVING_NOTLIVING_IN_CITY_DESC));
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

    @Test
    void getInputTest() {
    }


    /*
    @Test
    void getUserInputTest() {
        assertEquals("1", getUserInput("1"));
    }

    @Test
    public void shouldSelectTest() {
        assertEquals("y", shouldSelect("y"));
    }
    */


}