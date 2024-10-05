package com.napier.devops.reports;

import com.napier.devops.Reports;
import com.napier.devops.helpers.IUserSelectionProcessor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate the PopulationOfWorldList file from the sql query.
 */
public class CountriesList implements IUserSelectionProcessor {
    String fileName;
    String sqlQueryString;
    ResultSet rset;
    private static final Logger logger = Logger.getLogger(CountriesList.class.getName());

    public CountriesList(String filename, String sqlQueryString) {
        this.sqlQueryString = sqlQueryString;
        this.fileName = filename;
    }
    @Override
    public void processUserSelection(Reports report, Connection con, Integer userInput) {
        if (userInput > 0) {
            this.sqlQueryString = this.sqlQueryString + " LIMIT  " + userInput + ";";
        }
        try {
            // Prepare statement
            PreparedStatement stmt = con.prepareStatement(this.sqlQueryString);
            // Execute SQL statement
            rset = stmt.executeQuery();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error while executing the query" + ex.getMessage());
        }

        try {
            // File name may match the class name for readability
            // Columns provided by the Reports.Columns enum
            report.extract(rset, this.fileName, new String[]{
                    Reports.Columns.Code.toString(),
                    Reports.Columns.Name.toString(),
                    Reports.Columns.Continent.toString(),
                    Reports.Columns.Region.toString(),
                    Reports.Columns.Population.toString(),
                    Reports.Columns.Capital.toString()
            });
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while processing the report" + e.getMessage());
        }
    }
}
