Feature: Order a product 

Scenario Outline: User logs in successfully 
Given the user is on Login page
When the user enters a valid username '<username>'
And the user enters a valid password '<password>'
And the user clicks the '<loginButton>'
Then the user is redirected to the '<inventoryPage>'
And the user closes the browser

Examples:
| username | password | loginButton | inventoryPage |
| standard_user | secret_sauce | //*[@id=\"login-button\"] | https://www.saucedemo.com/inventory.html |

Scenario Outline: User adds a product to cart 
Given the user is logged in with '<username>' and '<password>' by clicking the '<loginButton>'
When the user adds the product Sauce Labs Backpack to the cart
Then the user is able to see one product on the cart
And the user closes the browser

Examples:
| username | password | loginButton | 
| standard_user | secret_sauce | //*[@id=\"login-button\"] |

Scenario Outline: User checks the product in the cart 
Given the user is logged in with '<username>' and '<password>' by clicking the '<loginButton>'
When the user navigates to the cart page
Then the user is able to see the product name and cost
And the user closes the browser

Examples:
| username | password | loginButton | 
| standard_user | secret_sauce | //*[@id=\"login-button\"] |

Scenario Outline: User fills in checkout information 
Given the user is logged in with '<username>' and '<password>' by clicking the '<loginButton>'
When the user navigates to the checkout pages 
And the user fills in the delivery data with '<firstName>' and '<lastName>' and '<postalCode>'
Then the user is able to see the Continue button
And the user closes the browser

Examples:
| username | password | loginButton | firstName | lastName | postalCode |
| standard_user | secret_sauce | //*[@id=\"login-button\"] | Viorica | Moraru | 2044 |

Scenario Outline: User checks final checkout informations  
Given the user is logged in with '<username>' and '<password>' by clicking the '<loginButton>'
When the user navigates to the checkout pages 
And the user fills in the delivery data with '<firstName>' and '<lastName>' and '<postalCode>'
And the user click on the continue button 
Then the user is able to see final information 
And the user closes the browser

Examples:
| username | password | loginButton | firstName | lastName | postalCode |
| standard_user | secret_sauce | //*[@id=\"login-button\"] | Viorica | Moraru | 2044 |


Scenario Outline: User had completed order  
Given the user is logged in with '<username>' and '<password>' by clicking the '<loginButton>'
When the user navigates to the checkout pages 
And the user fills in the delivery data with '<firstName>' and '<lastName>' and '<postalCode>'
And the user click on the continue button 
And the user is able to see final information 
And the user click on finish button 
Then the user is able to see thank you page
And the user closes the browser

Examples:
| username | password | loginButton | firstName | lastName | postalCode |
| standard_user | secret_sauce | //*[@id=\"login-button\"] | Viorica | Moraru | 2044 |



