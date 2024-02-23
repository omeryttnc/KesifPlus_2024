@UI
Feature: US_070

  Scenario: As a user, after entering valid information,
  I should be able to perform various actions in the Welcome Page

    Given User goes to homepage
    When Click the "Login" button in the main Page
    And Login as "BUYER"
    And Enter zipCode as "95170"
    And Click the "Orders" button in the main panel
    And Verify that the order is available in Orders
    And Verify that the order status is "In Progress"
    And Click the buyer notifications icon
    And Verify that the message "Your Order is Placed" is available in Notifications
    Then User logout


    When Click the "Login" button in the main Page
    And Login as "SELLER"
    And Enter zipCode as "95170"
    And Click the "Sold items" button in the main panel
    And Verify that the item is available in Sold Items
    And Verify that the item status is "In Progress"
    And Update the item status as "On Delivery"
    And Click the seller notifications icon
    And Verify that the message "You Have a New Order" is available in Notifications
    Then User logout


    When Click the "Login" button in the main Page
    And Login as "BUYER"
    And Enter zipCode as "95170"
    And Click the "Orders" button in the main panel
#    And Verify that the order status is "On Delivery"
#    And Click the buyer notifications icon
#    And Verify that mark all as read button is functional in Notifications
#    Then Verify that X button is functional in Notifications
#    And Verify that mark all as read button gives correct result
#    Then User logout
#
#
#    When Click the "Login" button in the main Page
#    And Login as "SELLER"
#    And Enter zipCode as "95170"
#    And Click the "Sold items" button in the main panel
#    And Update the item status as "In Progress"
#    And Click the seller notifications icon
#    And Verify that mark all as read button is functional in Notifications
#    Then Verify that X button is functional in Notifications
#    And Verify that mark all as read button gives correct result

