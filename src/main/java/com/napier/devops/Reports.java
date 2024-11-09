package com.napier.devops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * The {@code Reports} class provides functionalities to extract data from a
 * {@link ResultSet} into a CSV file. It allows specifying which columns should
 * be extracted and saved into the CSV file.
 */
public class Reports {

    /**
     * Enum representing the columns that can be extracted for reports.
     */
    public enum Columns {
        /**
         * Represents the 'Code' column.
         */
        Code,
        /**
         * Represents the 'Name' column.
         */
        Name,
        /**
         * Represents the 'Continent' column.
         */
        Continent,
        /**
         * Represents the 'Population' column.
         */
        Population,
        /**
         * Represents the 'Capital' column.
         */
        Capital,
        /**
         * Represents the 'Region' column.
         */
        Region,
        /**
         * Represents the 'District' column.
         */
        District,
        /**
         * Represents the 'City' column.
         */
        City,
        PopulationInCities,
        PopulationInNonCityAreas,
        PercentageOfWorld,
        Language
    }

    /**
     * Extracts the specified columns from the provided {@link ResultSet} and writes
     * them into a CSV file with the given file name.
     *
     * @param rset     The {@link ResultSet} containing the query results from the database.
     * @param fileName The name of the file where the CSV will be saved (without the extension).
     * @param cols     An array of column names (as {@link String}) that should be extracted from the result set.
     *
     * @throws IOException If an I/O error occurs when writing to the file.
     *
     * <p>Example usage:</p>
     * <pre>
     * {@code
     * ResultSet rs = ...;  // Obtained from a database query
     * String[] columns = {"Name", "Population"};
     * Reports report = new Reports();
     * report.extract(rs, "world_population", columns);
     * }
     * </pre>
     */
    public void extract(ResultSet rset, String fileName, String[] cols) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            // Append column headers
            for (int i = 0; i < cols.length; i++) {
                sb.append(cols[i]);
                if (i != cols.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("\n");

            // Append data rows
            while (rset.next()) {
                for (int i = 0; i < cols.length; i++) {
                    sb.append(rset.getString(cols[i]));
                    if (i == cols.length - 1) {
                        sb.append("\n");
                    } else {
                        sb.append(",");
                    }
                }
            }

            // Create directory if not exists and write to file
            new File("./tmp/");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("./tmp/" + fileName + ".csv"));
            writer.write(sb.toString());
            writer.close();
            System.out.println(sb);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
    }
}
