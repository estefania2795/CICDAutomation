package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent  {
	//AbstractComponets is the parent
		//LandingPage the child

	WebDriver driver;
	//contructor with name same as constructor name
	//contructor will be initialize because this will get initialized at the beginning before anything happens

	public LandingPage(WebDriver driver)

	{
		//To send elements from the child to the parent we can use SUPER, in parent should create the constructor to catch the element
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//WebElement password = driver.findElement(By.id("userPassword")).sendKeys("Banamex19");
	//WebElement login = driver.findElement(By.id("login"));

	//another way to declare this design pattern is "Page Factory" to reuse syntax

	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;

	@FindBy(id="login")
	WebElement submit;

	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	//Action method

	public ProductCatalog loginApplication (String email, String password) throws InterruptedException
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
	
		ProductCatalog productCatalogue = new ProductCatalog (driver);
		return productCatalogue;
	}

	public void goTo ()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMessage(){
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}


}
