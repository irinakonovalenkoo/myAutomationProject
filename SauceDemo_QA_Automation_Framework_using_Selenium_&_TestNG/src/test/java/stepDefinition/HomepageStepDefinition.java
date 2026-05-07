package stepDefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomepageStepDefinition {
	
FirefoxDriver driver = new FirefoxDriver();

	@Given("the user is on Login page")
	public void openBrowser() {
		//System.out.println("open the browser");
		driver.get("https://saucedemo.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
		//System.out.println("the browser was opened");
	}
	
	@When("the user enters a valid username {string}")
	public void enterUsername(String username) {
		
		// asemanarea cu implicitly wait este ca asteapta daca are nevoie
		// asemanarea cu threadsleep este ca se plaseaza de fiecare data inainte sa se incarce un element, atunci cand este nevoie 
		
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(22)) ;
		//driver.findElement(By.xpath(data.Locators.usernameTextField)).sendKeys(data.TextData.username); //- externalizarea textului intr-un fisier separat
		
		driver.findElement(By.xpath(data.Locators.usernameTextField)).sendKeys(username); // - externalizarea textului direct in fisierul .feature
	}
	
	@And("the user enters a valid password {string}")
	public void enterPassword(String password) {
		//driver.findElement(By.xpath(data.Locators.passwordTextField)).sendKeys(data.TextData.password);
		driver.findElement(By.xpath(data.Locators.passwordTextField)).sendKeys(password);
	}
	
	@And("the user clicks the {string}")
	public void clickLogin(String loginButton) {
		//driver.findElement(By.xpath(data.Locators.loginButton)).click();
		driver.findElement(By.xpath(loginButton)).click();
	}
	
	@Then("the user is redirected to the {string}")
	public void redirectStore(String inventoryPage) {
		String currentURL = driver.getCurrentUrl();
		//String expectedURL = data.Locators.externalURL;
		String expectedURL = inventoryPage;
		Assert.assertEquals(currentURL, expectedURL, "Page URL is incorrect!"); 
		// asertiunea inseamna verificarea corespunderii adresei paginii, 
		// in caz de necorespundere atunci este afisat in consola acel mesaj din ghilimele 
	}
	
	@And ("the user sees the page title")
	public void titleIsDisplayed() {
		System.out.println("userul vede titlul");
		// 1. Definim titlul așteptat
		String expectedTitle = "Swag Labs";
		
		// 2. Luăm titlul actual din browser (fără așteptare)
		String actualTitle = driver.getTitle();
		
		// 3. Aserțiunea propriu-zisă
		//Assert.assertEquals(actualTitle, expectedTitle); // acesta este un hard assertion - in cazul in care e failed - se opreste testul
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualTitle, expectedTitle); // acesta e un soft assertion, si in cazul in care e failed, testul oricum continua mai departe
		
		//driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
		System.out.println("userul a vazut");
	}
	
	@And ("the user sees the product Sauce Labs Backpack")
	public void firstUnitIsDisplayed() {
		System.out.println("userul verifica produsul");
		Assert.assertNotNull(driver.findElement(By.xpath(data.Locators.userCheckProductXpathDiv)));
		System.out.println("userul a verificat produsul");
		driver.close();
	}
	
	@Given("the user is logged in with {string} and {string} by clicking the {string}")
	public void userIsLoggedIn(String username, String password, String loginButton) {
		openBrowser();
		enterUsername(username);
		enterPassword(password);
		clickLogin(loginButton);
	}
	
	@When("the user adds the product Sauce Labs Backpack to the cart")
	public void userAddsToCart() {
		driver.findElement(By.xpath(data.Locators.userAddTheProductToCartXpathID)).click();
	}
	@Then("the user is able to see one product on the cart")
	public void productIsAddedToCart() {
		Assert.assertNotNull(driver.findElement(By.xpath(data.Locators.productIsAddedToCartSpan)));
	}
	
	
	@When ("the user navigates to the cart page")
	public void userNavigatesToCart() {
		userAddsToCart();
		driver.findElement(By.xpath(data.Locators.userNavigatesToCartA)).click();
	}
	@Then ("the user is able to see the product name and cost")
	public void productNameAndCostIsDisplayed() {
		String expectedName = "Sauce Labs Backpack";
		String expectedCost = "$29.99";
		
		String productName = driver.findElement(By.xpath(data.Locators.productNameID)).getText();
		String productCost = driver.findElement(By.xpath(data.Locators.productCostID)).getText();
		
		
		//String actualName = productName.getText();
		//String actualCost = productCost.getText();
		
		Assert.assertEquals(productName, expectedName);
		Assert.assertEquals(productCost, expectedCost);
	}
	
	@When ("the user navigates to the checkout pages")
	public void userNavigatesToCheckout() {
		userNavigatesToCart();
		driver.findElement(By.xpath(data.Locators.userNavigatesToCheckout)).click();
	}
	
	@And ("the user fills in the delivery data with {string} and {string} and {string}")
	public void userFillsInDeliveryData(String firstName, String lastName, String postalCode) {
		driver.findElement(By.xpath(data.Locators.firstNameTextField)).sendKeys(firstName);
		driver.findElement(By.xpath(data.Locators.lastNameTextField)).sendKeys(lastName);
		driver.findElement(By.xpath(data.Locators.postalCodeTextField)).sendKeys(postalCode);
	}
	@Then("the user is able to see the Continue button")
	public void continueButtonIsDisplayed() {
		Assert.assertNotNull(driver.findElement(By.xpath(data.Locators.continueButton)));
		//driver.close();
	}
	
	@And ("the user click on the continue button")
	public void userClickToContinueButton() {
		driver.findElement(By.xpath(data.Locators.continueButton)).click();
		
	}
	
	@Then ("the user is able to see final information")
	public void userIsAbleToFinalInformation() {
		String expectedPaymentInformation = "SauceCard #31337";
		String expectedShippingInformation = "Free Pony Express Delivery!";
		String expectedPriceTotal = "Total: $32.39";
		
		String paymentInformation = driver.findElement(By.xpath(data.Locators.paymentInformation)).getText();
		String shippingInformation = driver.findElement(By.xpath(data.Locators.shippingInformation)).getText();
		String priceTotal = driver.findElement(By.xpath(data.Locators.priceTotal)).getText();
		
		//String actualName = productName.getText();
		//String actualCost = productCost.getText();
		
		Assert.assertEquals(paymentInformation, expectedPaymentInformation);
		Assert.assertEquals(shippingInformation, expectedShippingInformation);
		Assert.assertEquals(priceTotal, expectedPriceTotal);
	}
	
	@And ("the user click on finish button")
	public void userClickToFinishButton() {
		driver.findElement(By.xpath(data.Locators.finishButton)).click();
		
	}
	@Then ("the user is able to see thank you page")
	public void userIsAbleToFinishTheOrder() {
		String expectedThankYouText = "Thank you for your order!";
		
		String thankYouText = driver.findElement(By.xpath(data.Locators.thankYouText)).getText();
		
		Assert.assertEquals(thankYouText, expectedThankYouText);
		driver.quit();
	}
	
	@And ("the user closes the browser")
	public void userClosesTheBrowser() {
		driver.quit();
	}
	
}











