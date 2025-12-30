package anuragsaroj.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import anuragsaroj.AbstractComponents.AbstractComponent;

public class PlaceOrder extends AbstractComponent {
	WebDriver driver;

	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// driver.findElement(By.cssSelector(".input.txt.text-validated:nth-child(1)")).sendKeys("Ind");
	@FindBy(css = ".input.txt.text-validated:nth-child(1)")
	WebElement Conutry;

	// driver.findElement(By.xpath("//input[@value='4542 9931 9292
	// 2293']")).sendKeys("4542 9931 9292 2293");
	@FindBy(xpath = "//input[@value='4542 9931 9292 2293']")
	WebElement cardNumber;

	// WebElement dropDown = driver.findElement(By.xpath("//select[@class='input
	// ddl'][1]"));
	@FindBy(xpath = "//select[@class='input ddl'][1]")
	WebElement dropDown;
	@FindBy(xpath = "//select[@class='input ddl'][2]")
	WebElement dropDownMonth;

	@FindBy(xpath = "(//input[@class='input txt'])[1]")
	WebElement Cvv;
	@FindBy(xpath = "(//input[@class='input txt'])[2]")
	WebElement cardName;

	@FindBy(xpath = "//input[@name='coupon']")
	WebElement couponName;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement ApplyCoupon;

	By wait = By.xpath("//p[@class='mt-1 ng-star-inserted']");

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement clickplaceOrder;

	public void SelectCountry(String conutry) {
		Conutry.sendKeys(conutry);
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,450)");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//span)[4]"))).click().build().perform();

	}

	public void Creditcard() {
		cardNumber.sendKeys("4542 9931 9292 2293");

	}

	public void selectDayMonth() {
		Select select = new Select(dropDown);
		select.selectByVisibleText("03");

		Select selectMonth = new Select(dropDownMonth);
		selectMonth.selectByIndex(5);

	}

	public void CvvNameOnCard() {
		Cvv.sendKeys("3242");
		cardName.sendKeys("Anurag saroj");

	}

	public void Coupon() {
		couponName.sendKeys("rahulshettyacademy");
		ApplyCoupon.click();
		WaitForElement(wait);
		String couponval = driver.findElement(By.xpath("//p[@class='mt-1 ng-star-inserted']")).getText();
		Assert.assertEquals(couponval, "* Coupon Applied");

	}

	public ConfirmationPage placeOrder() {
		clickplaceOrder.click();
		ConfirmationPage confirmation = new ConfirmationPage(driver);
		return confirmation;
	}

}
