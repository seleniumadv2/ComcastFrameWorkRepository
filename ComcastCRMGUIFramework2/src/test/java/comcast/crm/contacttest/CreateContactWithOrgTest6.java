package comcast.crm.contacttest;


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
import comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrgTest6 {

	public static void main(String[] args) throws Exception {
		
		// Create the objects
				FileUtility flib = new FileUtility();
				JsonUtility jlib = new JsonUtility();
				ExcelUtility elib = new ExcelUtility();
				JavaUtility jalib = new JavaUtility();
				WebDriverUtility wlib = new WebDriverUtility();
				
				// Read the Common Data from properties file utility	
				//String Browser = flib.getDataFromPropertiesFiles("browser");
				String Browser = jlib.getDataFromJsonFile("browser");
				String Url = flib.getDataFromPropertiesFiles("url");
				String Username = flib.getDataFromPropertiesFiles("username");
				String Password = flib.getDataFromPropertiesFiles("password");
		
				
		// readtestscripts from excel
			String lastname = elib.getDataFromExcel("contact", 7, 3) + jalib.getRandomNumber();
			String orgName = elib.getDataFromExcel("contact", 7, 2) + jalib.getRandomNumber();
					
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
			
			wlib.waitForPageLoadAndMaximize(driver);
			
			driver.get(Url);
			
			driver.findElement(By.name("user_name")).sendKeys(Username);
			driver.findElement(By.name("user_password")).sendKeys(Password);
			driver.findElement(By.id("submitButton")).click();
			
			
			//Step 2 : naviagte to Organization module
			
			driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
			
		
			// Step 3 : click on "Create Organization" Button
			
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			// Step 4 : Enter all the details and create new organization
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
			
			
			driver.findElement(By.xpath("(//input[contains(@class,'crmbutton')])[1]")).click();
			
			
			// Step 5 : verify header msg Expected Result
			
			String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if (headerinfo.contains(orgName)) {
					System.out.println(orgName + " is created==PASS");
				}
				else {
					System.out.println(orgName + " is not created==FAIL");
				}
				
				// Verify Header orgName info expected result
					String actOrg = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
					if (actOrg.equals(orgName)) {
						System.out.println(orgName + " is created==PASS");
					}
					else {
						System.out.println(orgName + " is not created==FAIL");
					}
					
					
					
					//Step-6 navigate to contact 
												
					driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
				
					// Step 7: click on "Create Contact" Button
					
					driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
					
					// Step 8 : Enter all the details and create new contact
					driver.findElement(By.name("lastname")).sendKeys(lastname);
					driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
					
					//Switch to child window
						
					wlib.switchToTabOnUrl(driver, "Accounts&action");
							driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
							driver.findElement(By.xpath("//input[@name='search']")).click();
							driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
											
				
					
					//Switch to parent window
					wlib.switchToTabOnUrl(driver, "Contacts&action");
					
					driver.findElement(By.xpath("(//input[contains(@class,'crmbutton')])[2]")).click();
					
					
					// Step 9 : verify header msg Expected Result
					String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
					if (actlastname.equals(lastname)) {
						System.out.println(lastname + " is created==PASS");
					}
					else {
						System.out.println(lastname + " is not created==FAIL");
					}
					
					
					//verify header orgname 
					String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
					System.out.println(actOrgName);
					if (actOrgName.trim().equals(orgName)) {
						System.out.println(orgName + " info is verified==PASS");
					}
					else {
						System.out.println(orgName + " info is not verified==FAIL");
					}
			
			//Step 10 : logout
			
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
				
			driver.quit();
			
			
		
		
		
		
		
		
		
		
		
	}
}
