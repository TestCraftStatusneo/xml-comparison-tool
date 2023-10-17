package com.sneo.utilities;

import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

/**
 * Contains data formatter methods
 *
 * @author ikumar
 */
@Log4j2
public class StringUtils {

    public static String replaceVariable(String dataString, String variable, String value) {
        dataString = dataString.replaceAll(Pattern.quote("${" + variable + "}"), value);
        return dataString.replaceAll(Pattern.quote("{{" + variable + "}}"), value);
    }

    public static String replaceString(String dataString, String originalString, String newString) {
        return dataString.replaceAll(Pattern.quote(originalString), newString);
    }

    public static String replaceStringIfContains(String dataString, String contains, CharSequence originalString, CharSequence newString) {
        String[] arr = dataString.split("\n");    // every arr items is a line now.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (s.contains(contains))
                s = s.replace(originalString, newString); //If you want to split with new lines you can use sb.append(s + "\n");
            sb.append(s + "\n");
        }
        return sb.toString(); //new file that does not contains that lines.
    }

    public static String removeLine(String fileText, int index) {
        String[] arr = fileText.split("\n");    // every arr items is a line now.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (i != index)
                sb.append(s + "\n"); //If you want to split with new lines you can use sb.append(s + "\n");
        }
        return sb.toString(); //new file that does not contains that lines.
    }

    public static String removeLine(String fileText, String line) {
        //String file = FileReader.readFile(filePath);
        String[] arr = fileText.split("\n");    // every arr items is a line now.
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            if (!s.contains(line))
                sb.append(s + "\n"); //If you want to split with new lines you can use sb.append(s + "\n");
        }
        return sb.toString(); //new file that does not contains that lines.
    }

    public static String replaceLine(String fileText, String line, String newLine) {
        //String file = FileReader.readFile(filePath);
        String[] arr = fileText.split("\n");    // every arr items is a line now.
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            if (!s.contains(line))
                sb.append(s + "\n"); //If you want to split with new lines you can use sb.append(s + "\n");
            else sb.append(newLine + "\n");
        }
        return sb.toString(); //new file that does not contains that lines.
    }


}

