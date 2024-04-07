@AutomatedTests
@HomePageSort

Feature: Home Page Scenarios

  Scenario: Cart bubble number increases/decreases as products are added
    Given I open the web page
    And I login as a "standard" user
    And I add "Sauce Labs Bike Light" to the cart
    And I add "Sauce Labs Bolt T-Shirt" to the cart
    And I add "Sauce Labs Fleece Jacket" to the cart
    And the number of items in the cart bubble is "3"
    When I remove "Sauce Labs Bike Light" from the cart
    Then the number of items in the cart bubble is "2"