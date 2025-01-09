#Author: Juhi

Feature: New User Registration and sign out
 
	
	Scenario: Register in the application and sign out
  	Given user opens the browser
    When user navigates to url
    And click Create New Account
    And Register by providing details
    Then user registered successfully
    When user click menu and sign out
    Then user signed out successfully
   