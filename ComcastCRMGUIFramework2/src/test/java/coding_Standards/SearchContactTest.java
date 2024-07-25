package coding_Standards;

import org.testng.annotations.Test;

import cmcast.crm.objectrepositoryutility.LoginPage;
import comcast.crm.baseclass.BaseClass;


/**
 * test class for Contact module
 * @author Pranav
 */
public class SearchContactTest extends BaseClass {
	/**
	 * Scenario : Login()==> navigate to Contact==> createcontact()===Verify 
	 */
	
	
	@Test
	public void searchContactText()  {
		/*Step-1 : Login to App*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
