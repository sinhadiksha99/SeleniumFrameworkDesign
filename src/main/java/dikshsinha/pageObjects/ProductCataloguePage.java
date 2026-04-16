package dikshsinha.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dikshasinha.AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	
	WebDriver driver;
	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products; 
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	By errorMsgBy = By.cssSelector("[class*='flyInOut']");
	By productsBy = By.cssSelector(".mb-3");
	By toastMessageBy = By.id("toast-container");
	By addToCart = By.xpath(".//child::button[2]");
	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String name) {
		WebElement prod = getProductList().stream()
			    .filter(s -> s.findElement(By.cssSelector("b")).getText().equals(name))
			    .findFirst()
			    .orElse(null);
		return prod;
	}
	
	public void addProductToCart(String name) {
		WebElement prod = getProductByName(name);
		prod.findElement(addToCart).click();		
		waitForElementToAppear(toastMessageBy);
		waitForElementToDisppear(toastMessageBy);
	}
	
	public String getErrorMsg() {
		waitForElementToAppear(errorMsgBy);
		return errorMsg.getText();
	}

}
