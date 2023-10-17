package com.sneo.utilities;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import java.util.Iterator;

import static com.sneo.core.TestBase.logToReport;

/**
 * Contains XML utility methods
 *
 * @author ikumar
 */
@Log4j2
public class XMLUtils2 {


    public static Iterator<Difference> XMLDifferences(String resp1, String resp2) {
        //XMLUnit.setIgnoreWhitespace(true);
        //DetailedDiff detailXmlDiff;
        // differences = XMLUnit.compareXML(resp1, resp2);
        //Diff xmlDiff = new Diff(resp1, resp2);

        Diff xmlDiff = DiffBuilder.
                compare(resp1).
                withTest(resp2).
                ignoreWhitespace().
                ignoreComments().
                // normalizeWhitespace().
                        checkForSimilar().
                build();

        logToReport("<b>" + " Differences: </b>");

        Iterator<Difference> iterator = xmlDiff.getDifferences().iterator();
        Iterator<Difference> iterator2 = xmlDiff.getDifferences().iterator();


        int diffSize = 0;

        while (iterator.hasNext()) {
            String diffString = iterator.next().toString();
            String diffToLog = diffSize + 1 + ". " + diffString;
            logToReport(diffToLog);
            diffSize++;
        }
        return iterator2;
    }

    public static String getElement(Response response, String tagName) {

        String respString = response.asPrettyString();
        String tagElement = "";
        try {
            tagElement = respString.substring(respString.indexOf("<" + tagName + ">"), respString.indexOf("</" + tagName + ">") + tagName.length() + 3);
        } catch (RuntimeException e) {
            log.info(e);
        }
        return tagElement;
    }
}

