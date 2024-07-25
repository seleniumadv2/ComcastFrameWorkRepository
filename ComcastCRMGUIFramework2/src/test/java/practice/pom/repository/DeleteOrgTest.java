package practice.pom.repository;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cmcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import cmcast.crm.objectrepositoryutility.HomePage;
import cmcast.crm.objectrepositoryutility.LoginPage;
import cmcast.crm.objectrepositoryutility.OrganizationInfoPage;
import cmcast.crm.objectrepositoryutility.OrganizationsPage;
import comcast.crm.generic.fileutility.ExcelUtility;
import comcast.crm.generic.fileutility.FileUtility;
import comcast.crm.generic.webdriverutility.JavaUtility;
import comcast.crm.generic.webdriverutility.WebDriverUtility;

public class DeleteOrgTest {
	
	public DeleteOrgTest(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {

			
		// Create the objects
				FileUtility flib = new FileUtility();
				//JsonUtility jlib = new JsonUtility();
				ExcelUtility elib = new ExcelUtility();
				JavaUtility jalib = new JavaUtility();
				WebDriverUtility wlib = new WebDriverUtility();
				
				// Read the Common Data from properties file utility	
				String Browser = flib.getDataFromPropertiesFiles("browser");
				//String Browser = jlib.getDataFromJsonFile("browser");
				String Url = flib.getDataFromPropertiesFiles("url");
				String Username = flib.getDataFromPropertiesFiles("username");
				String Password = flib.getDataFromPropertiesFiles("password");
	
		// readtestscripts from excel
			
			String orgName = elib.getDataFromExcel("org", 10, 2) + jalib.getRandomNumber();
			String dropDown = elib.getDataFromExcel("org", 10, 3);
			
			
			WebDriver driver = null ;
			
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
				
			LoginPage lp = new LoginPage(driver);
				lp.loginToApp(Url, Username, Password);
			
			//Step 2 : naviagte to Organization module
				
			HomePage hp = new HomePage(driver);
			hp.getOrglink().click();
							
			// Step 3 : click on "Create Organization" Button
			
			OrganizationsPage op = new OrganizationsPage(driver);
			op.getCreateNewOrgBtn().click();
		
			// Step 4 : Enter all the details and create new organization
			CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
			cop.createOrg(orgName);
			
			// Step 5 : verify header msg Expected Result
			
			OrganizationInfoPage oip = new OrganizationInfoPage(driver);
			String actOrgName = oip.getHeaderMsg().getText();
			 if (actOrgName.contains(orgName)) {
				System.out.println(orgName + " name is verified==PASS");
			}
			 else {
				System.out.println(orgName + " name is not verified==FAIL");
			}
			
			
			// step-6: Go back to Org page
			 hp.getOrglink().click();
			 
			// Step-7 : Search for Org
			 op.getSearchEdt().sendKeys(orgName);

			 wlib.select(op.getSearchDD(), dropDown);
			 
			 op.getSearchBtn().click();
			 
			 
			 // In dynamic webtable select & delete org
			 
			 driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
			 
			 wlib.switchToAlertAndAccept(driver);
			 
			 
			//Step 8 : logout
				hp.logout();
			
			driver.quit(); 
			
	
		
	}

}
