Feature: Account Resgisteration

  @regression
  Scenario: Successful Account Registeration
    Given the user navigated to Register Account Page
    When the user enters details into the below fields
      | firstName | Jane       |
      | lastName  | Kenedy     |
      | telephone | 1234567890 |
      | password  | tess@123   |
    And the user selects Privacy Policy
    And the user clicks on Continue button
    Then the user account should get created successfully
