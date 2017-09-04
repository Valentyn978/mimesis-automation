package com.tests;

import com.configuration.reporting.TestLogger;
import com.google.inject.Inject;
import com.pages.JsonPlaceholder;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.testng.annotations.*;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class TestUsersGetActions extends AbstractTest {

    private static final Logger LOGGER = TestLogger.getLogger(TestUsersGetActions.class);

    private String userName;
    private String userId;
    private String foundFullName;

    @Inject
    private JsonPlaceholder jsonPlaceholder;

    @Test(description = "Try to get full user name by first name")
    @Parameters("userName")
    public void test01GivenListOfUsersWhenFoundUserThenCheck(String userName) {

        this.userName = userName;

        //Check is env. work
        jsonPlaceholder.open();
        assertTrue(jsonPlaceholder.isOpen(), "Home page work environment does not open!");

        JSONArray jsonObject = getJsonArrayByGetRequest("users");
        String result = getJsonPathValue(String.valueOf(jsonObject), "$..name");

        for (String n : result.split(",")) {
            if (n.contains(this.userName)) foundFullName = n;
        }

        assertTrue(result.contains(this.userName), String.format("User with name %s does not found.", this.userName));
        LOGGER.info(String.format("User with name: %s is found. Full name: %s", this.userName, foundFullName));
    }

    @Test(description = "Try to get user ID by full user name",
            dependsOnMethods = "test01GivenListOfUsersWhenFoundUserThenCheck")
    public void test02GivenUserListWhenGetUserIdThenCheck() {
        JSONArray jsonObject = getJsonArrayByGetRequest("users");
        userId = getJsonPathValue(String.valueOf(jsonObject), String.format("$..[?(@.name == '%s')].id",
                foundFullName.replace("\"", "")));

        assertNotNull(userId, String.format("UserID for user Name: %s does not found.", foundFullName));
        LOGGER.info(String.format("User ID: %s is found for user Name: %s", userId, foundFullName));
    }

    @Test(description = "Getting all user data by user ID",
            dependsOnMethods = "test02GivenUserListWhenGetUserIdThenCheck")
    public void test03GivenListOfUsersWhenFindUserByIdThenCheck() {
        assertTrue(jsonPlaceholder.isOpen(), "Home page work environment does not open!");
        JSONArray users = getJsonArrayByGetRequest("posts?userId="
                + userId.replace("[", "").replace("]", ""));

        assertNotNull(users, String.format("User name: %s does not have posts.", userName));
        LOGGER.info(String.format("Given user by ID: %s have data: %s", userId, users));
    }
}