package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportsTest {
    private Reports reports;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        reports = new Reports();
        mockResultSet = mock(ResultSet.class);

        // Stub mock ResultSet behavior
        when(mockResultSet.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(mockResultSet.getString("Name")).thenReturn("Country1", "Country2");
        when(mockResultSet.getString("Population")).thenReturn("1000000", "2000000");
    }

    @Test
    void testExtractCreatesCorrectCsvFile() throws IOException, SQLException {
        // Arrange
        String fileName = "test_report";
        String[] columns = {"Name", "Population"};

        // Act
        reports.extract(mockResultSet, fileName, columns);

        // Assert
        File csvFile = new File("./tmp/" + fileName + ".csv");
        assertTrue(csvFile.exists());

        // Verify file contents
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            assertEquals("Name,Population", header);

            String firstRow = reader.readLine();
            assertEquals("Country1,1000000", firstRow);

            String secondRow = reader.readLine();
            assertEquals("Country2,2000000", secondRow);
        }
    }

    @Test
    void testExtractHandlesEmptyResultSet() throws IOException, SQLException {
        // Arrange
        ResultSet emptyResultSet = mock(ResultSet.class);
        when(emptyResultSet.next()).thenReturn(false);
        String fileName = "empty_report";
        String[] columns = {"Name", "Population"};

        // Act
        reports.extract(emptyResultSet, fileName, columns);

        // Assert
        File csvFile = new File("./tmp/" + fileName + ".csv");
        assertTrue(csvFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            assertEquals("Name,Population", header);
            assertNull(reader.readLine());
        }
    }

    @Test
    void testColumnsEnumCompleteness() {
        // Verify all enum values are present
        assertNotNull(Reports.Columns.Code);
        assertNotNull(Reports.Columns.Name);
        assertNotNull(Reports.Columns.Continent);
        assertNotNull(Reports.Columns.Population);
        assertNotNull(Reports.Columns.Capital);
        assertNotNull(Reports.Columns.Region);
        assertNotNull(Reports.Columns.District);
        assertNotNull(Reports.Columns.City);
    }
}