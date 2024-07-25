package cmcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author Pranav
 * 
 * Contains Login page elements and business lib like login()
 * 
 */
public class LoginPage {	// Rule-1 create a separate java class
	
	//Rule-2 Object creation
	
	
	
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;

			//Rule-3 Object intilization

	//Rule-4 Object Encapsulation
	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	
	
	/**
	 * Login to App based on username , password , url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	//rule-5 provide action
	
	public void loginToApp(String url , String username , String password) {
		
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
}
