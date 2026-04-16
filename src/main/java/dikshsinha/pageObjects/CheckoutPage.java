package dikshsinha.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dikshasinha.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryEl;
	
	@FindBy(css="section.ta-results button")
	List<WebElement> countrySuggestions;
	
	@FindBy(xpath ="//a[contains(@class,'submit')]")
	WebElement placeOrder;
	
	By countrySuggestionsBy = By.cssSelector("section.ta-results button");
	
	public ConfirmationPage selectCountry(String shorthand, String country) {
		countryEl.sendKeys(shorthand);
		waitForElementToAppear(countrySuggestionsBy);
		WebElement ourCountry = countrySuggestions.stream().filter(s->s.findElement(By.tagName("span")).getText().equals("Australia")).findFirst().orElse(null);
		ourCountry.click();
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	

}
