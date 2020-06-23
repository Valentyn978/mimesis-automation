package com.tests;

import com.data.DataSets;
import com.google.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestPutActions extends AbstractTest {

    private static final Logger LOGGER = LogManager.getLogger(TestPutActions.class);

    @Inject
    private DataSets sets;


    @Test(description = "Negative test for PUT")
    public void test01GivenWorkEnvironmentWhenUpdateExistingCommentThenCheck() {
        restSender.getResponseByPut("posts", sets.getPutDataUser());

        assertEquals(restSender.statusCode, 500);
        LOGGER.info("Negative test. Action for update existing comment is Fail. Test complete.");
    }

}