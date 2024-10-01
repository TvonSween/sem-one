package com.napier.devops;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
     /**
     * Connection to MySQL database.
     */
    public static void main(String[] args)
    {
        String databaseUrl = args.length < 1 ? "localhost:33060" : args[0];
        try {
            Connection con = DBconnector.connect(databaseUrl, 10000);
            if (con == null) return;
            Reports report = new Reports();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String sql = "select * from country order by population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(sql);

            report.extract(rset, "country", new String[]{
                    ReportColumns.Countries.Code.toString(),
                    ReportColumns.Countries.Name.toString(),
                    ReportColumns.Countries.Continent.toString(),
                    ReportColumns.Countries.Region.toString(),
                    ReportColumns.Countries.Population.toString(),
                    ReportColumns.Countries.Capital.toString()
            });
            DBconnector.disconnect();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
