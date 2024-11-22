package com.napier.devops.helpers;

import com.napier.devops.Reports;
import com.napier.devops.reports.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertThat;

@DisplayName("User selection")
@ExtendWith(SelectionParameterResolver.class)
class UserSelectionServiceTest {
    IUserSelectionProcessor processor;
    private CountriesList filename;
    private CountriesList countryQuery;
    //private  Map<String, IUserSelectionProcessor> processors = new HashMap<>();
    private Map<String, String> inputs = new HashMap<>();

    @BeforeEach
    void init(Map<String, CountriesList> processors) {
        processor = new IUserSelectionProcessor() {
            @Override
            public void processUserSelection(Reports report, Connection con, String userInput, String limit) throws SQLException {
                processor = processors.get("1");
            }
        };
    }

    @DisplayName("Country selection")
    @Test
    void CountrySelectionTest() {
        assertThat(processor)
                .containsKey("1")
                .containsValues("PopulationOfCountries");

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