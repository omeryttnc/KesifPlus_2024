@UI
Feature: US_074, US_078 and US_080

  Scenario: US_074 Create an event as a registered user

    Given User goes to homepage
    When Click the "Login" button in the main Page
    And Login as "SELLER"
    And Enter zipCode as "95170"
    And Click the "My Events" button in the main panel
    And Click the button "Create New Event"
    And Fill the event form with valid data
    And Verify required fields
    And Click the button "Submit"
    And Verify that the success message and event is available
    Then User logout


    When Click the "Login" button in the main Page
    And Login as "BUYER"
    And Enter zipCode as "95170"
    And Click the "Events" button in the main panel
    And Click the Register button for the created event
    And Verify that approving the terms box is required
    And Verify that the number of attendees is required
    And Click the approve button for event registration
    And Verify that the approve message and registration is available
    Then User logout


    When Click the "Login" button in the main Page
    And Login as "SELLER"
    And Enter zipCode as "95170"
    And Click the "My Events" button in the main panel
    And Verify that delete button is functional


