package com.napier.devops.helpers;

import com.napier.devops.Reports;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The {@code IUserSelectionProcessor} interface defines a contract for processing
 * user selections related to report generation. Implementing classes must provide
 * specific logic to handle the report generation based on user input.
 */
public interface IUserSelectionProcessor {

    /**
     * Processes the user's selection for generating a report.
     *
     * @param report    The instance of the {@link Reports} class used to generate the report.
     * @param con       The SQL connection to execute queries against the database.
     * @param userInput The additional user input required for specific report types.
     * @param limit     The additional user input for the limit of rows.
     *
     * @throws SQLException If an error occurs while executing the SQL query.
     */
    void processUserSelection(Reports report, Connection con, String userInput, String limit) throws SQLException;
}
