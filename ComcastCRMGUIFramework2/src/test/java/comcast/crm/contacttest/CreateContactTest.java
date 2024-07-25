package comcast.crm.contacttest;

/**
 * 
 * @author Pranav
 * 
 */
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import cmcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import cmcast.crm.objectrepositoryutility.HomePage;
import cmcast.crm.objectrepositoryutility.OrganizationInfoPage;
import cmcast.crm.objectrepositoryutility.OrganizationsPage;
import comcast.crm.baseclass.BaseClass;
import comcast.crm.generic.webdriverutility.UtilityClassObject;
import practice.pom.repository.ContactsInfoPage;
import practice.pom.repository.ContactsPage;
import practice.pom.repository.CreatingNewContactsPage;

 public class CreateContactTest extends BaseClass {	
	@Test(groups = {"smokeTest", "regressionTest"}) 
	public void createContactTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		String contactname = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		System.out.println(contactname);
		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		CreatingNewContactsPage cncp = new CreatingNewContactsPage(driver);
		ContactsInfoPage cip = new ContactsInfoPage(driver);

		// navigate to contacts module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to contact");
		hp.getContactlnk().click();
		cp.getCreateNewContactButton().click();
		cncp.getLastnametextEdit().sendKeys(contactname);

		cncp.getSaveBtn().click();

		// verify the header name
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Header name");
		String actualcontact = cip.getHeaderMsg().getText();
		boolean status = actualcontact.contains(contactname);
		Assert.assertEquals(status, true);
			
		// verify the last Name 
		UtilityClassObject.getTest().log(Status.INFO, "Verify the last name");
		String actlastnmae = cip.getLastnameVerify().getText();
		SoftAssert asserObj = new SoftAssert();
		asserObj.assertEquals(actlastnmae, contactname);
	}
	

	@Test(groups = ("regressionTest"))
	public void createContactWithOrgTest() throws Throwable {
		

		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgname = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactname = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();
		HomePage hp = new HomePage(driver);
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		// navigate to organization module

		UtilityClassObject.getTest().log(Status.INFO, "navigate to org page");
		hp.getOrglink().click();
		op.getCreateNewOrgBtn().click();

		// step4: enter all the details and create new organization

		UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		cnop.createOrg(orgname);
		cnop.getSaveBtn().click();

		// verify the organization by name

		UtilityClassObject.getTest().log(Status.INFO, "Verify the org name");
		String actualorgname = oip.getHeaderMsg().getText();
		boolean status = actualorgname.contains(orgname);
		Assert.assertEquals(status, true);

		ContactsPage cp = new ContactsPage(driver);
		CreatingNewContactsPage cncp = new CreatingNewContactsPage(driver);
		ContactsInfoPage cip = new ContactsInfoPage(driver);

		// navigate to contacts module

		UtilityClassObject.getTest().log(Status.INFO, "create contact ");
		hp.getContactlnk().click();
		cp.getCreateNewContactButton().click();
		cncp.getLastnametextEdit().sendKeys(contactname);
		cncp.getSelectOrgBtn().click();
		wlib.switchToTabOnUrl(driver, "module=Accounts&action");
		cncp.getSearchtxtEdit().sendKeys(orgname);
		cncp.getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		wlib.switchToTabOnUrl(driver, "Contacts&action");
		cncp.getSaveBtn().click();

		// verify the contact by name

		UtilityClassObject.getTest().log(Status.INFO, "verify the contact name");
		String actualcontact = cip.getHeaderMsg().getText();
		boolean status1 = actualcontact.contains(contactname);
		Assert.assertEquals(status1, true);
	}
	
	
	@Test(groups = ("integerationTest"))
	public void createContactWithSupportDateTest() throws Throwable {
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String contactname = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();
		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		CreatingNewContactsPage cncp = new CreatingNewContactsPage(driver);
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String startdate = jlib.getSystemDateYYYYMMDD();
		String enddate = jlib.getRequiredDateYYYYMMDD(30);
		driver.findElement(By.linkText("Contacts")).click();
 
		UtilityClassObject.getTest().log(Status.INFO, "Create Contact while entering date");
		hp.getContactlnk().click();
		cp.getCreateNewContactButton().click();
		cncp.getLastnametextEdit().sendKeys(contactname);
		cncp.getStartdateEdit().clear();
		cncp.getStartdateEdit().sendKeys(startdate);

		cncp.getEnddateEdit().clear();
		cncp.getEnddateEdit().sendKeys(enddate);

		cncp.getSaveBtn().click();

		String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(text);

		// verify the contact by name
		UtilityClassObject.getTest().log(Status.INFO, "Verify the contact name");
		String actualcontact = cip.getHeaderMsg().getText();
		boolean status = actualcontact.contains(contactname);
		Assert.assertEquals(status, true);
	
		// verify support date
		UtilityClassObject.getTest().log(Status.INFO, "Verify the date");
		String start = cip.getVerifystartDate().getText();
		Assert.assertEquals(start, startdate);
		
		String end = cip.getVerifyendDate().getText();
		Assert.assertEquals(end, enddate);
	}
}
