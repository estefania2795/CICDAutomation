package rahulshettyacademy.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cart;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
public class StepDefinitionImp extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public Cart cartCatalog;
	public CheckOutPage checkOutPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) throws IOException, InterruptedException {
		 productCatalog = landingPage.loginApplication(username,password);	
	}
	
	@Given("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) throws IOException, InterruptedException {
		 productCatalog.getProductsList();
		 productCatalog.selectProduct(productName);
	     productCatalog.addProductToCar(productName);
		 productCatalog.waitProductAdded();	
	}
	
	@Given("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws IOException, InterruptedException {
		cartCatalog =productCatalog.ClickonCart();
		Boolean match = cartCatalog.checkProduct(productName);
		Assert.assertTrue(match);
		checkOutPage =cartCatalog.goToCheckout();
		String expireMonth ="12";
		String expireDay ="12";
		String cvv ="345";
		String nameCard ="Estefania Rodriguez";
		String country= "me";
		checkOutPage.personalInfo(expireMonth,expireDay,cvv,nameCard);
		checkOutPage.shippingInfo(country);
		confirmationPage = checkOutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) throws IOException, InterruptedException {
		String confirmMessage = confirmationPage.getCheckMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));	
		driver.close();	
	}
	
	@Then("{string} Message is displayed")
	public void Message_is_displayed(String string) {
		Assert.assertEquals(string,landingPage.getErrorMessage());
		driver.close();	
	}
}
