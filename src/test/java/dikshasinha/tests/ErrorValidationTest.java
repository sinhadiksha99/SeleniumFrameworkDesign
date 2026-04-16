package dikshasinha.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import dikshasinha.TestComponents.BaseTest;
import dikshsinha.pageObjects.CartPage;
import dikshsinha.pageObjects.ProductCataloguePage;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"errorHandling"})
	public void loginErrorValidation() throws InterruptedException, IOException {
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("diksha@gmail.com", "Diksha@123");
		String errorMsg = productCataloguePage.getErrorMsg();
		Assert.assertEquals(errorMsg, "Incorrect email or password.");
	}
	

	@Test(groups= {"errorHandling"}, retryAnalyzer=dikshasinha.TestComponents.Retry.class)
	public void productErrorValidation() throws InterruptedException, IOException {
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("dikshasinha@gmail.com", "Diksha@123");
		
		productCataloguePage.addProductToCart("ZARA COAT 3");
		CartPage cart = productCataloguePage.goToCartPage();
		
		boolean prodMatch1 = cart.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(prodMatch1);
	}

}
 