package anuragsaroj.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import anuragsaroj.pageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String email = "anuragsaroj@gamil.com";
		String Password = "Anurag@123";
		String Product = "ZARA COAT 3";
	//	String conutry = "India";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Navigate to the application
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		// Login
		LandingPage Landing = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(Password);
		driver.findElement(By.id("login")).click();

		// Find and add product to cart
		List<WebElement> collect = driver.findElements(By.xpath("//div/h5"));

		for (int i = 0; i < collect.size(); i++) {
			String singleCollect = collect.get(i).getText();
			if (singleCollect.equals(Product)) {
				driver.findElements(By.xpath("//div/button[2]")).get(i).click();

			}

		}
		// Wait for toast message and go to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click();

		// Verify product in cart
		List<WebElement> ordercount = driver.findElements(By.xpath("//div/ul/li/div/div/h3"));

		Boolean match = ordercount.stream().anyMatch(order -> order.getText().equalsIgnoreCase(Product));
		Assert.assertTrue(match);
		// or
//		for (int i = 0; i < ordercount.size(); i++) {
//			String checkorder = ordercount.get(i).getText();
//			if (checkorder.equals(Product)) {
//				driver.findElement(By.xpath("//div/ul/li/div/div/button[1]")).click();
//			}
//
//		}

		// Proceed to checkout
		driver.findElement(By.xpath("//button[contains(.,'Buy Now')]")).click();

		// Select country
		driver.findElement(By.cssSelector(".input.txt.text-validated:nth-child(1)")).sendKeys("Ind");
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0,450)");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//span)[4]"))).click().build().perform();

		// Fill payment details
		driver.findElement(By.xpath("//input[@value='4542 9931 9292 2293']")).sendKeys("4542 9931 9292 2293");

		// Select expiry date
		WebElement dropDown = driver.findElement(By.xpath("//select[@class='input ddl'][1]"));
		Select select = new Select(dropDown);
		select.selectByVisibleText("03");

		// Select expiry Months
		WebElement dropDownMonth = driver.findElement(By.xpath("//select[@class='input ddl'][2]"));
		Select selectMonth = new Select(dropDownMonth);
		selectMonth.selectByIndex(5);

		// Enter CVV and cardholder name
		driver.findElement(By.xpath("(//input[@class='input txt'])[1]")).sendKeys("083");
		driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).sendKeys("Anurag Saroj");

		// Apply coupon and verify
		driver.findElement(By.xpath("//input[@name='coupon']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='mt-1 ng-star-inserted']")));
		String couponval = driver.findElement(By.xpath("//p[@class='mt-1 ng-star-inserted']")).getText();
		Assert.assertEquals(couponval, "* Coupon Applied");

		// Place the order
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

		// Verify confirmation message
		String verifyMesg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(verifyMesg, "THANKYOU FOR THE ORDER.");

		// Close browser
		driver.quit();

	}

}
