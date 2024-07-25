package cmcast.crm.objectrepositoryutility;

import org.testng.annotations.Test;

import comcast.crm.baseclass.BaseClass;

public class CreateOrganization extends BaseClass {

	@Test
	public void createContactTest() throws Exception {

		// readtestscripts from excel
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// Step 2 : naviagte to Organization module

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
		} else {
			System.out.println(orgName + " name is not verified==FAIL");
		}

	}

}
