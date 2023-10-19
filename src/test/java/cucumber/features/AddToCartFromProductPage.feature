Feature: Add an item to cart

  Background:
    Given User is on products page with standard_user as user

  @Products @Positive
  Scenario: User add an item to cart
    When User add Sauce Labs Bolt T-Shirt to cart
    Then Item in cart has the value of one

  @Products @Negative
  Scenario: User remove added item from cart
    When User add Sauce Labs Bolt T-Shirt to cart
    And Remove Sauce Labs Bolt T-Shirt from cart in product page
    Then Item in cart has the value of zero
