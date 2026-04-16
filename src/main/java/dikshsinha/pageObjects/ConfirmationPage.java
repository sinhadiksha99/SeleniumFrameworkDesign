package dikshsinha.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dikshasinha.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMsg;
	
	By confirmMsgBy = By.cssSelector(".hero-primary");
	
	public String getConfirmationMessage() {
		waitForElementToAppear(confirmMsgBy);
		String thankyouMsg = confirmationMsg.getText();
		return thankyouMsg;
	}

}
