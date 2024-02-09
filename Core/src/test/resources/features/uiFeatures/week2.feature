#US_50 - As a user, after entering valid information, I should be able to perform various actions in the Account section.
# "Account must be clickable
#When Account is clicked, Qr code, Full name, Email, Phone, Choose file options should be seen.
#Copy button should be functional
#Download button should be functional
#After clicking Edit button Choose file button should be functional
#The file should be selected when the choose file button is clicked.
#The account photo should change after the selected file
#Edit button should be clickable
#After clicking the Edit button, the phone menu should be updated.
#Save button should be functional
#After clicking the Save button, ''Your account information  has been updated"" alert' should be displayed.
#Toogle the menu button must be functional
#Clicking the menu button on the Tooglle should change the menu dimensions."
Feature: week2

  @UI
  @buyerLogin
  Scenario: US 50
#    Given buyer login annotation kullanma way 3
    And user send "95109" as zipcode
    When user clicks on Account button
    Then user should be able to on Account page

    When user click on copy button
    Then user should be able to see "Copied!" toast message
    And toast message colour should be green colour

    Given user should delete QRCode.png file if exist
    When user click on download button
    Then user should have QRCode.png file on local

    When user clicks on edit button
    Then Choose file should be visible

    When  user change the first image
    And user should take a copy of first image
    And user change the second image
    And user should take a copy of second image
    Then assert that first and second image should be difference

    Given user should clear phone number
    When user send new phone number
    And click on save button
    Then user should be able to see "Your account information has been updated." toast message
    And toast message colour should be green colour

    When user clicks on toggle button
    Then menu dimension should change



