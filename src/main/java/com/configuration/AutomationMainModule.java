package com.configuration;

import com.configuration.driver.PageDriver;
import com.configuration.driver.PageDriverImpl;
import com.configuration.driver.WebDriverSetUp;
import com.data.DataSets;
import com.data.RestClientSelector;
import com.data.sets.DataSetFirst;
import com.data.sets.DataSetSecond;
import com.data.sets.RestAssuredClient;
import com.data.sets.RestClosableClient;
import com.google.inject.AbstractModule;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestContext;
import org.testng.TestNGException;

public class AutomationMainModule extends AbstractModule {

    private ITestContext testContext;

    public AutomationMainModule(ITestContext testContext) {
        this.testContext = testContext;
    }

    @Override
    protected void configure() {
        String httpClientType = null;
        String dataSet = null;
        try {
            dataSet = testContext.getCurrentXmlTest().getParameter("dataSet");
            bind(MimesisConfig.class).toInstance(ConfigFactory.create(MimesisConfig.class));
            httpClientType = testContext.getCurrentXmlTest().getParameter("HttpClient");
        } catch (TestNGException ignore){}
        if (null != httpClientType) {
            switch (httpClientType) {
                case "closable":
                    bind(RestClientSelector.class).toInstance(new RestClosableClient());
                    break;
                case "assured":
                    bind(RestClientSelector.class).toInstance(new RestAssuredClient());
                    break;
            }
        } else {
            bind(RestClientSelector.class).toInstance(new RestClosableClient());
        }
        if (null != dataSet) {
            switch (dataSet){
                case "firstDataSet":
                    bind(DataSets.class).toInstance(new DataSetFirst());
                    break;
                case "secondDataSet":
                    bind(DataSets.class).toInstance(new DataSetSecond());
            }
        } else {
            bind(DataSets.class).toInstance(new DataSetFirst());
        }
        bind(PageDriver.class).toInstance(new PageDriverImpl(new WebDriverSetUp().getDriver(testContext)));
    }
}