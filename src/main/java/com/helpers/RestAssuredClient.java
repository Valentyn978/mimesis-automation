package com.helpers;

import com.jayway.restassured.RestAssured;

public class RestAssuredClient {
    final private String baseUrl;

    public RestAssuredClient(String baseUrl) {
        this.baseUrl = baseUrl;
        setUp();
    }

    private void setUp() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = baseUrl;
        }

        RestAssured.baseURI = baseHost;
    }
}
