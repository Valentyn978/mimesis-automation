package com.tests;


import com.configuration.AutomationMainModule;
import com.configuration.driver.PageDriverImpl;
import com.configuration.driver.WebDriverSetUp;
import com.configuration.reporting.GivenWhenThenTestListener;
import com.configuration.reporting.TestHtmlReporter;
import com.configuration.reporting.TestListener;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.helpers.PropertiesLoader;
import com.helpers.RestSender;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


@Listeners({TestListener.class, TestHtmlReporter.class, GivenWhenThenTestListener.class})
public class AbstractTest {

    protected static RestSender restSender;

    @Inject
    protected Injector injector;
    @Inject
    public WebDriverSetUp driverSetUp;

    public static CloseableHttpClient closeableClient;

    @BeforeSuite
    public void setUpInjector(ITestContext context) {
        injector = Guice.createInjector(new AutomationMainModule(context, new PropertiesLoader()));
        injector.injectMembers(this);
        restSender = new RestSender("MAIN_URL");
        closeableClient = restSender.setUpHttpClient();
    }

    @AfterSuite
    public void tearDown(ITestContext testContext) {
        restSender.closeHttpClient();
        driverSetUp.quiteDriver();
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
