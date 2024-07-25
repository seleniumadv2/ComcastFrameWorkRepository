package comcast.crm.baseclass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Parameters;

import cmcast.crm.objectrepositoryutility.HomePage;
import cmcast.crm.objectrepositoryutility.LoginPage;
import comcast.crm.generic.databaseutility.DataBaseUtility;
import comcast.crm.generic.fileutility.ExcelUtility;
import comcast.crm.generic.fileutility.FileUtility;
import comcast.crm.generic.webdriverutility.JavaUtility;
import comcast.crm.generic.webdriverutility.UtilityClassObject;
import comcast.crm.generic.webdriverutility.WebDriverUtility;

public class BaseClass {
	public FileUtility flib = new FileUtility();
	public ExcelUtility elib = new ExcelUtility();
	public WebDriverUtility wlib = new WebDriverUtility();
	public JavaUtility jlib = new JavaUtility();
	public DataBaseUtility dlib = new DataBaseUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() throws Exception {
		System.out.println("====Connect to DB====");
		dlib.getDbConnection();
	}

	// @Parameters("Browser")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBC() throws Exception {
		String Browser = flib.getDataFromPropertiesFiles("browser");

		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else if (Browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		sdriver = driver;
		UtilityClassObject.setDriver(driver);
		wlib.waitForPageLoadAndMaximize(driver);
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() throws Exception {
		String Url = flib.getDataFromPropertiesFiles("url");
		String Username = flib.getDataFromPropertiesFiles("username");
		String Password = flib.getDataFromPropertiesFiles("password");
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(Url, Username, Password);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		HomePage hp = new HomePage(driver);
		hp.logout();
		System.out.println("===logout==PASS");
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("==Close the Browser==");
		driver.quit();

	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() throws Exception {
		dlib.closeDbConnection();
		System.out.println("==Close the DB, Report Backup==");
	}

}
