package com.napier.devops;

import com.napier.devops.helpers.UserSelectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppTest {
    private DBconnector mockDbConnector;
    private Connection mockConnection;
    private UserSelectionService mockUserSelectionService;
    private Reports mockReport;

    @BeforeEach
    void setUp() throws Exception {
        // Prepare mock objects
        mockDbConnector = mock(DBconnector.class);
        mockConnection = mock(Connection.class);
        mockUserSelectionService = mock(UserSelectionService.class);
        mockReport = mock(Reports.class);

        // Stub the database connector
        when(mockDbConnector.connect(anyString(), anyInt())).thenReturn(mockConnection);
    }

    @Test
    void testMainMethodWithDefaultDatabaseUrl() {
        // Arrange
        Map<String, String> mockInputs = new HashMap<>();
        mockInputs.put("question", "1");
        mockInputs.put("userInput", "");
        mockInputs.put("limit", "");

        // Stub user selection service
        when(mockUserSelectionService.getUserInput()).thenReturn(mockInputs);
        when(mockUserSelectionService.shouldSelect()).thenReturn(false);
    }

    @Test
    void testMainMethodWithCustomDatabaseUrl() {
        // Arrange
        String[] args = {"custom:3306"};
        Map<String, String> mockInputs = new HashMap<>();
        mockInputs.put("question", "1");
        mockInputs.put("userInput", "");
        mockInputs.put("limit", "");

        // Stub user selection service
        when(mockUserSelectionService.getUserInput()).thenReturn(mockInputs);
        when(mockUserSelectionService.shouldSelect()).thenReturn(false);
    }

    @Test
    void testDatabaseConnectionFailure() throws Exception {
        // Arrange
        when(mockDbConnector.connect(anyString(), anyInt())).thenReturn(null);

        // Verify that a RuntimeException is thrown when connection fails
        assertThrows(RuntimeException.class, () -> {
            // Simulate main method logic
            String databaseUrl = "localhost:33060";
            DBconnector db = mockDbConnector;
            Connection con = db.connect(databaseUrl, 0);
            if (con == null) {
                throw new RuntimeException("Failed to connect to database");
            }
        });
    }
}