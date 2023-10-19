Feature: Viewing product detail

  Background:
    Given User is on Products page with standard_user as user

  @ProductDetail @Positive
  Scenario: User viewing detail of an item
    When User click Sauce Labs Bike Light
    Then It will show the Sauce Labs Bike Light product detail

  @ProductDetail @Negative
  Scenario: User viewing detail of an added-to-cart item
    When User add Sauce Labs Bike Light from product page
    And User click Sauce Labs Bike Light
    Then It will show the Sauce Labs Bike Light has been added to cart in product detail
