 package practice.pom.repository;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import comcast.crm.generic.webdriverutility.WebDriverUtility;

public class AutoHealing {
	
	WebDriverUtility wlib = new WebDriverUtility();

	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;
	
	@FindAll({@FindBy(id="submitBu") , @FindBy(xpath = "(//input[@value='Login'])[2]")})
	private WebElement ele3;
	
	
	@Test
	public void sampleTest() {
		
		WebDriver driver = new ChromeDriver();
		
		wlib.waitForPageLoadAndMaximize(driver);
		driver.get("http://localhost:8888");
		
		AutoHealing s = PageFactory.initElements(driver, AutoHealing.class);
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("password");
		
		driver.navigate().refresh();
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("password");
		
		s.ele3.click();
		
		driver.quit();
		
		
		
		
	}
		
	
}
