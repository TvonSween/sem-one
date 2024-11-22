package com.napier.devops.helpers;

import com.google.protobuf.DoubleArrayList;
import com.napier.devops.reports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;

@DisplayName("User selection")
@ExtendWith(SelectionParameterResolver.class)
class UserSelectionServiceTest {
    private CountriesList filename;
    private CountriesList countryQuery;
    //private final Map<String, IUserSelectionProcessor> processors = new HashMap<>();
    //public Map<String, String> inputs = new HashMap<>();
    //String categoryTest;

    @BeforeEach
    void init(Map<String, CountriesList> processors) {
       // this.report = processors.get("PopulationOfCountries");
       //this.countryQuery = processors.get();
       // countriesList = new CountriesList(this.countryFilename.toString(), this.countryQuery.toString());
    }

    @DisplayName("Country selection")
    @Test
    void CountrySelectionTest() {
        assertThat(processors).containsKey("1").containsValues("PopulationOfCountries");


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