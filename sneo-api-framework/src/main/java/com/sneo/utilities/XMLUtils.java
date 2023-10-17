package com.sneo.utilities;

import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLUnit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sneo.core.TestBase.logToReport;

/**
 * Contains XML utility methods
 *
 * @author ikumar
 */
@Log4j2
public class XMLUtils {

    public static DetailedDiff XMLDifferences(String resp1, String resp2, String tagName, boolean ignoreOrder) {
        XMLUnit.setIgnoreWhitespace(true);
        DetailedDiff detailXmlDiff;
        try {
            // differences = XMLUnit.compareXML(resp1, resp2);
            Diff xmlDiff = new Diff(resp1, resp2);
            if (ignoreOrder)
                xmlDiff.overrideElementQualifier(new ElementNameAndAttributeQualifier());


            logToReport("<b>" + tagName + " Differences: </b>");
            detailXmlDiff = new DetailedDiff(xmlDiff);

            int diffSize = detailXmlDiff.getAllDifferences().size();
            for (int i = 0; i < diffSize; i++) {
                String diffString = detailXmlDiff.getAllDifferences().get(i).toString();
                logToReport(i + 1 + ". " + diffString);
            }
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return detailXmlDiff;
    }

    public static String removeEmptyLines(String xmlString) {
        // Define the regular expression pattern to match empty lines
        String pattern = "(?m)^\\s*$[\n\r]{1,}";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher m = r.matcher(xmlString);

        // Replace empty lines with an empty string
        String result = m.replaceAll("");

        return result;
    }

    public static String deleteFirstElementAppearance(String respString, String tagName){
        StringBuilder stringBuilder = new StringBuilder(respString);
        int startIdx = getElementStartIdx(respString, tagName);
        int endIdx = getElementEndIdx(respString, tagName);
        if (startIdx != -1 && endIdx != -1){
            stringBuilder.delete(startIdx, endIdx);
            return stringBuilder.toString();
        }
        else{
            return respString;
        }
    }

    public static String deleteElementWithKeyword(String respString, String tagName, String keyword){
        StringBuilder stringBuilder = new StringBuilder(respString);
        int startIdx = getElementStartIdx(respString, tagName);
        int endIdx = getElementEndIdx(respString, tagName);
        if (startIdx != -1 && endIdx != -1){ // * element found
            if (respString.substring(startIdx, endIdx).contains(keyword)){ // * with keyword
                stringBuilder.delete(startIdx, endIdx);
                return deleteElementWithKeyword(stringBuilder.toString(), tagName, keyword);
            }
            else{ // * element found but without keyword, recursion
                return respString.substring(0, endIdx) + deleteElementWithKeyword(respString.substring(endIdx), tagName, keyword);
            }
        }
        else{ // * element not found
            return respString;
        }
    }

    public static String getElementWithKeyword(String respString, String tagName, String keyword){
        int startIdx = getElementStartIdx(respString, tagName);
        int endIdx = getElementEndIdx(respString, tagName);
        if (startIdx != -1 && endIdx != -1){ // * element found
            if (respString.substring(startIdx, endIdx).contains(keyword)){ // * with keyword
                return respString.substring(startIdx, endIdx);
            }
            else{ // * element found but without keyword, recursion
                return getElementWithKeyword(respString.substring(endIdx), tagName, keyword);
            }
        }
        else{ // * element not found
            return "";
        }
    }

    public static boolean elementWithKeywordExists(String respString, String tagName, String keyword){
        int startIdx = getElementStartIdx(respString, tagName);
        int endIdx = getElementEndIdx(respString, tagName);
        if (startIdx != -1 && endIdx != -1){ // * element found
            if (respString.substring(startIdx, endIdx).contains(keyword)){ // * with keyword
                return true;
            }
            else{ // * element found but without keyword, recursion
                return elementWithKeywordExists(respString.substring(endIdx), tagName, keyword);
            }
        }
        else{ // * element not found
            return false;
        }
    }

    public static String getElement(Response response, String tagName) {
        String respString = response.asPrettyString();
        int startIdx = getElementStartIdx(respString, tagName);
        int endIdx = getElementEndIdx(respString, tagName);
        if (startIdx != -1 && endIdx != -1){
            return respString.substring(startIdx, endIdx);
        }
        else{
            return "";
        }
    }

    public static String getElement(String respString, String tagName) {
        int startIdx = getElementStartIdx(respString, tagName);
        int endIdx = getElementEndIdx(respString, tagName);
        if (startIdx != -1 && endIdx != -1){
            return respString.substring(startIdx, endIdx);
        }
        else{
            return "";
        }
    }

    public static int getElementStartIdx(String respString, String tagName){
        String startTag = "<" + tagName;
        try{
            if (respString.contains(startTag)){ // * start tag exist
                return respString.indexOf(startTag);
            }
            else{
                return -1;
            }
        } catch (RuntimeException e) {
            log.info(e);
            return -1;
        }
    }

    public static int getElementEndIdx(String respString, String tagName){
        String startTag = "<" + tagName;
        tagName = tagName.replaceAll("\\s", "");
        tagName = tagName.replaceAll(">", "");
        String endTag = "</" + tagName + ">";
        String selfClosingTag = "/>";

        try{
            if (respString.contains(startTag)){ // * start tag exist
                int startIdx = respString.indexOf(startTag);
                int endIdx;
                // * Both start tag and end tag exist, and there's no additional starting tag between them
                if (respString.contains(endTag)
                        && respString.indexOf(endTag) > startIdx + startTag.length()
                        && !respString.substring(startIdx + startTag.length(), respString.indexOf(endTag)).contains(startTag)){
                    endIdx = respString.indexOf(endTag) + endTag.length();
                }
                // * Only start tag exist, check for self-closing
                else{
                    endIdx = respString.indexOf(selfClosingTag, startIdx) + selfClosingTag.length();
                }
                return endIdx;
            }
            else{
                return -1;
            }
        } catch (RuntimeException e) {
            log.info(e);
            return -1;
        }
    }

    public static String replaceXmlElement(String xmlString, String elementName, String newElementString) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        Document newDoc = builder.parse(new ByteArrayInputStream(newElementString.getBytes()));
        Element newElement = newDoc.getDocumentElement();

        // Import the newElement into the doc document
        Node importedNode = doc.importNode(newElement, true);

        NodeList nodes = doc.getElementsByTagName(elementName);
        Node oldElement = nodes.item(0);
        oldElement.getParentNode().replaceChild(importedNode, oldElement);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.getBuffer().toString();
    }
}

