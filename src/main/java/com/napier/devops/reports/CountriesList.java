package com.napier.devops.reports;

import com.napier.devops.Reports;
import com.napier.devops.helpers.IUserSelectionProcessor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Generate the PopulationOfWorldList file from the sql query.
 */
public class CountriesList implements IUserSelectionProcessor {
    String fileName;
    String sqlQueryString;

    public CountriesList(String filename, String sqlQueryString) {
        this.sqlQueryString = sqlQueryString;
        this.fileName = filename;
    }
    @Override
    public void processUserSelection(Reports report, Statement stmt, Integer userInput) throws SQLException {
        if (userInput > 0) {
            this.sqlQueryString = this.sqlQueryString + " LIMIT  " + userInput + ";";
        }
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(this.sqlQueryString);

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
