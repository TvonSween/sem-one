package com.napier.devops.helpers;

import com.napier.devops.Reports;

import java.sql.SQLException;
import java.sql.Statement;

public interface IUserSelectionProcessor {
    void processUserSelection(Reports report, Statement stmt, Integer userInput) throws SQLException;
}
