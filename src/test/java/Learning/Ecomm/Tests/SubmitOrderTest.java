package Learning.Ecomm.Tests;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Learning.Ecomm.TestComponents.BaseTest;
import Object.pageobjects.CartPage;
import Object.pageobjects.CheckoutPage;
import Object.pageobjects.ConfirmationPage;
import Object.pageobjects.OrderPage;
import Object.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(String email, String passw,String productName) throws IOException, InterruptedException
	
	{
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(email, passw);
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmPage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmPage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("apupatil50@gmail.com", "Apurva@11");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}
	
	

	
	    
	
	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] { {"apupatil50@gmail.com","Apurva@11","ZARA COAT 3"}, {"Xtreme@gmail.com","Xtreme1104","ADIDAS ORIGINAL"} };
	}
}
