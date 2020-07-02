Feature: Try to connect by REST to services and compare data with UI
  Scenario: Get data from Service
    Given "ServiceUser" open connection to the Service
    When He checks if the service connection is stable
    Then data is getting and save

  Scenario: Get data from UI and compare with data from Service
    Given web page opened by the "UiUser"
    When He gets data from UI is correctly
    Then "UiUser" compare data with Service