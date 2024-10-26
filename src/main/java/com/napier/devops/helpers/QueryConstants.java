package com.napier.devops.helpers;



public class QueryConstants {
    public static final String COUNTRIES_POPULATION_DESC = "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID ORDER BY Population DESC";
    public static final String REGION_POPULATION_DESC = "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city on country.Capital = city.ID WHERE Region = '%s' ORDER BY Population DESC";
}
