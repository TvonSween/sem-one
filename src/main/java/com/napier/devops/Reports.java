package com.napier.devops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;


public class Reports {
    public void extract(ResultSet rset, String fileName, String[] cols) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            //cycle
            while (rset.next()) {
                for (int i = 0; i < cols.length; i++) {
                    sb.append(rset.getString(cols[i])).append("\t");
                    if (i == cols.length - 1) {
                        sb.append("\r\n");
                    }
                }
            }
            new File("./tmp/").mkdir();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(new File("./tmp/" + fileName + ".txt")));
            writer.write(sb.toString());
            writer.close();
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return;
        }
        // write to file
        new File("./output/").mkdir();
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(new File("./tmp/" + fileName + ".txt")));
        writer.write(sb.toString());
        writer.close();
        System.out.println(sb.toString());
    }
}
