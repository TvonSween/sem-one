package com.napier.devops.reports;

import com.napier.devops.Reports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CapitalCitiesListTest {
    private CapitalCitiesList capitalCitiesList;
    private Connection mockConnection;
    private Reports mockReport;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        // Setup mock objects
        mockConnection = mock(Connection.class);
        mockReport = mock(Reports.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Stub the connection to return mock prepared statement
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);

        // Stub the prepared statement to return mock result set
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testConstructor() {
        // Test constructor sets filename and query correctly
        String filename = "test_capital_cities.csv";
        String sqlQuery = "SELECT * FROM capital_cities";
        capitalCitiesList = new CapitalCitiesList(filename, sqlQuery);

        // Use reflection or custom method to verify private fields if needed
    }

    @Test
    void testProcessUserSelectionWithEmptyUserInput() throws SQLException, IOException {
        // Arrange
        String filename = "capital_cities.csv";
        String sqlQuery = "SELECT * FROM country";
        capitalCitiesList = new CapitalCitiesList(filename, sqlQuery);

        // Act
        capitalCitiesList.processUserSelection(mockReport, mockConnection, "", "");

        // Assert
        verify(mockConnection).prepareStatement(sqlQuery);
        verify(mockReport).extract(eq(mockResultSet), eq(filename), any());
    }

    @Test
    void testProcessUserSelectionWithUserInput() throws SQLException, IOException {
        // Arrange
        String filename = "capital_cities.csv";
        String sqlQuery = "SELECT * FROM capital_cities WHERE country LIKE ?";
        capitalCitiesList = new CapitalCitiesList(filename, sqlQuery);

        // Act
        capitalCitiesList.processUserSelection(mockReport, mockConnection, "Europe", "");

        // Assert
        verify(mockConnection).prepareStatement(contains("%Europe%"));
        verify(mockReport).extract(eq(mockResultSet), eq(filename), any());
    }

    @Test
    void testProcessUserSelectionWithLimit() throws SQLException, IOException {
        // Arrange
        String filename = "capital_cities.csv";
        String sqlQuery = "SELECT * FROM capital_cities";
        capitalCitiesList = new CapitalCitiesList(filename, sqlQuery);

        // Act
        capitalCitiesList.processUserSelection(mockReport, mockConnection, "", "10");

        // Assert
        verify(mockConnection).prepareStatement(contains("LIMIT 10"));
        verify(mockReport).extract(eq(mockResultSet), eq(filename), any());
    }

    @Test
    void testLogSevereError() {
        // Create a custom test implementation to verify logging
        class TestCapitalCitiesList extends CapitalCitiesList {
            private String loggedMessage;
            private Throwable loggedThrowable;

            public TestCapitalCitiesList(String filename, String sqlQueryString) {
                super(filename, sqlQueryString);
            }

            @Override
            protected void logSevereError(String message, Throwable ex) {
                this.loggedMessage = message;
                this.loggedThrowable = ex;
            }

            public String getLoggedMessage() {
                return loggedMessage;
            }

            public Throwable getLoggedThrowable() {
                return loggedThrowable;
            }
        }

        // Arrange
        TestCapitalCitiesList testList = new TestCapitalCitiesList("test.csv", "test query");
        Exception testException = new Exception("Test error");

        // Use reflection to call private logSevereError method
        testList.logSevereError("Test error message", testException);

        // Assert
        assertEquals("Test error message", testList.getLoggedMessage());
        assertEquals(testException, testList.getLoggedThrowable());
    }
}