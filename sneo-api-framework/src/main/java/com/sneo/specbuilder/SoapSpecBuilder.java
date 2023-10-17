package com.sneo.specbuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.sneo.core.Configuration.BASE_URI;

/**
 * Class contains SOAP RequestSpecification and ResponseSpecification
 *
 * @author ikumar
 */
@Log4j2
public class SoapSpecBuilder extends AbstractBuilder {

    public static RequestSpecification getRequestSpec() {
        log.info("=====================REQUEST START=====================");
        //requestWriter = new StringWriter();
        //requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
        byteArrayOutputStream = new ByteArrayOutputStream();
        requestCapture = new PrintStream(byteArrayOutputStream);


        return new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                setContentType("text/xml").
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }

    public static RequestSpecification getRequestSpec(String baseURI) {
        log.info("=====================REQUEST START=====================");
        byteArrayOutputStream = new ByteArrayOutputStream();
        requestCapture = new PrintStream(byteArrayOutputStream);
        //requestWriter = new StringWriter();
        //requestCapture = new PrintStream(new WriterOutputStream(requestWriter));

        return new RequestSpecBuilder().
                setBaseUri(baseURI).
                setContentType("text/xml").
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }



}
