package anuragsaroj.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import anuragsaroj.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement email= driver.findElement(By.id("userEmail"));

	// Page factory
	@FindBy(id = "userEmail")
	WebElement email;

	// driver.findElement(By.id("userPassword"));
	@FindBy(id = "userPassword")
	WebElement Password;

//	driver.findElement(By.id("login"))
	@FindBy(id = "login")
	WebElement Submit;

//	@FindBy(xpath = "//div[@class='ng-tns-c4-25 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']")
	@FindBy(css = "[class*='flyInOut']")
	WebElement errMsg;

	public ProductCart LoginPage(String Email, String password) {
		email.sendKeys(Email);
		Password.sendKeys(password);
		Submit.click();
		ProductCart prodcut = new ProductCart(driver);
		return prodcut;
	}

	public String getErrorMessage() {
		WaitForWebElement(errMsg);
		return errMsg.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
