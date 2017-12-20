Feature: Search check
        As a user I should able given result.

  Scenario: I put search phrase and given result
        Given I navigate to "https://www.google.com.ua/"
        And I put value "Cucumber" into input field having id "lst-ib" and press Enter button
        Then I wait 3 seconds for element having xpath "//*[@id='hdtb-msb-vis']/div[1]" to display
        Then I take screenshot
        Then I close browser