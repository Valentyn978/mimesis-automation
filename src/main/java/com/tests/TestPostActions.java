package com.tests;

import com.configuration.reporting.TestLogger;
import com.data.DataSets;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestPostActions extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestPostActions.class);

    @Inject
    private DataSets sets;


    @Test(description = "Negative test for POST")
    public void test01GivenWorkEnvironmentWhenCreateNewCommentThenCheck() {
        restSender.getResponseByPost("posts", sets.getPostDataUser());

        assertEquals(restSender.statusCode, 400);
        LOGGER.info("Negative test. Action for create new comment is Fail. Test complete.");
    }
}