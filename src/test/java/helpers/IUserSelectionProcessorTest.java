package com.napier.devops.helpers;

import com.napier.devops.Reports;
import com.napier.devops.helpers.IUserSelectionProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserSelectionProcessorTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private Reports mockReports;

    private TestUserSelectionProcessor processor;

    // Test implementation of IUserSelectionProcessor
    private static class TestUserSelectionProcessor implements IUserSelectionProcessor {
        private boolean processWasCalled = false;
        private String lastUserInput;
        private String lastLimit;
        private boolean shouldThrowException;

        public void setShouldThrowException(boolean shouldThrow) {
            this.shouldThrowException = shouldThrow;
        }

        @Override
        public void processUserSelection(Reports report, Connection con, String userInput, String limit) throws SQLException {
            if (shouldThrowException) {
                throw new SQLException("Test exception");
            }
            processWasCalled = true;
            lastUserInput = userInput;
            lastLimit = limit;
        }

        public boolean wasProcessCalled() {
            return processWasCalled;
        }

        public String getLastUserInput() {
            return lastUserInput;
        }

        public String getLastLimit() {
            return lastLimit;
        }

        public void reset() {
            processWasCalled = false;
            lastUserInput = null;
            lastLimit = null;
            shouldThrowException = false;
        }
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new TestUserSelectionProcessor();
    }


    @Test
    void testProcessUserSelection_ValidInput() throws SQLException {
        // Arrange
        String userInput = "test input";
        String limit = "10";

        // Act
        processor.processUserSelection(mockReports, mockConnection, userInput, limit);

        // Assert
        assertTrue(processor.wasProcessCalled());
        assertEquals(userInput, processor.getLastUserInput());
        assertEquals(limit, processor.getLastLimit());
    }

    @Test
    void testProcessUserSelection_NullInput() throws SQLException {
        // Act
        processor.processUserSelection(mockReports, mockConnection, null, null);

        // Assert
        assertTrue(processor.wasProcessCalled());
        assertNull(processor.getLastUserInput());
        assertNull(processor.getLastLimit());
    }

    @Test
    void testProcessUserSelection_EmptyInput() throws SQLException {
        // Act
        processor.processUserSelection(mockReports, mockConnection, "", "");

        // Assert
        assertTrue(processor.wasProcessCalled());
        assertEquals("", processor.getLastUserInput());
        assertEquals("", processor.getLastLimit());
    }
    @Test
    void testProcessUserSelection_SQLException() {
        // Arrange
        processor.setShouldThrowException(true);

        // Act & Assert
        assertThrows(SQLException.class, () ->
                processor.processUserSelection(mockReports, mockConnection, "test", "10")
        );
    }

    @Test
    void testProcessUserSelection_NullReports() {
        Reports mockReports1 = new Reports();
        mockReports1 = null;
        Assertions.<NullPointerException>assertThrows(NullPointerException.class, () ->
                processor.processUserSelection(null, mockConnection, "test", "10")
        );
    }

    @Test
    void testProcessUserSelection_NullConnection() {
        // Act & Assert
        assertThrows(NullPointerException.class, () ->
                processor.processUserSelection(mockReports, null, "test", "10")
        );
    }
}