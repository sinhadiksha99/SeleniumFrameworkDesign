package dikshsinha.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dikshasinha.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> prodNames;
	
	By prodNameBy = By.xpath("//tbody/tr/td[2]");
	
	public boolean verifyOrderDisplay(String prodName) {
		waitForElementToAppear(prodNameBy);
		boolean match = prodNames.stream().anyMatch(s->s.getText().equals(prodName));
		return match;
	}

}
