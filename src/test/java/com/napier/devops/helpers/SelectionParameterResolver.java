package com.napier.devops.helpers;

import com.napier.devops.reports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class SelectionParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(), "java.util.Map<java.lang.String, devops.helpers>");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        //refactor to include cities, etc later
        Map<String, CountriesList> processors = new HashMap<>();
        processors.put("1", new CountriesList("PopulationOfCountries", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put("2", new CountriesList("PopulationOfContinentsByCountry", QueryConstants.CONTINENT_POPULATION_DESC));
        processors.put("3", new CountriesList("PopulationOfRegionsByCountry", QueryConstants.REGION_POPULATION_DESC));
        processors.put("4", new CountriesList("TopCountriesPerPopulation", QueryConstants.COUNTRIES_POPULATION_DESC));
        processors.put("5", new CountriesList("PopulationOfContinentsByCountryWithLimit", QueryConstants.CONTINENT_POPULATION_DESC));
        processors.put("6", new CountriesList("PopulationOfRegionsByCountryWithLimit", QueryConstants.REGION_POPULATION_DESC));
        return processors;
    }

}