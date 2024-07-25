package comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import cmcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import cmcast.crm.objectrepositoryutility.HomePage;
import cmcast.crm.objectrepositoryutility.OrganizationInfoPage;
import cmcast.crm.objectrepositoryutility.OrganizationsPage;
import comcast.crm.baseclass.BaseClass;
import comcast.crm.generic.webdriverutility.UtilityClassObject;

@Listeners(comcast.crm.listenerutility.ListenerImpClass.class)

public class CreateOrganization_testNg extends BaseClass {

	@Test(groups = ("smokeTest")) 
	public void createOrganizationTest() throws Exception {

		// readtestscripts from excel
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// Step 2 : naviagte to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org page");
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Step 3 : click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO , "Click on Create org Btn");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4 : Enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Create Org");
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(orgName);
		cop.getSaveBtn().click();

		// Step 5 : verify header msg Expected Result
		UtilityClassObject.getTest().log(Status.INFO, "Verify Header Msg");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		boolean status = actOrgName.contains(orgName);
		Assert.assertEquals(status, true);

	}

	@Test()
	public void createOrgWithIndus() throws Exception {
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		// Step 2 : naviagte to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org module");
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Step 3 : click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on create Org");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4 : Enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Create Org");
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrgWithIndu_Type(orgName, industry, type);

		// Step 5 : verify the industry and type info
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Industry");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);

		String actindu = oip.getVerifyIndus().getText();
		Assert.assertEquals(actindu, industry);
		
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Type");
		String acttype = oip.getTypeVerify().getText();
		Assert.assertEquals(acttype, type);
	}

	@Test
	public void createOrgWithPh() throws Exception {
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String phno = elib.getDataFromExcel("org", 7, 3);

		// Step 2 : naviagte to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate To Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// Step 3 : click on "Create Organization" Button
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create Org");
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();

		// Step 4 : Enter all the details and create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Create Org");
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrgWithPh(orgName, phno);

		// Step 5 : verify header phno info Expected Result
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Header msg");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actphno = oip.getVerifyPhNo().getText();
		Assert.assertEquals(phno, actphno);

	}

}
