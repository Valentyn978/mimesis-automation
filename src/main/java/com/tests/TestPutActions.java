package com.tests;

import com.configuration.reporting.TestLogger;
import com.data.sets.DataSets;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TestPutActions extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestPutActions.class);

    @Inject
    private DataSets sets;


    @Test(description = "Negative test for PUT")
    public void test01GivenWorkEnvironmentWhenUpdateExistingCommentThenCheck() {
        restSender.getResponseByPut("posts", sets.getPutDataUser());

        assertThat(restSender.statusCode, is(500));
        LOGGER.info("Negative test. Action for update existing comment is Fail. Test complete.");
    }

}