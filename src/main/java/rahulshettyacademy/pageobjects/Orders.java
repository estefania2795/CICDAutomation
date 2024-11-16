package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class Orders  extends AbstractComponent{
	WebDriver driver;

	public Orders(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> productsOrder;

	Boolean match;

	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;


	public Boolean verifyOrderDisplay(String productName) {
		match = productsOrder.stream().anyMatch(g-> g.getText().equalsIgnoreCase(productName));
		return match;
	}
}
