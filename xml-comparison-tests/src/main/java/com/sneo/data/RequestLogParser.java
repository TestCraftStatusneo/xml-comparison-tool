package com.sneo.data;

import com.sneo.datautils.CSVWriter;
import com.sneo.datautils.FileReader;

public class RequestLogParser {

    public static void parseRequestLogs(String filepath, String startTag, String endTag) {
        //  String fileName = "input_file.txt"; // replace with your file name
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(fileName));
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
        String fileContent = FileReader.readFile(filepath);

        //String startTag = "<PickUpLocation";
        // String endTag = "</OTA_VehAvailRateRQ>";
        int startIndex = 0;
        int endIndex = 0;
        while (true) {
            startIndex = fileContent.indexOf(startTag, endIndex);
            endIndex = fileContent.indexOf(endTag, startIndex);
            if (startIndex != -1 && endIndex != -1) {
                String entry = fileContent.substring(startIndex, endIndex + endTag.length());
                if (!entry.contains("<PictureURL>")) {
                    String[] record = {entry};
                    CSVWriter.appendToCSV(record);
                }
                // System.out.println(fileContent.substring(startIndex, endIndex + endTag.length()));
            } else {
                break;
            }
        }

        //  reader.close();
    }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
}

