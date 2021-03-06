package com.tests;

import com.data.DataSets;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestPostActions extends AbstractTest {

    private static final Logger LOGGER = LogManager.getLogger(TestPostActions.class);

    @Inject
    private DataSets sets;


    @Test(description = "Negative test for POST")
    public void test01GivenWorkEnvironmentWhenCreateNewCommentThenCheck() {
        restSender.getResponseByPost("posts", sets.getPostDataUser());

        assertEquals(restSender.statusCode, 500);
        LOGGER.info("Negative test. Action for create new comment is Fail. Test complete.");
    }
}