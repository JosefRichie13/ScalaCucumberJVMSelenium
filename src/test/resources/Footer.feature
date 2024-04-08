@AutomatedTests
@Footer

Feature: Footer Scenarios

  Scenario: Footer is not visible when logged out
    Given I open the web page
    Then I confirm that the footer is "not visible"

  Scenario: Footer is visible when logged in
    Given I open the web page
    When I login as a "standard" user
    Then I confirm that the footer is "visible"

  Scenario: Twitter Footer link redirects correctly
    Given I open the web page
    And I login as a "standard" user
    When I click on the "Twitter" icon in the footer
    Then I should see the redirect link as "https://twitter.com/saucelabs"

  Scenario: Facebook Footer link redirects correctly
    Given I open the web page
    And I login as a "standard" user
    When I click on the "Facebook" icon in the footer
    Then I should see the redirect link as "https://www.facebook.com/saucelabs"

  Scenario: LinkedIn Footer link redirects correctly
    Given I open the web page
    And I login as a "standard" user
    When I click on the "LinkedIn" icon in the footer
    Then I should see the redirect link as "https://www.linkedin.com/company/sauce-labs/"