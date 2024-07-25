package comcast.crm.contacttest;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import comcast.crm.generic.fileutility.ExcelUtility;
import comcast.crm.generic.fileutility.FileUtility;
import comcast.crm.generic.fileutility.JsonUtility;
import comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest4 {

	public static void main(String[] args) throws Exception {
		
		
	// Create the objects
		FileUtility flib = new FileUtility();
		JsonUtility jlib = new JsonUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jalib = new JavaUtility();
		
		// Read the Common Data from properties file utility	
		//String Browser = flib.getDataFromPropertiesFiles("browser");
		String Browser = jlib.getDataFromJsonFile("browser");
		String Url = flib.getDataFromPropertiesFiles("url");
		String Username = flib.getDataFromPropertiesFiles("username");
		String Password = flib.getDataFromPropertiesFiles("password");
		
		
		// readtestscripts from excel
			String lastname = elib.getDataFromExcel("contact", 1 , 2) + jalib.getRandomNumber();
			
			WebDriver driver = null;
			
			if (Browser.equals("chrome")) {
				driver = new ChromeDriver();
			}
			else if (Browser.equals("edge")) {
				driver = new EdgeDriver();
			}
			else if (Browser.equals("firefox")) {
				driver = new FirefoxDriver();
			}
			else {
				driver = new ChromeDriver();
			}
			
			
			//Step 1 : login to app
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
			
			driver.manage().window().maximize();
			driver.get(Url);
			
			driver.findElement(By.name("user_name")).sendKeys(Username);
			driver.findElement(By.name("user_password")).sendKeys(Password);
			driver.findElement(By.id("submitButton")).click();
			
			
			//Step 2 : naviagte to Contact module
			
			driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		
			// Step 3 : click on "Create Contact" Button
			
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			// Step 4 : Enter all the details and create new contact
			driver.findElement(By.name("lastname")).sendKeys(lastname);
			
			driver.findElement(By.xpath("(//input[contains(@class,'crmbutton')])[2]")).click();
			
			
			// Step 5 : verify header msg Expected Result
			String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
			if (actlastname.equals(lastname)) {
				System.out.println(lastname + " is created==PASS");
			}
			else {
				System.err.println(lastname + " is not created==FAIL");
			}
				
			//Step 6 : logout
			
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				
			driver.quit();
			
			
		
		
		
		
		
		
		
		
		
	}
}
