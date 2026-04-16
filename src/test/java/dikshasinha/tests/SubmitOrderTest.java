package dikshasinha.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dikshasinha.TestComponents.BaseTest;
import dikshsinha.pageObjects.CartPage;
import dikshsinha.pageObjects.CheckoutPage;
import dikshsinha.pageObjects.ConfirmationPage;
import dikshsinha.pageObjects.OrderPage;
import dikshsinha.pageObjects.ProductCataloguePage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest{

//	@Test(dataProvider="dpMethod")
//	@Test(dataProvider="hashMapMethod")
	@Test(dataProvider="jsonToHashMapMethod")
	public void loginAndaddtoCart(HashMap<String,String> map) throws InterruptedException, IOException {
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("dikshasinha@gmail.com", "Diksha@123");
		
		productCataloguePage.addProductToCart(map.get("prod1"));
		productCataloguePage.addProductToCart(map.get("prod2"));
		CartPage cart = productCataloguePage.goToCartPage();
		
		boolean prodMatch1 = cart.verifyProductDisplay("ZARA COAT 3");
		boolean prodMatch2 = cart.verifyProductDisplay("ADIDAS ORIGINAL");
		Assert.assertTrue( prodMatch1&&prodMatch2 );
		CheckoutPage checkout = cart.goToCheckout();
		
		ConfirmationPage confirmationPage = checkout.selectCountry("aus", "Austria");
		String confirmationMsg = confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmationMsg,"THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"loginAndaddtoCart"})
	public void orderHistoryTest(){
		ProductCataloguePage productCataloguePage = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage order = productCataloguePage.goToOrderPage();
		boolean match = order.verifyOrderDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}

	@DataProvider
	public Object[][] dpMethod(){
		return new Object[][] {{"ZARA COAT 3","ADIDAS ORIGINAL"}};
	}
	
	@DataProvider
	public Object[][] hashMapMethod(){
		HashMap<String, String> map = new HashMap<>();
		map.put("prod1","ZARA COAT 3");
		map.put("prod2","ADIDAS ORIGINAL");
		return new Object[][] {{map}};
	}
	
	@DataProvider
	public Object[][] jsonToHashMapMethod() throws IOException{
		String currDir = System.getProperty("user.dir");
		List<HashMap<String, String>> mapList = getJsonDataToMap(currDir + "/src/test/java/dikshasinha/data/PurchaseOrder.json");
		return new Object[][] {{mapList.get(0)},{mapList.get(1)}};
	}
	
}
 