package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)


	public void LoginError() throws IOException, InterruptedException{
	landingPage.loginApplication("anshika@gmail.com","Iamki000");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
  //jajaja
	//new comments for demo purposes
	int b=0;
	@Test

	public void ProducErrorValidation() throws IOException, InterruptedException{

	String productName = "ZARA COAT 3";
	ProductCatalog productCatalog = landingPage.loginApplication("mario@gmail.com","Iamki000");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
}
