package com.sneo.specbuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class contains REST RequestSpecification and ResponseSpecification
 *
 * @author ikumar
 */

@Log4j2
public abstract class AbstractBuilder {


    //protected static StringWriter requestWriter;
    protected static PrintStream requestCapture;
    @Getter
    @Setter
    protected static ByteArrayOutputStream byteArrayOutputStream;


    public static ResponseSpecification getResponseSpec() {
        log.info("=====================RESPONSE=====================");
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
