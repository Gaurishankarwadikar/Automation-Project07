package Learning.Ecomm.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import Learning.Ecomm.TestComponents.BaseTest;
import Object.pageobjects.CartPage;
import Object.pageobjects.CheckoutPage;
import Object.pageobjects.ConfirmationPage;
import Object.pageobjects.LandingPage;
import Object.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Eccomerce Page")
	public void I_landed_on_Eccomerce_Page() throws IOException
	{
		landingPage = launchApplication();
		
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_submit_order(String productName) 
	{
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmPage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmPage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_displayed(String string1)
	{
		Assert.assertEquals(string1,landingPage.getErrorMessage());
		driver.close();
	}
}
