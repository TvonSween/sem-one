package com.napier.devops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;


public class Reports {
    // Column enums that can be used in the reports
    public enum Columns {
        Code, Name, Continent, Population, Capital, Region
    }
    /**
     * Extract the results to a .csv file.
     * @param rset The ResultSet of the query from db.
     * @param fileName The file name.
     * @param cols The columns that should be extracted.
     */
    public void extract(ResultSet rset, String fileName, String[] cols) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            for (String col : cols) {
                sb.append(col).append(",");
            }
            sb.append("\r\n\n");
            //cycle
            while (rset.next()) {
                for (int i = 0; i < cols.length; i++) {
                    sb.append(rset.getString(cols[i])).append(",");
                    if (i == cols.length - 1) {
                        sb.append("\r\n");
                    }
                }
            }
            new File("./tmp/").mkdir();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("./tmp/" + fileName + ".csv"));
            writer.write(sb.toString());
            writer.close();
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }
    }
}
