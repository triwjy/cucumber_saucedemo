Feature: Log in to the application

  Background:
    Given User is on Swag Labs login page

  @Login @Positive
  Scenario: Login to application as valid user
    When User enter the standard_user username
    And User enter valid password
    And Click on Login
    Then Products Page should be displayed

  @Login @Negative
  Scenario: Login to application as invalid user
    When User enter the standard_user username
    And User enter invalid password
    And Click on Login
    Then Error Message should be displayed

  @Login @DDT
  Scenario Outline: User login to Swag Labs
    When User enter the <username> as username
    And User enter <password> as password
    And Click on Login
    Then Verify login is <status>
    Examples:
      | username      | password     | status  |
      | standard_user | secret_sauce | success |
      | standard_user | invalid_pwd  | fail    |
