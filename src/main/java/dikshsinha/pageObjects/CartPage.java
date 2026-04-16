package dikshsinha.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dikshasinha.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> prodNames;
	
	By prodNamesBy = By.cssSelector(".cartSection h3");
	By checkoutBy = By.cssSelector(".totalRow button");
	
	public boolean verifyProductDisplay(String prodName) {
		waitForElementToAppear(prodNamesBy);
		boolean match = prodNames.stream().anyMatch(s->s.getText().equals(prodName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		new Actions(driver).scrollToElement(checkoutBtn).perform();
		checkoutBtn.click();
		CheckoutPage checkout = new CheckoutPage(driver);
		return checkout;
	}

}
