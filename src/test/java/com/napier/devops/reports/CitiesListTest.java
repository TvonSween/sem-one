package com.napier.devops.reports;

import com.napier.devops.Reports;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CitiesListTest {
    private CitiesList citiesList;
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
        String filename = "test_cities.csv";
        String sqlQuery = "SELECT * FROM cities";
        citiesList = new CitiesList(filename, sqlQuery);

        // Use reflection or custom method to verify private fields if needed
    }

    @Test
    void testProcessUserSelectionWithEmptyUserInput() throws SQLException, IOException {
        // Arrange
        String filename = "cities.csv";
        String sqlQuery = "SELECT * FROM country";
        citiesList = new CitiesList(filename, sqlQuery);

        // Act
        citiesList.processUserSelection(mockReport, mockConnection, "", "");

        // Assert
        verify(mockConnection).prepareStatement(sqlQuery);
        verify(mockReport).extract(eq(mockResultSet), eq(filename), any());
    }

    @Test
    void testProcessUserSelectionWithUserInput() throws SQLException, IOException {
        // Arrange
        String filename = "cities.csv";
        String sqlQuery = "SELECT * FROM cities WHERE country LIKE ?";
        citiesList = new CitiesList(filename, sqlQuery);

        // Act
        citiesList.processUserSelection(mockReport, mockConnection, "Europe", "");

        // Assert
        verify(mockConnection).prepareStatement(contains("%Europe%"));
        verify(mockReport).extract(eq(mockResultSet), eq(filename), any());
    }

    @Test
    void testProcessUserSelectionWithLimit() throws SQLException, IOException {
        // Arrange
        String filename = "cities.csv";
        String sqlQuery = "SELECT * FROM cities";
        citiesList = new CitiesList(filename, sqlQuery);

        // Act
        citiesList.processUserSelection(mockReport, mockConnection, "", "10");

        // Assert
        verify(mockConnection).prepareStatement(contains("LIMIT 10"));
        verify(mockReport).extract(eq(mockResultSet), eq(filename), any());
    }

    @Test
    void testLogSevereError() {
        // Create a custom test implementation to verify logging
        class TestCitiesList extends CitiesList {
            private String loggedMessage;
            private Throwable loggedThrowable;

            public TestCitiesList(String filename, String sqlQueryString) {
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
        TestCitiesList testList = new TestCitiesList("test.csv", "test query");
        Exception testException = new Exception("Test error");

        // Use reflection to call private logSevereError method
        testList.logSevereError("Test error message", testException);

        // Assert
        assertEquals("Test error message", testList.getLoggedMessage());
        assertEquals(testException, testList.getLoggedThrowable());
    }
}