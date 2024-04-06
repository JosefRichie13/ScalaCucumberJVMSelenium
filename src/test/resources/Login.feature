@AutomatedTests
@LoginAndLogout

Feature: User login and logout scenarios

  Scenario: Valid user login
    Given I open the web page
    When I login as a "standard" user
    Then I should see "Swag Labs" in the "homepage"