Feature: Login Data Driven with Excel

  Scenario Outline: Login Data Driven with Excel
    Given the user navigates to the login page
    When the user enters email and password as present in excel sheet "<row_index>"
    And the user clicks on the Login button
    Then the user should be redirected to MyAccountPage only with valid credentials

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
      |         6 |
      |         7 |
