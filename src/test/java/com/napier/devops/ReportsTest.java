package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class ReportsTest {

    @BeforeAll
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void extractTest() {
        StringBuilder sb = new StringBuilder();
        String[] cols = {"Country code", "Population"};
        String filename = "PopulationOfCities";
        ResultSet rset = null;
        //extract(rset, filename, cols);

    }
}