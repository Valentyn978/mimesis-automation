package com.tests.wd;

import com.configuration.AutomationMainModule;
import com.configuration.reporting.GivenWhenThenTestListener;
import com.configuration.reporting.TestHtmlReporter;
import com.configuration.reporting.TestListener;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, TestHtmlReporter.class, GivenWhenThenTestListener.class})
abstract class FunctionalTest {

    @Inject
    public Injector injector;

    @BeforeTest
    public void setup(ITestContext context) {
        injector = Guice.createInjector(new AutomationMainModule(context));
        injector.injectMembers(this);
    }
}
