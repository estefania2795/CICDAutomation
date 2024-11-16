package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//select[@class='input ddl'][1]")
	WebElement expireMonth;

	@FindBy(xpath="//select[@class='input ddl'][2]")
	WebElement expireDay;

	@FindBy(xpath="(//input[@type='text'])[2]")
	WebElement cvvEl;

	@FindBy(xpath="(//input[@type='text'])[3]")
	WebElement nameCardEl;

	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickCountry;

	@FindBy(css=".action__submit")
	WebElement submit;

	By countryResult = By.cssSelector(".ta-results");

	public void personalInfo(String month, String day, String cvv, String nameCard) {
		waitToBeClickable(expireMonth);
		expireMonth.click();
		selectDropdown(expireMonth,month);
		selectDropdown(expireDay,day);
		cvvEl.sendKeys(cvv);
		nameCardEl.sendKeys(nameCard);
	}

	public void shippingInfo(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry,country).build().perform();
		waitForElementToAppear(countryResult);
		clickCountry.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
		}
	}

