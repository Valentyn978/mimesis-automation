package com.configuration;

import com.configuration.driver.PageDriver;
import com.configuration.driver.PageDriverImpl;
import com.configuration.driver.WebDriverSetUp;
import com.google.inject.AbstractModule;
import org.testng.ITestContext;

public class AutomationMainModule extends AbstractModule {

    private final ITestContext testContext;

    public AutomationMainModule(ITestContext testContext) {
        this.testContext = testContext;
    }

    @Override
    protected void configure() {
        bind(PageDriver.class).toInstance(new PageDriverImpl(new WebDriverSetUp().getDriver(testContext)));
    }
}