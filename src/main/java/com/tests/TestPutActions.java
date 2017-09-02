package com.tests;

import com.configuration.reporting.TestLogger;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import static com.helpers.DataProcessing.getRandomString;
import static org.testng.Assert.assertEquals;


public class TestPutActions extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestPutActions.class);
    private static final String UPDATE_COMMENT = "{\n" +
            "    \"postId\": 1,\n" +
            "    \"id\": 5,\n" +
            "    \"name\": \"vero eaque aliquid doloribus et culpa\",\n" +
            "    \"email\": \"Hayden@althea.biz\",\n" +
            "    \"body\": \"harum non quasi et ratione\\ntempore iure ex voluptates in ratione" +
            "   \\nharum architecto fugit inventore cupiditate\\nvoluptates magni quo et\"\n" + getRandomString(5);


    @Test
    public void test01GivenWorkEnvironmentWhenUpdateExistingCommentThenCheck() {
        restSender.getResponseByPut("posts", UPDATE_COMMENT);

        assertEquals(restSender.statusCode, 400);
        LOGGER.info("Negative test. Action for update existing comment is Fail. Test complete.");
    }

}