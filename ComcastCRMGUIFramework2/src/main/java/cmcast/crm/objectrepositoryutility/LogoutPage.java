package cmcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	WebDriver driver;
	public LogoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement movetoLogoutBtn;
	
		public WebElement getMovetoLogoutBtn() {
		return movetoLogoutBtn;
	}

		
		public void clickOnLogoutBtn() {
			
	Actions action = new Actions(driver);
	action.moveToElement(movetoLogoutBtn).perform();
	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		}	
}
