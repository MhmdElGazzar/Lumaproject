Feature: End To End Valid Shopping and Checkout Scenario

  Scenario: Complete shopping flow with adding, removing, reviewing, and logging out

    Given I am on the Magento homepage
    When I click on the login button
    And I enter a valid email and password
    And I click on the sign-in button
    Then I should be logged in successfully

    When I search for "Watch"
    Then I should see relevant search results

    When I hover on a product and add it to the watchlist
    Then the product should be added to my watchlist successfully

    When I select an item from the category and add it to the cart
    Then I should see a success message for adding to cart

    When I open the cart
    Then the cart icon quantity should reflect the added products correctly

    When I go to the cart page
    Then the total price should match the sum of the product prices

    When I remove an item from the cart
    Then the subtotal should update correctly after removal

    When I proceed to checkout
    Then I should be able to complete the checkout process successfully

    When I add a review and a rating to a product
    Then I should see a success message for the review submission

    When I log out from the account
    Then I should see the "Sign In" button again
