package com.sneo.requests;

import io.restassured.response.Response;

import java.util.Map;

import static com.sneo.specbuilder.RestSpecBuilder.getRequestSpec;
import static com.sneo.specbuilder.RestSpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

/**
 * Class contains common REST Request Methods
 *
 * @author ikumar
 */

public class RestRequest {

    public static Response get(String path) {
        return given(getRequestSpec()).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String baseURI, String path) {
        return given(getRequestSpec(baseURI)).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, Map headerMap) {
        return given(getRequestSpec()).
                headers(headerMap).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String path, Map paramsMap, Map headerMap) {
        return given(getRequestSpec()).
                params(paramsMap).
                headers(headerMap).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path, Object requestBody) {
        return given(getRequestSpec()).
                body(requestBody).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path, Map headerMap, Object requestBody) {
        return given(getRequestSpec()).
                headers(headerMap).
                body(requestBody).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }



    public static Response update(String path, Object requestBody) {
        return given(getRequestSpec()).
                body(requestBody).
                when().put(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response delete(String path) {
        return given(getRequestSpec()).
                when().delete(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response delete(String baseURI, String path) {
        return given(getRequestSpec(baseURI)).
                when().delete(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }
}
