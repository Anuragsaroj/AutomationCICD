package anuragsaroj.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import anuragsaroj.TestComponenets.BaseTest;
import anuragsaroj.TestComponenets.Retry;
import anuragsaroj.pageobject.CartPage;
import anuragsaroj.pageobject.ProductCart;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "Smoke" }, retryAnalyzer = Retry.class)
	public void submitOrder() throws IOException {

		Landing.LoginPage("anuragsaroj@gamil.com", "Anurag@1253");
		String errMsg = Landing.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errMsg);

	}

	@Test
	public void ProductValidation() throws IOException, InterruptedException {
		String Product = "ZARA COAT 3";

		ProductCart prodcut = Landing.LoginPage("anuragsaroj@gamil.com", "Anurag@123");
		CartPage cartpage = prodcut.getProdcutName(Product);
		cartpage.ClickOnCart();
		// Verify product in cart
		boolean match = cartpage.verifyProduct(Product);
		Assert.assertTrue(match);

	}

}
