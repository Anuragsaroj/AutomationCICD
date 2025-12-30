package anuragsaroj.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import anuragsaroj.AbstractComponents.AbstractComponent;

public class ProductCart extends AbstractComponent {
	WebDriver driver;

	public ProductCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> collect = driver.findElements(By.xpath("//div/h5"));
	@FindBy(xpath = "//div/h5")
	List<WebElement> collect;

	public List<WebElement> Productcart() {
		return collect;
	}

	public CartPage getProdcutName(String Product) {
		Productcart();
		for (int i = 0; i < collect.size(); i++) {
			String singleCollect = collect.get(i).getText();
			if (singleCollect.equals(Product)) {
				driver.findElements(By.xpath("//div/button[2]")).get(i).click();
			}
		}
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

}
