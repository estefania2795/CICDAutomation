package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog  extends AbstractComponent {

	WebDriver driver;
	//contructor with name same as constructor name
	//contructor will be initialize because this will get initialized at the beginning before anything happens

	public ProductCatalog(WebDriver driver)
	{
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//WebElement password = driver.findElement(By.id("userPassword")).sendKeys("Banamex19");
	//WebElement login = driver.findElement(By.id("login"));

	//another way to declare this design pattern is "Page Factory" to reuse syntax

	//List <WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> listProducts;

	By productsBy = By.cssSelector(".mb-3");
	WebElement prod ;

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	By productAdded = By.cssSelector("#toast-container");

	@FindBy(css=".ng-animating")
	WebElement productAddedDisa;

	//Action method

	public List<WebElement> getProductsList ()
	{

		waitForElementToAppear(productsBy);
		List <WebElement> products = listProducts;
		return products;

	}

	public WebElement selectProduct(String productName) {
		prod =getProductsList().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		return prod;
	}

	public void addProductToCar(String productName) {
		WebElement prod = selectProduct(productName);
		prod.findElement(addToCart).click();

	}

	public void goTo ()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

	public void waitProductAdded() throws InterruptedException
	{	
		waitForElementToAppear(productAdded); 
		waitForElementToDissappear(productAddedDisa);
		
	}



}
