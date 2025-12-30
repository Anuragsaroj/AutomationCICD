package anuragsaroj.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import anuragsaroj.TestComponenets.BaseTest;
import anuragsaroj.pageobject.CartPage;
import anuragsaroj.pageobject.ConfirmationPage;
import anuragsaroj.pageobject.OrderPage;
import anuragsaroj.pageobject.PlaceOrder;
import anuragsaroj.pageobject.ProductCart;

public class SubmitOrderTestt extends BaseTest {

	String ProductName = "ZARA COAT 3";

	@Test(dataProvider = "GetData", groups = "hello")
	public void SubmitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		String conutry = "India";
		// Login
		ProductCart prodcut = Landing.LoginPage(input.get("email"), input.get("Password"));

		// Find and add product to cart
		CartPage cartpage = prodcut.getProdcutName(input.get("Product"));

		// clicking on cartPage
		cartpage.ClickOnCart();

		// Verify product in cart
		boolean match = cartpage.verifyProduct(input.get("Product"));
		Assert.assertTrue(match);
		PlaceOrder placeOrder = cartpage.BuyNow();

		// Select country
		placeOrder.SelectCountry(conutry);

		// Calling Days and months function
		placeOrder.selectDayMonth();

		// Enter CVV and Card-holder name
		placeOrder.CvvNameOnCard();

		// Apply coupon and verify
		placeOrder.Coupon();
		ConfirmationPage confirmation = placeOrder.placeOrder();

		// Confirmation page
		String finalmesg = confirmation.confirmation();
		Assert.assertEquals(finalmesg, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "SubmitOrderTest" })
	public void OrderHistory() {
		Landing.LoginPage("anuragsaroj@gamil.com", "Anurag@123");
		OrderPage order = new OrderPage(driver);
		boolean finaql = order.VerifyOrderDisplay();
		Assert.assertTrue(finaql);
	}

	@DataProvider
	public Object[][] GetData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "anuragsaroj@gamil.com");
//		map.put("Password", "Anurag@123");
//		map.put("Product", "ZARA COAT 3");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "anuragsaroj@gamil.com");
//		map1.put("Password", "Anurag@123");
//		map1.put("Product", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\anuragsaroj\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	@DataProvider
//	public Object[][] GetData() {
//		return new Object[][] { { "anuragsaroj@gamil.com", "Anurag@123", "ZARA CAT 3" },
//				{ "anuragsaroj@gamil.com", "Anurag@123", "ADIDAS ORIGINAL" } };
//		// Then in code we should not use like (public void
//		// SubmitOrderTest(HashMap<String, String> input) throws IOException {)
//		// It should be like (public void SubmitOrderTest(String email,String
//		// Password,String Product) throws IOException {)
//
//	}

}
