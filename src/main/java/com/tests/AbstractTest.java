package com.tests;


import com.configuration.AutomationMainModule;
import com.configuration.driver.PageDriver;
import com.configuration.reporting.GivenWhenThenTestListener;
import com.configuration.reporting.TestHtmlReporter;
import com.configuration.reporting.TestListener;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.helpers.RestSender;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.*;


@Listeners({TestListener.class, TestHtmlReporter.class, GivenWhenThenTestListener.class})
public class AbstractTest {

    public RestSender restSender = new RestSender("MAIN_URL");

    @Inject
    public Injector injector;

    public static CloseableHttpClient closeableClient;

    @BeforeSuite
    public void setUpSuite(ITestContext context) {
        closeableClient = restSender.setUpHttpClient();
    }

    @AfterSuite
    public void tearDownSuite() {
        restSender.closeHttpClient();
    }

    @BeforeTest
    public void setUpBeforeTest(ITestContext context) {
        injector = Guice.createInjector(new AutomationMainModule(context));
        injector.injectMembers(this);
    }

    @AfterTest
    public void tearDownAfterTest() {
        if (null != injector)
            injector.getInstance(PageDriver.class).quit();
    }

    public String getJsonPathValue(String responseContent, String jsonPath) {
        String actualValue = null;
        try {
            Object returnValue = JsonPath.read(String.valueOf(responseContent), jsonPath);
            if ((returnValue instanceof Character[])) {
                actualValue = returnValue.toString();
            } else {
                actualValue = String.valueOf(returnValue);
            }
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        }
        if (actualValue == null)
            throw new AssertionError("No value '" + jsonPath + "' in response");
        return actualValue;
    }

    public JSONArray getJsonArrayByGetRequest(String urlPath) {
        JSONArray jsonArray = null;
        String responseByGet = restSender.getResponseByGet(urlPath);
        try {
            jsonArray = new JSONArray(responseByGet);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
