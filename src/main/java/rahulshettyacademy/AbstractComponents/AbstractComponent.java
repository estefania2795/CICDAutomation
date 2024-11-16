package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.Cart;
import rahulshettyacademy.pageobjects.Orders;

public class AbstractComponent {

	//this is how we connect this driver with super method-->>
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}//<----

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;

	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement ele3) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele3));
	}

	public void waitForElementToDissappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		/*Our execution is stopping here because we are waiting for that loading to dissapear as there are another loaders happening in the backend (many users are using this page)
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));*/
	}
	public void waitToBeClickable(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(elem));
	}

	public void javaScriptExecutor(WebElement ele2) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();",ele2);
	}
	public void selectDropdown(WebElement element, String month) {
		WebElement staticDropdown = element;
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText(month);
	}

	public Cart ClickonCart() {
		javaScriptExecutor(cart);
		cart.click();
		Cart cartCatalog = new Cart(driver);
		return cartCatalog;
	}

	public Orders ClickonOrders() {
		javaScriptExecutor(orderButton);
		orderButton.click();
		Orders orders = new Orders(driver);
		return orders;
	}

}
