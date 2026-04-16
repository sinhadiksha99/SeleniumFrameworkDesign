package dikshasinha.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginAndAddToCartTest {

	@Test
	public void loginAddToCartNoPage() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		WebElement email = driver.findElement(By.id("userEmail"));
		email.sendKeys("dikshasinha@gmail.com");
		WebElement password = driver.findElement(By.id("userPassword"));
		password.sendKeys("Diksha@123");
		WebElement loginBtn = driver.findElement(By.id("login"));
		loginBtn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
			    .filter(s -> s.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
			    .findFirst()
			    .orElse(null);
		prod.findElement(By.xpath(".//child::button[2]")).click();		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		WebElement prod1 = products.stream()
			    .filter(s -> s.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
			    .findFirst()
			    .orElse(null);
		prod1.findElement(By.xpath(".//child::button[2]")).click();		
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")));
		WebElement goToCart = driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]"));
		goToCart.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<String> prodNames = driver.findElements(By.cssSelector(".cartSection h3")).stream()
			    .map(s -> s.getText())
			    .collect(Collectors.toList());
		Assert.assertEquals(prodNames.get(0), "ZARA COAT 3");
		Assert.assertEquals(prodNames.get(1), "ADIDAS ORIGINAL");
		WebElement checkoutBtn = driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", checkoutBtn);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder=\"Select Country\"]")));
		driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")).sendKeys("Aus");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results button")));
		WebElement country = driver.findElements(By.cssSelector("section.ta-results button")).stream().
				filter(s->s.findElement(By.tagName("span")).getText().equals("Australia")).findFirst().orElse(null);
		country.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'submit')]")));
		WebElement placeOrder = driver.findElement(By.xpath("//a[contains(@class,'submit')]"));
		placeOrder.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

	}

}
