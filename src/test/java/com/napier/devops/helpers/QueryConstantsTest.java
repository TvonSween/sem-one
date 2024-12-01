package com.napier.devops.helpers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueryConstantsTest {

    @BeforeEach
    void setUp() {
    }


    @Test
    public void testQueryContentsAreCorrect() {
        // Test that the SQL queries contain expected content
        assertEquals(
            "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID ORDER BY Population DESC;",
            QueryConstants.COUNTRIES_POPULATION_DESC
        );
        assertEquals(
                "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID WHERE Continent LIKE '%s' ORDER BY Population DESC;",
                QueryConstants.CONTINENT_POPULATION_DESC
        );
        assertEquals(
                "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID WHERE Region LIKE '%s' ORDER BY Population DESC;",
                QueryConstants.REGION_POPULATION_DESC
        );
        assertEquals(
                "SELECT city.Name as City, country.Name, Region, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode ORDER BY city.Population DESC;",
                QueryConstants.CITY_POPULATION_DESC
        );
        assertEquals(
                "SELECT city.Name as City, country.Name, Continent, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode WHERE Continent LIKE '%s' ORDER BY city.Population DESC;",
                QueryConstants.CITY_POPULATION_CONTINENT_DESC
        );
        assertEquals(
                "SELECT city.Name as City, country.Name, Region, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode WHERE Region LIKE '%s' ORDER BY city.Population DESC;",
                QueryConstants.CITY_POPULATION_REGION_DESC
        );
        assertEquals(
                "SELECT city.Name as City, country.Name, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode WHERE country.Name LIKE '%s' ORDER BY city.Population DESC;",
                QueryConstants.CITY_POPULATION_COUNTRY_DESC
        );
        assertEquals(
                "SELECT city.Name as City, country.Name, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode WHERE city.District LIKE '%s' ORDER BY city.Population DESC;",
                QueryConstants.CITY_POPULATION_DISTRICT_DESC
        );
        assertEquals(
                "SELECT city.Name as Capital, country.Name, city.Population FROM country JOIN city ON country.Capital = city.ID ORDER BY city.Population DESC;",
                QueryConstants.CITY_CAPITALS_POPULATION_DESC
        );
        assertEquals(
                "SELECT city.Name as Capital, country.Name, Region, city.Population FROM country JOIN city ON country.Capital = city.ID WHERE Region LIKE '%s' ORDER BY city.Population DESC;",
                QueryConstants.CITY_CAPITALS_REGION_POPULATION_DESC
        );
        assertEquals(
                "SELECT city.Name as Capital, country.Name, Continent, city.Population FROM country JOIN city ON country.Capital = city.ID WHERE Continent LIKE '%s' ORDER BY city.Population DESC;",
                QueryConstants.CITY_CAPITALS_CONTINENT_POPULATION_DESC
        );
        assertEquals(
                "SELECT co.Continent as Name, SUM(co.Population) AS Population, CONCAT(SUM(ci.CityPopulation), ' (', ROUND((SUM(ci.CityPopulation) / SUM(co.Population)) * 100), '%)') AS PopulationInCities, CONCAT(SUM(co.Population) - SUM(ci.CityPopulation), ' (', ROUND(((SUM(co.Population) - SUM(ci.CityPopulation)) / SUM(co.Population)) * 100), '%)') AS PopulationInNonCityAreas FROM country co LEFT JOIN (SELECT c.CountryCode, SUM(c.Population) AS CityPopulation FROM city c GROUP BY c.CountryCode) ci ON co.Code = ci.CountryCode GROUP BY co.Continent ORDER BY Population DESC;",
                QueryConstants.CONTINENT_LIVING_NOTLIVING_IN_CITY_DESC
        );
        assertEquals(
                "SELECT co.Region as Name, SUM(co.Population) AS Population, CONCAT(SUM(ci.CityPopulation), ' (', ROUND((SUM(ci.CityPopulation) / SUM(co.Population)) * 100), '%)') AS PopulationInCities, CONCAT(SUM(co.Population) - SUM(ci.CityPopulation), ' (', ROUND(((SUM(co.Population) - SUM(ci.CityPopulation)) / SUM(co.Population)) * 100), '%)') AS PopulationInNonCityAreas FROM country co LEFT JOIN (SELECT c.CountryCode, SUM(c.Population) AS CityPopulation FROM city c GROUP BY c.CountryCode) ci ON co.Code = ci.CountryCode GROUP BY co.Region ORDER BY Population DESC;",
                QueryConstants.REGION_LIVING_NOTLIVING_IN_CITY_DESC
        );
        assertEquals(
                "SELECT co.name AS Name, co.population AS Population, CONCAT(IFNULL(ci.CityPopulation, 0), ' (', IFNULL(ROUND((ci.CityPopulation / co.population) * 100), 0), '%)') AS PopulationInCities, CONCAT(co.population - IFNULL(ci.CityPopulation, 0), ' (', IFNULL(ROUND(((co.population - ci.CityPopulation) / co.population) * 100), 0), '%)') AS PopulationInNonCityAreas FROM country co LEFT JOIN (SELECT countrycode, SUM(population) AS CityPopulation FROM city GROUP BY countrycode) ci ON co.code = ci.countrycode ORDER BY Population DESC;",
                QueryConstants.COUNTRY_LIVING_NOTLIVING_IN_CITY_DESC
        );
        assertEquals(
                "SELECT Language, SUM(population) AS Population, CONCAT(FORMAT((SUM(population) / (SELECT SUM(population) FROM country)) * 100, 0), '%') AS PercentageOfWorld FROM countrylanguage JOIN country ON countrylanguage.countrycode = country.code WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') GROUP BY language ORDER BY Population DESC;",
                QueryConstants.LANGUAGE_PERCENTAGE_WORLD
        );
        assertEquals(
                "SELECT 'World' AS Name, SUM(population) AS Population FROM country;",
                QueryConstants.TOTAL_POPULATION_WORLD
        );
        assertEquals(
                "SELECT country.Continent AS Name, SUM(population) AS Population FROM country WHERE continent = '%s';",
                QueryConstants.TOTAL_POPULATION_CONTINENT
        );
        assertEquals(
                "SELECT country.Region AS Name, SUM(population) AS Population FROM country WHERE region = '%s';",
                QueryConstants.TOTAL_POPULATION_REGION
        );
        assertEquals(
                "SELECT city.Name AS Name, population AS Population FROM city WHERE Name = '%s';",
                QueryConstants.TOTAL_POPULATION_CITY
        );
        assertEquals(
                "SELECT country.Name AS Name, population AS Population FROM country WHERE name = '%s';",
                QueryConstants.TOTAL_POPULATION_COUNTRY
        );
        assertEquals(
                "SELECT city.District as Name, SUM(population) AS Population FROM city WHERE district = '%s';",
                QueryConstants.TOTAL_POPULATION_DISTRICT
        );

    }


    @Test
    public void testQuerySyntaxRequirements() {
        // Test SQL syntax requirements for all query constants
        for (java.lang.reflect.Field field : QueryConstants.class.getDeclaredFields()) {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                try {
                    String query = (String) field.get(null);

                    // Check if query is not empty
                    assertFalse(
                        query.trim().isEmpty(),
                        field.getName() + " should not be empty"
                    );

                    // Check if query ends with semicolon (if that's your standard)
                    assertTrue(
                        query.trim().endsWith(";"),
                        field.getName() + " should end with semicolon"
                    );

                    // Check for basic SQL keywords based on query type
                    if (query.toUpperCase().contains("SELECT")) {
                        assertTrue(
                            query.toUpperCase().contains("FROM"),
                            field.getName() + " SELECT query should contain FROM"
                        );
                    }

                    // Check for common SQL syntax errors
                    assertFalse(
                        query.contains("  "),
                        field.getName() + " should not contain double spaces"
                    );

                } catch (IllegalAccessException e) {
                    fail("Could not access field: " + field.getName());
                }
            }
        }
    }

    @AfterEach
    void tearDown() {
    }
}