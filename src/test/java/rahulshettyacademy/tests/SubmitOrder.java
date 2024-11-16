package rahulshettyacademy.tests;
import java.io.File; 
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.Cart;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.Orders;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrder extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups = {"Purchase"})
	public  void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
	
	//	public  void submitOrder(String email, String password,String productName) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//LandingPage landingPage = launchApplication();
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		productCatalog.getProductsList();
		productCatalog.selectProduct(input.get("product"));
		productCatalog.addProductToCar(input.get("product"));
		productCatalog.waitProductAdded();
		
		//although the method clickoncart doesnt exist in cartCatalog we can acces because cart inherited the methods of abstract
		Cart cartCatalog =productCatalog.ClickonCart();
		Boolean match = cartCatalog.checkProduct(input.get("product"));
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage =cartCatalog.goToCheckout();
		
		String expireMonth ="12";
		String expireDay ="12";
		String cvv ="345";
		String nameCard ="Estefania Rodriguez";
		String country= "me";
		checkOutPage.personalInfo(expireMonth,expireDay,cvv,nameCard);
		checkOutPage.shippingInfo(country);
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getCheckMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
	
	@Test (dependsOnMethods={"submitOrder"})
	
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalog productCatalog = landingPage.loginApplication("visaadventures@gmail.com","Banamex19");
		Orders orderPage =productCatalog.ClickonOrders();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	/*@DataProvider
	public Object[][] getData(){
		return new Object[][] {{"visaadventures@gmail.com","Banamex19","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	}*/
	
	/*@DataProvider
	public Object[][] getData(){
		
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("email","visaadventures@gmail.com");
		map.put("password","Banamex19");
		map.put("product","ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email","shetty@gmail.com");
		map1.put("password","Iamking@000");
		map1.put("product","ADIDAS ORIGINAL");

		return new Object[][] {{map},{map1}};
	}*/
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String, String>> data = getJsonDataToMap("C:/Users/estef/Documents/Selenium/SeleniumFrameworkDesign/src/test/java/rahulshettyacademy/data/PurchaseOrder.json");
		//List<HashMap<String, String>> data = getJsonDataToMap("C://Users//estef//Documents//Selenium//SeleniumFrameworkDesign//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}


	
}
