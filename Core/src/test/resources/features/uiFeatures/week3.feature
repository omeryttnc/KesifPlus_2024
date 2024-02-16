Business Need: Your products/services
  Rule: keyword is to represent one business rule that should be implemented
  is used to group together several scenarios that belong to this business rule
  should contain one or more scenarios that illustrate the particular rule.
    @UI
    @DB
    @buyerLogin
    Scenario: As a user, after entering valid information, I should be able to perform various actions in the Your products/services section
#      Given User login as Seller
      And user send "95109" as zipcode
      And User goes to Your products-services
      And User click on random Hubs

      When Product Name, Price, Stock, Unit information added products should be displayed.
      Then One of the APPROVED, IN_REVIEW, REJECTED options for each product should be visible on the file.

      When User add new product
      Then In-Review should be visible on the added product.

      When User approve last added product from database
      Then Approved should be visible on the added product.
      And It should be possible to click on the product name on the file related to the previously added product.

      When The product name is clicked.
      Then the update-delete button will be displayed.

      When the update option is clicked
      Then user should be able to see "has been succesfully updated" as part of toast message
      And user should be able to see product name as part of toast message

      When the Delete button is clicked.
      Then Yes-No options should appear.

      When Yes option is selected
      Then user should be able to see "has been succesfully deleted" as part of toast message
      And user should be able to see product name as part of toast message
      And the product should be removed from the page.




  Rule: another implementation