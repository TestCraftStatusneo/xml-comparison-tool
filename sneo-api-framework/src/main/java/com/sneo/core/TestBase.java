package com.sneo.core;

import com.sneo.listeners.report.ExtentListener;
import com.sneo.listeners.report.ExtentManager;
import com.sneo.specbuilder.AbstractBuilder;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;


/**
 * Class needs to be extended by all Test classes
 *
 * @author ikumar
 */
@Listeners({ExtentListener.class})
@Log4j2
public class TestBase {

    protected static Response response;

    @BeforeTest(alwaysRun = true)
    public void startTest(XmlTest xmlTest) {
        Configuration.setTestNGParameters(xmlTest);
        Configuration.setURL();
        ExtentListener.extent = ExtentManager.createInstance();
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        log.info("Starting test: " + m.getName());

    }

    protected static void logRequestAndResponse() {
        if (AbstractBuilder.getByteArrayOutputStream() != null) {
            logToReport("<b>" + "Request:" + "</b>");
            logCodeBlock(AbstractBuilder.getByteArrayOutputStream().toString());
            AbstractBuilder.setByteArrayOutputStream(null);
        }
        if (response != null) {
            logToReport("<b>" + "Response:" + "</b>");
            logCodeBlock(response.headers().toString() + "\n\n" + response.asPrettyString());
            response = null;
        }
    }

    public static void logToReport(String info) {
        ExtentListener.logInfo(info);
    }

    public static void logCodeBlock(String code) {
        ExtentListener.logCodeBlock(code);
    }


    @AfterMethod
    public void afterMethod() {

        //logRequestAndResponse();
    }


}
