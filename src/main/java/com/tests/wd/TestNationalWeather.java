package com.tests.wd;

import com.configuration.reporting.DotTestListener;
import com.google.inject.Inject;
import com.pages.NationalWeatherPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestNationalWeather extends FunctionalTest {

    @Inject
    private NationalWeatherPage weatherPage;

    @Test(description = "Getting data from anothe source for compare")
    public void test01GivenOpenedPageWhenGetDataWhenCompare() {
        weatherPage.open();
        assertTrue(weatherPage.isOpen(), "Page does not opened");

        DotTestListener.log("Test complete.");
    }
}
