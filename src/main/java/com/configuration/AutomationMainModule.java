package com.configuration;

import com.configuration.driver.PageDriver;
import com.configuration.driver.PageDriverImpl;
import com.configuration.driver.WebDriverSetUp;
import com.data.sets.RestAssuredClient;
import com.data.sets.RestClientSelector;
import com.data.sets.RestClosableClient;
import com.google.inject.AbstractModule;
import org.testng.ITestContext;
import org.testng.TestNGException;

import java.util.Objects;

public class AutomationMainModule extends AbstractModule {

    private ITestContext testContext;

    public AutomationMainModule(ITestContext testContext) {
        this.testContext = testContext;
    }

    @Override
    protected void configure() {
        String httpClientType = null;
        try {
            httpClientType = testContext.getCurrentXmlTest().getParameter("HttpClient");
        } catch (TestNGException ignore){}
        switch (Objects.requireNonNull(httpClientType)) {
            case "closable":
                bind(RestClientSelector.class).toInstance(new RestClosableClient("URL_API"));
                break;
            case "assured":
                bind(RestClientSelector.class).toInstance(new RestAssuredClient());
                break;
        }
        bind(PageDriver.class).toInstance(new PageDriverImpl(new WebDriverSetUp().getDriver(testContext)));
    }
}