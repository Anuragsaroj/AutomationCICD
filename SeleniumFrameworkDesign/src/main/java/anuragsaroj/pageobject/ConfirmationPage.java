package anuragsaroj.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	WebDriver driver;

	@FindBy(css = ".hero-primary")
	WebElement verifyMesg;


	public ConfirmationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public String confirmation() {
		String finalmesg = verifyMesg.getText();
		return finalmesg;
	}

}
