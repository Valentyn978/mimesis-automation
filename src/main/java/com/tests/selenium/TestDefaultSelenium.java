package com.tests.selenium;

import com.configuration.reporting.DotTestListener;
import com.data.sets.DataSets;
import com.google.inject.Inject;
import com.pages.WelcomePage;
import com.tests.AbstractTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestDefaultSelenium extends AbstractTest {

    public static final String DESCRIPTION_TEST2 = "Check is translator work right";
    public static final String DESCRIPTION_TEST1 = "Try to check for page is open";
    @Inject
    private WelcomePage welcomePage;
    @Inject
    private DataSets sets;

    @Test(description = DESCRIPTION_TEST1)
    public void test01GivenWorkEnvironmentWhenOpenWelcomePageThenCheck() {
        welcomePage.open();
        assertTrue(welcomePage.isOpen(), "Welcome Page does not open!");

        DotTestListener.log(String.format("Test '%s' is complete", DESCRIPTION_TEST1));
    }

    @Test(description = DESCRIPTION_TEST2)
    public void test02GivenTranslatorWhenPutTextThenCheckResult() {
        welcomePage.putTextToSourceArea(sets.getSourceDataTranslator());

        assertEquals(welcomePage.getTextFromResultArea(), sets.getResultDataTranslator(),
                "Result of translator does not right.");
        DotTestListener.log(String.format("Test '%s' is complete", DESCRIPTION_TEST2));
    }
}
