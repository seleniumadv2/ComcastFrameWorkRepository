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

public class CreateContactWithSupportDateTest5 {

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
			
			String lastname = elib.getDataFromExcel("contact", 4, 2)+ jalib.getRandomNumber();
						
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
			
			
			//Step 2 : naviagte to Organization module
			
			driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		
			// Step 3 : click on "Create Organization" Button
			
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			// Step 4 : Enter all the details and create new organization
			
			String startdate = jalib.getSystemDateYYYYMMDD();
			String enddate = jalib.getRequiredDateYYYYMMDD(30);
			
			
			driver.findElement(By.name("lastname")).sendKeys(lastname);
			
			driver.findElement(By.id("jscal_field_support_start_date")).clear();
			driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startdate);
			//System.out.println(startdate);
			
			driver.findElement(By.id("jscal_field_support_end_date")).clear();
			driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(enddate);
			//System.out.println(enddate);
			
			driver.findElement(By.xpath("(//input[contains(@class,'crmbutton')])[2]")).click();
			
			
			// Step 5 : verify header msg Expected Result
			String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
			if (actlastname.equals(lastname)) {
				System.out.println(lastname + " is verified==PASS");
			}
			else {
				System.out.println(lastname + " is not verified==FAIL");
			}
				
			//verify the date
			String actdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
			if (actdate.equals(startdate)) {
				System.out.println(startdate + "  current date is displayed==PASS");
			}
			else {
				System.out.println(startdate + " current date is not displayed==FAIL");
			}
			
			
			String rtenddt = driver.findElement(By.id("dtlview_Support End Date")).getText();
			if (rtenddt.equals(enddate)) {
				System.out.println(enddate + "  end date is displayed==PASS");
			}
			else {
				System.out.println(enddate + " end date is not displayed==FAIL");
			}
			
			
			//Step 6 : logout
			
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				
			driver.quit();
			
			
		
		
		
		
		
		
		
		
		
	}
}
