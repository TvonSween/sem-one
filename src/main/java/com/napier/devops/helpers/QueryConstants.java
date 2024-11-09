package com.napier.devops.helpers;



public class QueryConstants {
    public static final String COUNTRIES_POPULATION_DESC = "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID ORDER BY Population DESC";
    public static final String REGION_POPULATION_DESC = "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID WHERE Region LIKE '%s' ORDER BY Population DESC";
    public static final String CITY_POPULATION_DESC = "SELECT city.Name as City, country.Name, Region, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode ORDER BY city.Population DESC";
    public static final String CITY_POPULATION_CONTINENT_DESC = "SELECT city.Name as City, country.Name, Continent, city.District, city.Population FROM country JOIN city ON country.Code = city.CountryCode WHERE Continent LIKE '%s' ORDER BY city.Population DESC";
    public static final String CITY_CAPITALS_POPULATION_DESC = "SELECT city.Name as Capital, country.Name, city.Population FROM country JOIN city ON country.Capital = city.ID ORDER BY city.Population DESC";
    public static final String CITY_CAPITALS_REGION_POPULATION_DESC = "SELECT city.Name as Capital, country.Name, Region, city.Population FROM country JOIN city ON country.Capital = city.ID WHERE Region LIKE '%s' ORDER BY city.Population DESC";
    public static final String CITY_CAPITALS_CONTINENT_POPULATION_DESC = "SELECT city.Name as Capital, country.Name, Continent, city.Population FROM country JOIN city ON country.Capital = city.ID WHERE Continent LIKE '%s' ORDER BY city.Population DESC";
    public static final String CONTINENT_LIVING_NOTLIVING_IN_CITY_DESC = "SELECT co.Continent as Name, SUM(co.Population) AS Population, CONCAT(SUM(ci.CityPopulation), ' (', ROUND((SUM(ci.CityPopulation) / SUM(co.Population)) * 100), '%)') AS PopulationInCities, CONCAT(SUM(co.Population) - SUM(ci.CityPopulation), ' (', ROUND(((SUM(co.Population) - SUM(ci.CityPopulation)) / SUM(co.Population)) * 100), '%)') AS PopulationInNonCityAreas FROM country co LEFT JOIN (SELECT c.CountryCode, SUM(c.Population) AS CityPopulation FROM city c GROUP BY c.CountryCode) ci ON co.Code = ci.CountryCode GROUP BY co.Continent ORDER BY Population DESC";
    public static final String REGION_LIVING_NOTLIVING_IN_CITY_DESC = "SELECT co.Region as Name, SUM(co.Population) AS Population, CONCAT(SUM(ci.CityPopulation), ' (', ROUND((SUM(ci.CityPopulation) / SUM(co.Population)) * 100), '%)') AS PopulationInCities, CONCAT(SUM(co.Population) - SUM(ci.CityPopulation), ' (', ROUND(((SUM(co.Population) - SUM(ci.CityPopulation)) / SUM(co.Population)) * 100), '%)') AS PopulationInNonCityAreas FROM country co LEFT JOIN (SELECT c.CountryCode, SUM(c.Population) AS CityPopulation FROM city c GROUP BY c.CountryCode) ci ON co.Code = ci.CountryCode GROUP BY co.Region ORDER BY Population DESC";
    public static final String COUNTRY_LIVING_NOTLIVING_IN_CITY_DESC = "SELECT co.name AS Name, co.population AS Population, CONCAT(IFNULL(ci.CityPopulation, 0), ' (', IFNULL(ROUND((ci.CityPopulation / co.population) * 100), 0), '%)') AS PopulationInCities, CONCAT(co.population - IFNULL(ci.CityPopulation, 0), ' (', IFNULL(ROUND(((co.population - ci.CityPopulation) / co.population) * 100), 0), '%)') AS PopulationInNonCityAreas FROM country co LEFT JOIN (SELECT countrycode, SUM(population) AS CityPopulation FROM city GROUP BY countrycode) ci ON co.code = ci.countrycode ORDER BY Population DESC";
    public static final String LANGUAGE_PERCENTAGE_WORLD = "SELECT Language, SUM(population) AS Population, CONCAT(FORMAT((SUM(population) / (SELECT SUM(population) FROM country)) * 100, 0), '%') AS PercentageOfWorld FROM countrylanguage JOIN country ON countrylanguage.countrycode = country.code WHERE language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') GROUP BY language ORDER BY Population DESC";
    public static final String TOTAL_POPULATION_WORLD = "SELECT 'World' AS Name, SUM(population) AS Population FROM country";
    public static final String TOTAL_POPULATION_CONTINENT = "SELECT country.Continent AS Name, SUM(population) AS Population FROM country WHERE continent = '%s'";
    public static final String TOTAL_POPULATION_REGION = "SELECT country.Region AS Name, SUM(population) AS Population FROM country WHERE region = '%s';";
    public static final String TOTAL_POPULATION_CITY = "SELECT city.Name AS Name, population AS Population FROM city WHERE Name = '%s'";
    public static final String TOTAL_POPULATION_COUNTRY = "SELECT country.Name AS Name, population AS Population FROM country WHERE name = '%s'";
    public static final String TOTAL_POPULATION_DISTRICT = "SELECT city.District as Name, SUM(population) AS Population FROM city WHERE district = '%s'";
}
