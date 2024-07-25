package comcast.crm.orgtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cmcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import cmcast.crm.objectrepositoryutility.HomePage;
import cmcast.crm.objectrepositoryutility.LoginPage;
import cmcast.crm.objectrepositoryutility.LogoutPage;
import cmcast.crm.objectrepositoryutility.OrganizationInfoPage;
import cmcast.crm.objectrepositoryutility.OrganizationsPage;
import comcast.crm.generic.fileutility.ExcelUtility;
import comcast.crm.generic.fileutility.FileUtility;
import comcast.crm.generic.fileutility.JsonUtility;
import comcast.crm.generic.webdriverutility.JavaUtility;
import comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithPhoneNoTest3 {

	public static void main(String[] args) throws Exception {

		// Create the objects
		FileUtility flib = new FileUtility();
		JsonUtility jlib = new JsonUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jalib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		// Read the Common Data from properties file utility
		// String Browser = flib.getDataFromPropertiesFiles("browser");
		String Browser = jlib.getDataFromJsonFile("browser");
		String Url = flib.getDataFromPropertiesFiles("url");
		String Username = flib.getDataFromPropertiesFiles("username");
		String Password = flib.getDataFromPropertiesFiles("password");

		// readtestscripts from excel
		String orgName = elib.getDataFromExcel("org", 7, 2) + jalib.getRandomNumber();
		String phno = elib.getDataFromExcel("org", 7, 3);

		WebDriver driver = null;

		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		// Step 1 : login to app

		wlib.waitForPageLoadAndMaximize(driver);

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(Url, Username, Password);

		// Step 2 : naviagte to Organization module

		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Step 3 : click on "Create Organization" Button

		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4 : Enter all the details and create new organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrgWithPh(orgName, phno);

		// Step 5 : verify header phno info Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphno = oip.getVerifyPhNo().getText();
		if (actphno.equals(phno)) {
			System.out.println(phno + " info is created==PASS");
		} else {
			System.out.println(phno + " info is not created==FAIL");
		}

		// Step 6 : logout
		LogoutPage lOut = new LogoutPage(driver);
		lOut.clickOnLogoutBtn();

		driver.quit();

	}
}
