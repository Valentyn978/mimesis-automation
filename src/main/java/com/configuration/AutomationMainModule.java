package com.configuration;

import com.configuration.driver.PageDriver;
import com.configuration.driver.PageDriverImpl;
import com.configuration.driver.WebDriverSetUp;
import com.google.inject.AbstractModule;
import com.helpers.PropertiesLoader;
import org.testng.ITestContext;

public class AutomationMainModule extends AbstractModule {

    private final PropertiesLoader propertyLoader;
    private final ITestContext testContext;

    public AutomationMainModule(ITestContext testContext, PropertiesLoader propertyLoader) {
        this.propertyLoader = propertyLoader;
        this.testContext = testContext;
    }

    @Override
    protected void configure() {
        WebDriverSetUp driverSetUp = new WebDriverSetUp();
        bind(PropertiesLoader.class).toInstance(propertyLoader);
        bind(PageDriver.class).toInstance(new PageDriverImpl(driverSetUp.getDriver(testContext)));
    }
}