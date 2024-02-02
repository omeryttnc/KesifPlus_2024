@BU-51
Feature: Botton Bar Contact US btn feature

  #Contact Us should be clickable and relevant page should be visible
  @UI
  @TEST_BU-50
  Scenario: User should be able to perform various operations on homepage
    Given User goes to homepage
    When User click to Contact US btn at the botton
    Then User should be able to see relevant page
