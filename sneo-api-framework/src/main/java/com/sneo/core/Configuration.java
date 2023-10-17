package com.sneo.core;

import lombok.extern.log4j.Log4j2;
import org.testng.xml.XmlTest;

import java.util.Map;

import static com.sneo.datautils.PropertyUtils.getValue;

/**
 * Class contains all the properties required to configure the environment variables
 *
 * @author ikumar
 */
@Log4j2
public class Configuration {

    /* Environment Configuration */
    public static String ENVIRONMENT;
    public static String BASE_URI;
    public static String SOAP_PATH = getValue("SOAP_PATH");
    public static String VERSION;

    public static void setTestNGParameters(XmlTest xmlTest) {
        Map<String, String> XML_PARAMS_MAP = xmlTest.getAllParameters();
        // Assigning all the XML parameters to the Base Class Global variables
        ENVIRONMENT = XML_PARAMS_MAP.get("environment") != null ? XML_PARAMS_MAP.get("environment") : getValue("environment");
        VERSION = XML_PARAMS_MAP.get("version") != null ? XML_PARAMS_MAP.get("version") : getValue("version");
        ENVIRONMENT = ENVIRONMENT.toUpperCase();
        VERSION = VERSION.toUpperCase();
    }

    public static void setURL() {

        BASE_URI = getValue(ENVIRONMENT + "_BASE_URI");
    }
}
