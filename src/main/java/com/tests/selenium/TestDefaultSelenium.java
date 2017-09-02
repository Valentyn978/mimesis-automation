package com.tests.selenium;

import com.configuration.reporting.DotTestListener;
import com.google.inject.Inject;
import com.pages.WelcomePage;
import com.tests.AbstractTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class TestDefaultSelenium extends AbstractTest {

    @Inject
    private WelcomePage welcomePage;

    @Test
    public void test01GivenWorkEnvironmentWhenOpenWelcomePageThenCheck() {
        welcomePage.open();
        assertTrue(welcomePage.isOpen(), "Welcome Page does not open!");

        DotTestListener.log("Test is complete");
    }
}
