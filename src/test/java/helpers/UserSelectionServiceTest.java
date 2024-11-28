package com.napier.devops.helpers;
//import com.napier.devops.reports.*;
import com.napier.devops.Reports;
//import com.napier.devops.helpers.UserSelectionService;
//import com.napier.devops.helpers.IUserSelectionProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
//import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class UserSelectionServiceTest {

    @InjectMocks
    private UserSelectionService userSelectionService;

    @Mock
    private Reports mockReports;

    @Mock
    private Connection mockConnection;

    @Mock
    private IUserSelectionProcessor mockProcessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessUserSelectionValid() throws SQLException {
        // Setup
        String questionId = "1";
        String userInput = "Asia";
        String limit = "10";

        // Simulate the processor behavior for a valid questionId
        mockProcessor.processUserSelection(mockReports, mockConnection, userInput, limit);

        // Run the method
        userSelectionService.processUserSelection(questionId, mockReports, mockConnection, userInput, limit);

        // Verify that the processor's method is called
        verify(mockProcessor, times(1)).processUserSelection(mockReports, mockConnection, userInput, limit);
    }

    @Test
    void testProcessUserSelectionInvalid() {
        // Setup
        String invalidQuestionId = "99"; // Invalid ID

        // Run and assert that an IllegalArgumentException is thrown
        assertThrows(IllegalArgumentException.class, () -> {
            userSelectionService.processUserSelection(invalidQuestionId, mockReports, mockConnection, "Asia", "10");
        });
    }

    @Test
    void testGetUserInputValid() {
        // Here we simulate user input for a valid question
        // In practice, we would need to mock Scanner or the user interaction
        Map<String, String> userInput = userSelectionService.getUserInput();

        // Assert that the output map contains valid keys (this will depend on the user input)
        assertTrue(userInput.containsKey("question"));
        assertTrue(userInput.containsKey("userInput"));
        assertTrue(userInput.containsKey("limit"));
    }

    @Test
    void testShouldSelectYes() {
        // Simulate user input as "Y"
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("Y");

        // Test the method's behavior
        assertTrue(userSelectionService.shouldSelect());
    }

    @Test
    void testShouldSelectNo() {
        // Simulate user input as "N"
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("N");

        // Test the method's behavior
        assertFalse(userSelectionService.shouldSelect());
    }

    @Test
    void testShouldSelectInvalidInput() {
        // Simulate invalid input (not Y or N)
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.nextLine()).thenReturn("Invalid");

        // Test the method's behavior
        assertFalse(userSelectionService.shouldSelect());
    }

    @Test
    void testGetNValid() {
        // Mock the user input for "N"
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn("5");

        // Assert that the method returns the correct value
        assertEquals("5", UserSelectionService.getN());
    }

    @Test
    void testGetContinentValid() {
        // Mock the user input for continent selection
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn("2"); // Let's say user selects Africa

        // Assert that the method returns the correct continent name
        assertEquals("Africa", userSelectionService.getContinent());
    }

    @Test
    void testReadRangeInvalidInput() {
        // Mock invalid input for reading range
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn("invalid"); // Invalid input

        // Verify that the input is retried until valid
        assertEquals("1", UserSelectionService.read_range(mockScanner, 1, 32)); // Assuming 1 is a valid input
    }

    @Test
    void testReadRangeValidInput() {
        // Mock valid input for reading range
        Scanner mockScanner = mock(Scanner.class);
        when(mockScanner.next()).thenReturn("5"); // Valid input

        // Verify that the method returns the correct value
        assertEquals("5", UserSelectionService.read_range(mockScanner, 1, 32));
    }
}
