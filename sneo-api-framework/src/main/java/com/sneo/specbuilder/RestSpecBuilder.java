package com.sneo.specbuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.sneo.core.Configuration.BASE_URI;

/**
 * Class contains REST RequestSpecification and ResponseSpecification
 *
 * @author ikumar
 */

@Log4j2
public class RestSpecBuilder extends AbstractBuilder {


    public static RequestSpecification getRequestSpec() {
        log.info("=====================REQUEST=====================");
        byteArrayOutputStream = new ByteArrayOutputStream();
        requestCapture = new PrintStream(byteArrayOutputStream);

        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(BASE_URI).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();

        return requestSpecification;
    }

    public static RequestSpecification getRequestSpec(String baseURI) {
        log.info("=====================REQUEST=====================");
        byteArrayOutputStream = new ByteArrayOutputStream();
        requestCapture = new PrintStream(byteArrayOutputStream);

        return new RequestSpecBuilder().
                setBaseUri(baseURI).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }

    public static RequestSpecification getRequestSpec(String baseURI, ContentType contentType) {
        log.info("=====================REQUEST=====================");
        byteArrayOutputStream = new ByteArrayOutputStream();
        requestCapture = new PrintStream(byteArrayOutputStream);

        return new RequestSpecBuilder().
                setBaseUri(baseURI).
                setContentType(contentType).
                log(LogDetail.ALL).
                addFilter(new RequestLoggingFilter(requestCapture)).
                build();
    }
}
