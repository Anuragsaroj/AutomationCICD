package anuragsaroj.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import anuragsaroj.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "(//button[@class='btn btn-custom'])[3]")
	WebElement Click;
	By ProductsBy = By.cssSelector("#toast-container");
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	@FindBy(xpath = "//div/ul/li/div/div/h3")
	List<WebElement> ordercount;

//	driver.findElement(By.xpath("//button[contains(.,'Buy Now')]")).click();
	@FindBy(xpath = "//button[contains(.,'Buy Now')]")
	WebElement buyNow;

	public void ClickOnCart() throws InterruptedException {
		WaitForElement(ProductsBy);
		waitForElementToDisappear(spinner);

		// Wait for toast message and go to cart
		Click.click();

//		List<WebElement> ordercount = driver.findElements(By.xpath("//div/ul/li/div/div/h3"));
//
//		Boolean match = ordercount.stream().anyMatch(order -> order.getText().equalsIgnoreCase(Product));
//		Assert.assertTrue(match);
		// or
//		for (int i = 0; i < ordercount.size(); i++) {
//			String checkorder = ordercount.get(i).getText();
//			if (checkorder.equals(Product)) {
//				driver.findElement(By.xpath("//div/ul/li/div/div/button[1]")).click();
//			}
//
//		}

	}

	public Boolean verifyProduct(String Product) {
		Boolean match = ordercount.stream().anyMatch(order -> order.getText().equalsIgnoreCase(Product));

		return match;
	}

	public PlaceOrder BuyNow() {
		buyNow.click();
		PlaceOrder placeOrder = new PlaceOrder(driver);
		return placeOrder;
	}
}
