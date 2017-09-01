package com.tests;

import com.configuration.reporting.TestLogger;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestUsersGetActions extends AbstractTest {

    private static final String USER_NAME = "Nicholas";
    private String userId = "";
    private String foundFullName = "";
    private static final Logger LOGGER = TestLogger.getLogger(TestUsersGetActions.class);

    @Test
    public void test01GivenListOfUsersWhenFoundUserThenCheck() {
        JSONArray jsonObject = getJsonArrayByGetRequest("users");
        String result = getJsonPathValue(String.valueOf(jsonObject), "$..name");

        for (String n : result.split(",")) {
            if (n.contains(USER_NAME)) foundFullName = n;
        }

        Assert.assertTrue(result.contains(USER_NAME), String.format("User with name %s does not found.", USER_NAME));
        LOGGER.info(String.format("User with name: %s is found. Full name: %s", USER_NAME, foundFullName));
    }

    @Test(dependsOnMethods = "test01GivenListOfUsersWhenFoundUserThenCheck")
    public void test02GivenUserListWhenGetUserIdThenCheck() {
        JSONArray jsonObject = getJsonArrayByGetRequest("users");
        userId = getJsonPathValue(String.valueOf(jsonObject), String.format("$..[?(@.name == '%s')].id",
                foundFullName.replace("\"", "")));

        Assert.assertNotNull(userId, String.format("UserID for user Name: %s does not found.", foundFullName));
        LOGGER.info(String.format("User ID: %s is found for user Name: %s", userId, foundFullName));
    }

    @Test(dependsOnMethods = "test02GivenUserListWhenGetUserIdThenCheck")
    public void test03GivenListOfUsersWhenFindUserByIdThenCheck() {
        JSONArray users = getJsonArrayByGetRequest("posts?userId=" + userId.replace("[", "").replace("]", ""));

        Assert.assertNotNull(users, String.format("User name: %s does not have posts.", USER_NAME));
        LOGGER.info(String.format("Given user by ID: %s have data: %s", userId, users));
    }
}