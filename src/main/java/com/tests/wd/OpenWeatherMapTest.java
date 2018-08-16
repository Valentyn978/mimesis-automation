package com.tests.wd;


import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class OpenWeatherMapTest extends FunctionalTest {


    @Test
    public void basicPingTest() {
        given().when().get("/").then().statusCode(200);
    }
}
