package com.tests;

import com.configuration.reporting.TestLogger;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static com.helpers.DataProcessing.getRandomString;
import static org.testng.Assert.assertEquals;


public class TestPostActions extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestPostActions.class);
    private static final String NEW_COMMENT = "{\n" +
            "    \"postId\": 1,\n" +
            "    \"name\": \"id automation tests\",\n" +
            "    \"email\": \"automation@mail.com\",\n" +
            "    \"body\": \"New comment: " + getRandomString(5) + "\n" +
            "  }";


    @Test(description = "Negative test for POST")
    public void test01GivenWorkEnvironmentWhenCreateNewCommentThenCheck() {
        restSender.getResponseByPost("posts", NEW_COMMENT);

        assertEquals(restSender.statusCode, 400);
        LOGGER.info("Negative test. Action for create new comment is Fail. Test complete.");
    }
}