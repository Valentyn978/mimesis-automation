package com.tests.wd;


import org.testng.annotations.Test;

public class TestOpenWeatherMap extends FunctionalTest {


    @Test(description = "Simple ping")
    public void test01GivenBaseUrlWhenSendRequestThenCheckResponseCode() {
//        given().when().get("/data/2.5/forecast?id=524901&APPID=f31cce7ac5a1983cd83db0da00b2e0e1")
//                .then().statusCode(200).assertThat().body("main.temp", equalTo(306));// https://semaphoreci.com/community/tutorials/testing-rest-endpoints-using-rest-assured
    }

//    @Test(description = "Get weather for today", dependsOnMethods = "test01GivenBaseUrlWhenSendRequestThenCheckResponseCode")
//    public void test02GivenLinkForGetDataWhenGetDataThenCheckResponse() {
//        given().when().get("/data/2.5/weather?zip=94040,us").then().statusCode(200);
//    }
}
