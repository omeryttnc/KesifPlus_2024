@BU-88
Feature: US_015 TC_001 and TC_002 Test Execution

	#Users can click the What’s Near You button, after clicking users can see the relevant page.
	@TEST_BU-77
	@UI
	Scenario: TC_001_US_015 : What's Near You buttton should be clickable and relevant page should be visible.
		Given User goes to homepage
		    When User click to WhatsNearYou button
		    Then User should be able to reach Seller page
		
	#Users can click the Post Your Goods button, after clicking users can reach the relevant page.
	@TEST_BU-78
		@UI
	Scenario: TC_002_US_015: Post Your Goods button should be clickable and the relevant page should be visible.
		When User click to PostYourGoods button
		Then User should be able to reach relevant page
		
