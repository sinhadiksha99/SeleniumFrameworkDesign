package dikshasinha.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dikshsinha.pageObjects.CartPage;
import dikshsinha.pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderBtn;
	
	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisppear(By findBy) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToBeClickable(By findBy) {
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

	public CartPage goToCartPage() {
		cartBtn.click();
		CartPage cart = new CartPage(driver);
		return cart;

	}
	
	public OrderPage goToOrderPage() {
		orderBtn.click();
		OrderPage order = new OrderPage(driver);
		return order;

	}

}
