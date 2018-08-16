package com.tests.wd;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class FunctionalTest {

    @BeforeSuite
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        } else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null) {
            basePath = "/current/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null) {
            baseHost = "https://openweathermap.org";
        }

        RestAssured.baseURI = baseHost;
    }
}
