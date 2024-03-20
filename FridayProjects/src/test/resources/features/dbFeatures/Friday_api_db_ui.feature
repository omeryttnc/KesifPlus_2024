Feature: Add, update and verify a product with api, database and ui

  @DB
  @UI
  Scenario: Add a product on sale
    Given Add a new item to your products_services through api
    * update status as APPROVED for the item through database
    * User goes to homepage
    * Click the "Login" button in the main Page
    And Login as "BUYER"
    And Enter zipCode as "95170"
    And Click the "Your products/services" button in the main panel
    And Click the "Vegetables & fruits" hub button
    Then Verify that the status is updated through ui

#  @DB
#  Scenario: Update the status
#    Given update status as APPROVED for the item through database
#
#  @UI
#  Scenario: Check the product status
#    Given User goes to homepage
#    When Click the "Login" button in the main Page
#    And Login as "BUYER"
#    And Enter zipCode as "95170"
#    And Click the "Your products/services" button in the main panel
#    And Click the "Vegetables & fruits" hub button
#    Then Verify that the status is updated through ui
