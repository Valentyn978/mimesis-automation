package com.data.sets;

import com.configuration.MimesisConfig;
import com.data.RestClients;
import com.jayway.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class RestAssuredClient implements RestClients {

    public RestAssuredClient() {
        new RestAssuredClientStart();
    }

    /**
     * @param name London
     * @return String
     */
    @Override
    public String getResultByCityName(String name) {
        return Float.toString(RestAssured.given().get(getRequest(name).toString()).jsonPath().get(JSON_PATH));
    }

    /**
     * @param code
     * @return
     */
    @Override
    public String getResultByCityCode(int code) {
        return Float.toString(RestAssured.given().get(getRequest(code).toString()).jsonPath().get(JSON_PATH));
    }

    class RestAssuredClientStart {
        final private String baseUrl;

        RestAssuredClientStart() {
            MimesisConfig config = ConfigFactory.create(MimesisConfig.class);
            this.baseUrl = config.urlApi();
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
