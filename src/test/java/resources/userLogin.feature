Feature: Login form on Facebook page

  Scenario: Login with valid credentials
    Given there is open "browser"
    When the user is on the login page
    And when valid "email" is entered to the email field
    And when valid "password" is entered to the password field
    And when login button is clicked
    Then user will see their home page

  Scenario: Login with invalid account
    Given there is open "browser"
    When the user is on the login page
    And when invalid "email" is entered to the email field
    And when invalid "password" is entered to the password field
    And when login button is clicked
    Then user will see the invalid account error message and will not be logged in

  Scenario: Login with invalid password
    Given there is open "browser"
    When the user is on the login page
    And when valid "email" is entered to the email field
    And when invalid "password" is entered to the password field
    And when login button is clicked
    Then user will see the invalid password error message and will not be logged in

  Scenario: Login without entering credentials
    Given there is open "browser"
    When the user is on the login page
    And login button is clicked without entering credentials
    Then user will see the invalid account error message and will not be logged in