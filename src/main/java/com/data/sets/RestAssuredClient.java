package com.data.sets;

import com.helpers.PropertiesLoader;
import com.jayway.restassured.RestAssured;

public class RestAssuredClient implements RestClientSelector {

    public RestAssuredClient() {
        new RestAssuredClientStart("URL_API");
    }

    /**
     * @param name London
     * @return
     */
    @Override
    public String getResultByCityName(String name) {
        return Float.toString(RestAssured.given().get(getRequest(name).toString()).jsonPath().get(JSON_PATH));
    }

    @Override
    public String getResultByCityCode(int code) {
        return Float.toString(RestAssured.given().get(getRequest(code).toString()).jsonPath().get(JSON_PATH));
    }

    class RestAssuredClientStart {
        final private String baseUrl;

        public RestAssuredClientStart(String baseUrl) {
            this.baseUrl = new PropertiesLoader().getProperty(baseUrl);
            ;
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
}
