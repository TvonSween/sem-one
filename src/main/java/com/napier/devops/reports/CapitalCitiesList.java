package com.napier.devops.reports;

import com.napier.devops.Reports;
import com.napier.devops.helpers.IUserSelectionProcessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The {@code CapitalCitiesList} class implements the {@link IUserSelectionProcessor}
 * interface and is responsible for generating a report of capital cities based on
 * a specified SQL query. It handles the execution of the query and the
 * extraction of results into a specified file format.
 */
public class CapitalCitiesList implements IUserSelectionProcessor {

    private String fileName;        // Name of the output file
    private String sqlQueryString;  // SQL query string to execute
    private ResultSet rset;         // ResultSet object to hold query results
    private static final Logger logger = Logger.getLogger(CapitalCitiesList.class.getName());

    /**
     * Constructs a {@code CapitalCitiesList} instance with the specified filename and SQL query.
     *
     * @param filename       The name of the file to which the results will be written.
     * @param sqlQueryString The SQL query string used to fetch the city data.
     */
    public CapitalCitiesList(String filename, String sqlQueryString) {
        this.sqlQueryString = sqlQueryString;
        this.fileName = filename;
    }

    /**
     * Processes the user selection by executing the SQL query and extracting the results
     * into a CSV file. If the user specifies a limit, the query will be modified to include
     * the limit on the number of results returned.
     *
     * @param report    The instance of the {@link Reports} class used to generate the report.
     * @param con       The SQL connection to execute queries against the database.
     * @param userInput The additional user input required for specific questions.
     * @param limit The additional user input required for limiting the number of results.
     */
    @Override
    public void processUserSelection(Reports report, Connection con, String userInput, String limit) {

        if (!Objects.equals(userInput, "")) {
            this.sqlQueryString = String.format(this.sqlQueryString, "%" + userInput + "%");
        }
        if (!Objects.equals(limit, "")) {
            this.sqlQueryString = this.sqlQueryString + " LIMIT " + Integer.parseInt(limit) + ";";
        }

        try {
            // Prepare statement
            PreparedStatement stmt = con.prepareStatement(this.sqlQueryString);
            // Execute SQL statement
            rset = stmt.executeQuery();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error while executing the query: " + ex.getMessage());
        }

        try {
            report.extract(rset, this.fileName, new String[]{
                        Reports.Columns.Capital.toString(),
                        //Country name
                        Reports.Columns.Name.toString(),
                        Reports.Columns.Population.toString()
                });
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing the report: " + e.getMessage());
        }
    }
}
