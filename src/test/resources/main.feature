Feature: Gmail Login
        As a user I should able to login into Gmail.

  Scenario: I login with valid credential
        Given I navigate to "http://some_test_url.com/login"
        And I enter "UserName" into input field having id "username"
        And I enter "UserPassword" into input field having id "password"
        When I click on element having class "radius"
        Then I should get logged-in
        Then I close browser