package comcast.crm.orgtest;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
//import java.io.FileNotFoundException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cmcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import cmcast.crm.objectrepositoryutility.HomePage;
import cmcast.crm.objectrepositoryutility.LoginPage;
import cmcast.crm.objectrepositoryutility.LogoutPage;
import cmcast.crm.objectrepositoryutility.OrganizationInfoPage;
import cmcast.crm.objectrepositoryutility.OrganizationsPage;
import comcast.crm.generic.webdriverutility.JavaUtility;
import comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateOrganizationWithIndustriesTest2 {

	public static void main(String[] args) throws Exception {

		// Common Data
		FileInputStream fis = new FileInputStream("C:\\Users\\hp\\Desktop\\data\\commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);

		WebDriver driver = null;

		WebDriverUtility wlib = new WebDriverUtility();
		

		String Browser = pobj.getProperty("browser");
		String Url = pobj.getProperty("url");
		String Username = pobj.getProperty("username");
		String Password = pobj.getProperty("password");

		// Generate the random number
		JavaUtility jlib = new JavaUtility();

		// readtestscripts from excel
		
		FileInputStream fis1 = new FileInputStream("C:\\Users\\hp\\Desktop\\data\\GU_Automatication.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(4);
		String orgName = row.getCell(2).toString() + jlib.getRandomNumber();
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
		wb.close();

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
		cop.createOrgWithIndu_Type(orgName, industry, type);

		// Step 5 : verify the industry and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actindu = oip.getVerifyIndus().getText();
		
		if (actindu.trim().contains(industry)) {
			System.out.println(industry + " info is verified==PASS");
		} else {
			System.out.println(industry + " info is not verified==FAIL");
		}

		String acttype = oip.getTypeVerify().getText();

		if (acttype.equals(type)) {
			System.out.println(type + " info is verified==PASS");
		} else {
			System.out.println(type + " info is not verified==FAIL");
		}

		// Step 6 : logout
		LogoutPage lOut = new LogoutPage(driver);
		lOut.clickOnLogoutBtn();

		driver.quit();

	}
}
