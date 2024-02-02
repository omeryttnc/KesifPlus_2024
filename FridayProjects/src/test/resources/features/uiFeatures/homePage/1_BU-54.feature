@BU-55
	@UI
Feature: Test Execution Of_US_026 

	#Terms Of Service should be clickable and relevant page should be visible
	@TEST_BU-54
	Scenario: US_026 - TC01_User should be able to perform various operations on homepage
		Given User goes to homepage
		When User click to Terms Of Service
		Then User should be able to see relevant page
		
