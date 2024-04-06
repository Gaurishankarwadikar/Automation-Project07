package Learning.Ecomm.Tests;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import Learning.Ecomm.TestComponents.BaseTest;
import Object.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Learning.Ecomm.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	
	{
		// TODO Auto-generated method stub
		
		//String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("apupatil50@gmail.com", "Apu@11");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		
		
	}
	
	
}

