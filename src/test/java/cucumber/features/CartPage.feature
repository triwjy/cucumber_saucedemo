Feature: Show added items on cart page

  Background:
    Given User is on products page as standard_user

  @Cart @Positive
  Scenario: Added items shown on cart page
    When User add Sauce Labs Bolt T-Shirt and Sauce Labs Backpack to cart
    And User go to cart page
    Then Cart page should show the added Sauce Labs Bolt T-Shirt and Sauce Labs Backpack

  @Cart @Negative
  Scenario: Remove added item on cart page
    When User add Sauce Labs Bolt T-Shirt and Sauce Labs Backpack to cart
    And User go to cart page
    And Remove Sauce Labs Bolt T-Shirt from cart in cart page
    Then Cart page should only show the Sauce Labs Backpack
