package com.napier.devops;

import com.napier.devops.helpers.UserSelectionService;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    public static App app = new App();
    private Logger logger;
    private String databaseURL;
    private DBconnector db;
    private Connection con;
    private Reports reports;
    private Boolean shouldQuestion;

    @BeforeEach
    void init() {
        databaseURL = "//localhost:33060";
        logger = Logger.getLogger("App");
        db = new DBconnector();
        con = db.connect(databaseURL, 0);
        reports = new Reports();
        shouldQuestion = true;
    }

    @Test
    void shouldSelectTest() {
        UserSelectionService userSelectionService = new UserSelectionService();
        //assertFalse(userSelectionService.shouldSelect());
    }

    @Test
    void getUserInputTest(){
        UserSelectionService userSelectionService = new UserSelectionService();
        //assertEquals("1", userSelectionService.getUserInput(), "Valid selection");
    }

    @Test
    void loggerTest() {
        //Logger logger = Logger.getLogger("AppTest");
    }

    @Test
    void throwNullPointerException() {
        Connection con = null;
        //NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> {
       // });
    }

    @AfterAll
    static void tearDown() {
        app = null;
    }

}