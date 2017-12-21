package com.tests;

import com.configuration.reporting.TestLogger;
import com.data.sets.DataSets;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;


public class TestPostActions extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestPostActions.class);

    @Inject
    private DataSets sets;


    @Test(description = "Negative test for POST")
    public void test01GivenWorkEnvironmentWhenCreateNewCommentThenCheck() {
        restSender.getResponseByPost("posts", sets.getPostDataUser());

        assertThat(restSender.statusCode, is(400));
        LOGGER.info("Negative test. Action for create new comment is Fail. Test complete.");
    }

    @Test(description = "Simple test for show Hamcrest library")
    public void test02GivenArrayWhenCompareItemsThenCheck() {
        List<Integer> list = Arrays.asList(5, 2, 4);

        assertThat(list, everyItem(greaterThan(2)));
    }

    @Test(description = "Show as exercise NullPointer exception")
    public void test03GivenEmptyStringWhenUseSpecificMethodThenCheck() {
        String stringToTest = "";

        assertThat(stringToTest, isEmptyOrNullString());
    }

}