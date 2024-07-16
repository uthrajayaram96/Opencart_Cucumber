Feature: Login with Valid Credentials

  @sanity @regression
  Scenario: Successful Login with valid Credentials
    Given the user navigates to the login page
    When the user enters email as "test_automation_practice@email.com" and password as "test@#123"
    And the user clicks on the Login button
    Then the user should be redirected to MyAccountPage

  @regression
  Scenario Outline: Login Data Driven
    Given the user navigates to the login page
    When the user enters email as "<email>" and password as "<password>"
    And the user clicks on the Login button
    Then the user should be redirected to MyAccountPage

    Examples: 
      | email                              | password  |
      | pavanoltraining@gmail.com          | test@123  |
      | abc123@gmail.com                   | test@123  |
      | test_automation_practice@email.com | test@#123 |
