Feature: Order a product on OLX

Scenario Outline: User logs in successfully
Given the user is on OLX login page
When the user enters a valid email '<email>'
And the user enters a valid password '<password>'
And the user clicks the '<loginButton>'
Then the user is redirected to the OLX account page
And the user closes the browser

Examples:
| email | password | loginButton |
| irinagorea182002@gmail.com | Cucumber333 | Login |


Scenario Outline: User searches and opens a product
Given the user is logged in with '<email>' and '<password>'
When the user searches for a product '<productName>'
And the user opens the selected product
Then the user is able to see the product details page
And the user closes the browser

Examples:
| email | password | productName |
| irinagorea182002@gmail.com | Cucumber333 | Yeezy |


Scenario Outline: User starts the order process
Given the user is logged in with '<email>' and '<password>'
When the user opens the product '<productName>'
And the user clicks on the Buy with delivery button
Then the user is redirected to the delivery checkout page
And the user closes the browser

Examples:
| email | password | productName |
| irinagorea182002@gmail.com | Cucumber333 | Yeezy |


Scenario Outline: User fills delivery information
Given the user is on the checkout page
When the user enters first name '<firstName>'
And the user enters last name '<lastName>'
And the user enters email '<deliveryEmail>'
And the user enters phone number '<phoneNumber>'
And the user enters address '<address>'
And the user enters city '<city>'
Then the user is able to continue the order process
And the user closes the browser

Examples:
| firstName | lastName | deliveryEmail | phoneNumber | address | city |
| Irina | Konovalenko | irinagorea182002@gmail.com | 0721234567 | Alba-Iulia19 | Bucuresti |


Scenario Outline: User selects payment method
Given the user filled delivery information
When the user selects payment method '<paymentMethod>'
And the user clicks on continue button
Then the user is redirected to the order summary page
And the user closes the browser

Examples:
| paymentMethod |
| Plata la livrare |


Scenario Outline: User verifies order summary
Given the user is on the order summary page
When the user verifies the product name '<productName>'
And the user verifies the total price
And the user accepts terms and conditions
Then the user is able to place the order
And the user closes the browser

Examples:
| productName |
| Yeezy calitate premium |