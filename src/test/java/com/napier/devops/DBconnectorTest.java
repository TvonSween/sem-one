package com.napier.devops;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DBConnectorTest {

    private DBconnector dbConnector;
    private Connection mockConnection;

    @BeforeEach
    void setUp() {
        dbConnector = new DBconnector();
        mockConnection = mock(Connection.class);
    }

    @AfterEach
    void tearDown() throws Exception {
        // Ensure all resources are closed
        if (mockConnection != null) {
            mockConnection.close();
        }
    }

    // ... (previous tests remain the same)

    @Test
    void testDisconnectNullConnection() {
        // Instead of assertDoesNotThrow, we'll check system output or just call the method
        try {
            dbConnector.disconnect(null);
            // If no exception is thrown, test passes
            assertTrue(true);
        } catch (Exception e) {
            fail("disconnect(null) should not throw an exception");
        }
    }

    @Test
    void testDisconnectWithSQLException() throws SQLException {
        // Simulate SQLException during connection close
        doThrow(new SQLException("Forced exception during connection close"))
                .when(mockConnection)
                .close();

        try {
            dbConnector.disconnect(mockConnection);
            // If no unhandled exception is thrown, test passes
            verify(mockConnection).close();
            assertTrue(true);
        } catch (Exception e) {
            fail("disconnect should handle SQLException gracefully");
        }
    }
}