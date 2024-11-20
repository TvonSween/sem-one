package com.napier.devops;

import com.napier.devops.helpers.UserSelectionService;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    static App app;
    Boolean shouldQuestion = true;

    @BeforeAll
    static void init() {
        app = new App();
        Logger logger = Logger.getLogger("AppTest");
        String databaseURL = "jdbc:mysql://localhost:3306/test";
        DBconnector db = new DBconnector();
        Connection con = db.connect(databaseURL, 0);
        Reports reports = new Reports();
        Boolean shouldQuestion = true;

    }

    @Test
    void shouldSelectTest() {
        UserSelectionService userSelectionService = new UserSelectionService();
        assertTrue(userSelectionService.shouldSelect());
    }

    @Test
    void getUserInputTest(){
        UserSelectionService userSelectionService = new UserSelectionService();
        assertEquals("1", userSelectionService.getUserInput(), "Valid selection");
    }

    @Test
    void throwNullPointerException() {
        Connection con = null;
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> {
        });
    }

    @AfterEach
    void tearDown() {
        app = null;
    }

    /*
    @Test
    void main() {
    }

     */
}