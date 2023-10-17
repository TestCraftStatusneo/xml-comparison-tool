package com.sneo.datautils;


import lombok.extern.log4j.Log4j2;
import org.custommonkey.xmlunit.DetailedDiff;
import org.xmlunit.diff.Difference;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Writes CSV using OpenCSV
 *
 * @author ikumar
 */

@Log4j2
public class CSVWriter {


    public static void appendToCSV(String[] record) {
        // Create the CSV writer object
        com.opencsv.CSVWriter writer = null;
        try {
            writer = new com.opencsv.CSVWriter(new FileWriter("./testreport/output.csv", true));


            //Write the record to file
            writer.writeNext(record, false);

            // Close the CSV writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendDiffsToCSV(String info, DetailedDiff dd) {
        int diffSize = dd.getAllDifferences().size();
        StringBuffer sb = new StringBuffer();

        if (diffSize > 0) {

            for (int i = 0; i < diffSize; i++) {
                String diffString = i + 1 + ". " + dd.getAllDifferences().get(i).toString() + "\n";
                sb.append(diffString);
            }
            String[] record = {info, sb.toString()};
            CSVWriter.appendToCSV(record);
        }
    }

    public static int appendDiffsToCSV(String info, Iterator<Difference> iterator) {
        StringBuffer sb = new StringBuffer();
        int diffSize = 0;

        while (iterator.hasNext()) {
            String diffString = iterator.next().toString();
            String diffToLog = diffSize + 1 + ". " + diffString + "\n";
            sb.append(diffToLog);
            diffSize++;
        }

        if (diffSize > 0) {
            String[] record = {info, sb.toString()};
            CSVWriter.appendToCSV(record);
        }

        return diffSize;
    }

}
