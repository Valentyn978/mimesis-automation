package com.tests.wd;


import com.configuration.reporting.TestLogger;
import com.data.sets.RestClientSelector;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.tests.wd.TestNationalWeather.tempFromUI;

public class TestCompareValueFromApi extends FunctionalTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestCompareValueFromApi.class);
    private String result = null;

    @Inject
    RestClientSelector restClient;


    @Test(description = "Get temperature for London", groups = {"smoke", "regression"})
    public void test01GivenBaseUrlWhenSendRequestThenCheckResult() {
        result = restClient.getResultByCityName("London");

        Assert.assertNotNull(result, "Value from API is empty.");
        LOGGER.info("Test complete, value form API " + result);
    }

    @Test(description = "Compare value from API and UI",
            dependsOnMethods = "test01GivenBaseUrlWhenSendRequestThenCheckResult", groups = {"smoke", "regression"})
    public void test02GivenValueFromUiWhenCompareThenPutReport() {
        // Compare with result from UI
        makeAssertionsForCompare();

        LOGGER.info("Test complete value form API and UI is the same. " + result);
    }

    @Test(description = "Get temperature for London by city code", groups = {"regression"})
    public void test03GivenBaseUrlWhenSendRequestThenCheckResult() {
        result = null;
        int cityCode = 2643743;
        result = restClient.getResultByCityCode(cityCode);

        LOGGER.info(String.format("Using city code: %s for London", cityCode));

        Assert.assertNotNull(result, "Value from API is empty.");
        LOGGER.info("Test complete, value form API " + result);
    }

    @Test(description = "Compare value from API by city code and UI",
            dependsOnMethods = "test03GivenBaseUrlWhenSendRequestThenCheckResult", groups = {"regression"})
    public void test04GivenValueFromUiWhenCompareThenPutReport() {
        // Compare with result from UI
        makeAssertionsForCompare();

        LOGGER.info("Test complete value form API and UI is the same. " + result);
    }

    @Test(description = "Negative test for API", dependsOnMethods = "test01GivenBaseUrlWhenSendRequestThenCheckResult",
            priority = 5, groups = {"negative", "regression"})
    public void test05GivenInvalidValueWhenSendRequestThenCheckResult() {
        result = null;
        boolean resultFlag = false;
        try {
            result = restClient.getResultByCityName("Londoniiiiiiii");
        } catch (Exception e) {
            resultFlag = true;
        }

        Assert.assertNull(result, "Value from API is not empty.");

        Assert.assertTrue(resultFlag, "Result is valid and contains: " + result);

        LOGGER.info("Test complete, value form API " + result);
    }

    private void makeAssertionsForCompare() {
        Assert.assertEquals(
                (int) Math.round(tempFromUI),
                (int) Math.round(((Double.parseDouble(result) - 273) * 1.8) + 32),
                "Values does not equals."
        );
    }
}
