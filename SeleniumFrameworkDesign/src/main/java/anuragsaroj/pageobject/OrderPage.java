package anuragsaroj.pageobject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import anuragsaroj.AbstractComponents.AbstractComponent;
import net.bytebuddy.asm.Advice.This;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
	WebElement order;
	
	@FindBy(xpath = "//tr//td[2]")
	List<WebElement> orderName;

	public boolean  VerifyOrderDisplay() {
		order.click();
	  boolean name = orderName.stream().anyMatch(product-> product.getText().equalsIgnoreCase("ZARA COAT 3"));
		return  name;

	}

}
