@UI
Feature: US_065

  Scenario: User should be able to perform various actions in the orders module

    Given User goes to homepage
    When Click the "Login" button in the main Page
    And Login with credential and zipCode
      | testuser@deneme.com | Testuser1/. | 95170 |

    And Click the "Orders" button in the main panel
    And Verify that user is in the Orders Page
    And Select any order
    And Click View order details button for selected order
    And Verify that the order details page and order summary are visible
    And Click Seller Page button in Order Details page
    And Verify that seller name is correct in seller page
    And User navigate to previous page
    And Click seller's address map
    Then verify seller map matches with the larger map
