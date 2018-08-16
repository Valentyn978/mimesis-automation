package com.tests.selenium;

import com.configuration.reporting.DotTestListener;
import com.google.inject.Inject;
import com.pages.WelcomePage;
import com.tests.AbstractTest;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertTrue;

@Test
public class TestDefaultSelenium extends AbstractTest {

    @Inject
    private WelcomePage welcomePage;

    @Test(description = "Try to check for page is open")
    public void test01GivenWorkEnvironmentWhenOpenWelcomePageThenCheck() {
        try {
            welcomePage.open();
            assertTrue(welcomePage.isOpen(), "Welcome Page does not open!");
            DotTestListener.log("Test is complete");
        } catch (NullPointerException e) {
            throw new SkipException("Web Driver does not connected to the Browser. " + Arrays.toString(e.getStackTrace()));
        }
    }
}
