package com.napier.devops.reports;

import com.napier.devops.Reports;
import com.napier.devops.reports.CapitalCitiesList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CapitalCitiesListTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private Reports mockReports;

    @Spy
    private Logger spyLogger;

    private CapitalCitiesList capitalCitiesList;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Configure mocks for database interactions
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testProcessUserSelection_SQLExceptionHandling() throws SQLException, IOException {
        // Arrange
        String filename = "capital_cities.csv";
        String sqlQuery = "SELECT * FROM capital_cities";

        capitalCitiesList = spy(new CapitalCitiesList(filename, sqlQuery));

        // Simulate SQLException
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Connection error"));

        // Act
        capitalCitiesList.processUserSelection(mockReports, mockConnection, "", "");

        // Assert
        // Verify that the logger was called with the appropriate error level and message
        verify(capitalCitiesList).logSevereError(contains("Error while executing the query"), any(SQLException.class));

        // Verify that no further processing occurs after the error
        verify(mockReports, never()).extract(any(), anyString(), any());
    }

    @Test
    void testProcessUserSelection_ExtractionExceptionHandling() throws Exception {
        // Arrange
        String filename = "capital_cities.csv";
        String sqlQuery = "SELECT * FROM capital_cities";

        capitalCitiesList = spy(new CapitalCitiesList(filename, sqlQuery));

        // Simulate exception during report extraction
        doThrow(new Exception("Extraction error")).when(mockReports).extract(any(), anyString(), any());

        // Act
        capitalCitiesList.processUserSelection(mockReports, mockConnection, "", "");

        // Assert
        // Verify that the logger was called with the appropriate error level and message
        verify(capitalCitiesList).logSevereError(contains("Error while processing the report"), (SQLException) any());

        // Optionally, you might want to add a method to the original class to help with testing
        // This would involve adding a method like:
        /*
        protected void logSevereError(String message, Throwable ex) {
            logger.log(Level.SEVERE, message, ex);
        }
        */
    }
}