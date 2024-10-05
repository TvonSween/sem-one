package com.napier.devops.helpers;

import com.napier.devops.Reports;

import java.sql.Connection;
import java.sql.SQLException;

public interface IUserSelectionProcessor {
    void processUserSelection(Reports report, Connection con, Integer userInput) throws SQLException;
}
