Feature: Cart functionality

  @Test1
  Scenario Outline: Verify E2E of Add products to Cart and checkout
    Given User Navigate to Swag Labs application
    When login with "Standard User" role on Swag Labs application with user "<testUser>"
    And User clicks on "Login" Button
    Then User should see "Products" on page

    When user add below products on Product Page
      | ProductName             |
      | Sauce Labs Bike Light   |
      | Sauce Labs Onesie       |
      | Sauce Labs Bolt T-Shirt |
     #Verify Cart should display the number of products get added
    Then User should see "3" on page
    When User clicks on "shopping-cart-badge" Button
     #Verify user navigate to Cart Page
    Then User should see "Your Cart" on page
    And User should see the below product added on the cart page
      | ProductName             |
      | Sauce Labs Bike Light   |
      | Sauce Labs Onesie       |
      | Sauce Labs Bolt T-Shirt |
    When User clicks on "Checkout" Button on page

    #Verify user navigate to checkout Page
    Then User should see "Checkout: Your Information" on page
    When User enter following details on Checkout page
      | fieldName       | Value      |
      | First Name      | Bhagyashri |
      | Last Name       | Pandey     |
      | Zip/Postal Code | 411033     |
    And User clicks on "Continue" Button
    Then User should see "Checkout: Overview" on page
    Then User verify price Total of the product on checkout page
    When User clicks on "Finish" Button on page
    Then User should see "Your order has been dispatched, and will arrive just as fast as the pony can get there!" on page

    Examples:
      | testUser                |
      | standard_user           |
      | performance_glitch_user |

  @Test2
  Scenario Outline: Verify whether user can see the remove button when add items to cart
     #Step3- Add a product and Remove a product from the cart
    Given User Navigate to Swag Labs application
    When login with "Standard User" role on Swag Labs application with user "<testUser>"
    And User clicks on "Login" Button
    Then User should see "Products" on page

    When user add below products on Product Page
      | ProductName             |
      | Sauce Labs Bike Light   |
    Then User should see "1" on page
    When User clicks on "shopping-cart-badge" Button
     #Verify user navigate to Cart Page
    Then User should see "Your Cart" on page
    When User should see the below product added on the cart page
      | ProductName             |
      | Sauce Labs Bike Light   |
    And user remove below products on cart Page
      | ProductName             |
      | Sauce Labs Bike Light   |
    Then User should not see "Sauce Labs Bike Light" on page

    Examples:
      | testUser                |
      | standard_user           |
      | performance_glitch_user |