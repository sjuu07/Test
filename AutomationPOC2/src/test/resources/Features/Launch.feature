Feature: Advantage Demo online shopping
	
  @smoke
  Scenario: Search product and place an order as loggedin user
  	Given user opens the browser
    When user navigates to url
    And click menu to provide login credentials
    Then verify the page title
    When user is on application welcome page
    When user search an item
    And add item to cart
    And complete the payment
    Then order is placed successfully
    
  @smoke 
 	Scenario: Validate ContactUs functionality
 #	Given user opens the browser
 #   When user navigates to url
 # And click menu to provide login credentials
 #   Then verify the page title
 		Given user is on application welcome page
 		When user is on Contact Us section
 		And user provide all the details
 		And click on Send
 	Then user should be able to see Thankyou message 
 		
 	
 	Scenario: Adding multiple products to cart and verifying clear cart functionality
# 	Given user opens the browser
#   When user navigates to url
#    And click menu to provide login credentials
#   Then verify the page title
 		Given user is on application welcome page
 		When user scroll to Popular Items section
 		And user capture and click the first available product
 		And capture price and Add to Cart
 		When user navigate back to Popular Items section
 		And user capture and click the second available product
 		And capture price and Add to Cart
 		And Hover on the cart icon to view the total price
 		And user click X icon for the products added
 		Then cart should be cleared
 		When user click menu and sign out
    Then user signed out successfully
    
    @smoke
    Scenario: Shop from the category list
    Given user is on application welcome page
    When user click on Speakers Shop Now
    And select the compataibility to bluetooth enabled
    And select HP product
    And add item to cart
    And complete the payment
    Then order is placed successfully 