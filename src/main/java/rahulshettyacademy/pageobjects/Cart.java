package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class Cart  extends AbstractComponent{
	WebDriver driver;

	public Cart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(css=".cartSection h3")
	List <WebElement> productsCar;

	Boolean match;

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;


	public Boolean checkProduct(String productName) {
		match = productsCar.stream().anyMatch(g-> g.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckOutPage goToCheckout() {
		checkout.click();
		return new CheckOutPage (driver);
	}

}
