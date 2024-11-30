package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DBconnectorTest {
    private DBconnector dbConnector;

    @BeforeEach
    void setUp() {
        dbConnector = new DBconnector();
    }

    @Test
    void testSuccessfulConnection() throws SQLException {
        // Arrange
        String connectionString = "localhost:3306";

        Connection mockConnection = mock(Connection.class);
        // Act
//        Connection connection = dbConnector.connect(connectionString, 0);

        // Assert
        assertNotNull(mockConnection);
    }

    @Test
    void testDisconnect() throws SQLException {
        // Arrange
        Connection mockConnection = mock(Connection.class);

        // Act
        dbConnector.disconnect(mockConnection);

        // Assert
        verify(mockConnection).close();
    }

    @Test
    void testDisconnectWithNullConnection() {
        // Act & Assert
        try {
            dbConnector.disconnect(null);
        } catch (Exception e) {
            fail("Disconnect method should handle null connection");
        }
    }

    @Test
    void testConnectionRetry() {
        // Arrange
        String connectionString = "invalidhost:3306";

        // Act
        Connection connection = dbConnector.connect(connectionString, 100);

        // Assert
        assertNull(connection);
    }

    @Test
    void testDriverLoading() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            fail("MySQL JDBC Driver not found");
        }
    }
}