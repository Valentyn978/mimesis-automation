package com.tests.wd;

import com.configuration.reporting.DotTestListener;
import com.google.inject.Inject;
import com.pages.NationalWeatherPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TestNationalWeather extends FunctionalTest {

    public static Double tempFromUI = null;

    @Inject
    private NationalWeatherPage weatherPage;

    @Test(description = "Getting data from another source for compare", groups = {"smoke", "regression"})
    public void test01GivenOpenedPageWhenGetDataThenCompare() {
        weatherPage.open();
        assertTrue(weatherPage.isOpen(), "Page does not opened");

        String tempToDayFromUI = weatherPage.getTempToDay();

        assertNotNull(tempToDayFromUI, "Can't getting value from UI");

        tempToDayFromUI = tempToDayFromUI.substring(0, tempToDayFromUI.length() - 1);

        tempFromUI = Double.parseDouble(tempToDayFromUI);

        DotTestListener.log("Test complete. Temperature is getting: " + tempToDayFromUI);
    }
}
