package com.napier.devops.reports;

import com.napier.devops.Reports;
import com.napier.devops.helpers.IUserSelectionProcessor;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Generate the TopCountriesPerPopulation file from the sql query.
 */
public class TopCountriesPerPopulation implements IUserSelectionProcessor {
    @Override
    public void processUserSelection(Reports report, Statement stmt, Integer userInput) throws SQLException {
        // Create string for SQL statement
        String sql = "SELECT country.code as Code, country.Name, Continent, Region, country.Population, city.Name as Capital FROM country JOIN city ON country.Capital = city.ID ORDER BY Population DESC LIMIT " + userInput;
        // Execute SQL statement
        ResultSet rset = stmt.executeQuery(sql);

        try {
            // File name may match the class name for readability
            // Columns provided by the Reports.Columns enum
            report.extract(rset, "TopCountriesPerPopulation", new String[]{
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
