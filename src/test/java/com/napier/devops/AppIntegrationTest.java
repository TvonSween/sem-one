package com.napier.devops;

import com.napier.devops.helpers.UserSelectionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    public static DBconnector dbConnector;
    public static Connection con = null;

    @BeforeAll
    static void init()
    {
        dbConnector = new DBconnector();
        // if running locally delay can be zero if database is running
        // To work on GitHub Actions set the delay to 10000 as the database takes a while to build
        con = dbConnector.connect("localhost:33060", 10000);

    }

    @Test
    void testProcessUserSelectionNotNull()
    {
        UserSelectionService userSelectionService = new UserSelectionService();
        assertNotNull(userSelectionService);
    }
/**
    @Test
    void testProcessUserSelection()
    {

        UserSelectionService userSelectionService = new UserSelectionService();
        Reports report = new Reports();

        assertEquals("North America", userSelectionService.processUserSelection("2", report, con, "North America", null));
        System.out.println("successfully retrieved " + "North America");
    }
    */
}