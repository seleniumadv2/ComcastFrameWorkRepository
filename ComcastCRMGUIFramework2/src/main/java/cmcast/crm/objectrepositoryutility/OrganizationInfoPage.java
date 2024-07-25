package cmcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(xpath = "//span[@id='dtlview_Industry']/font")
	private WebElement verifyIndus;
	
	@FindBy(xpath = "//span[@id='dtlview_Type']/font")
	private WebElement typeVerify;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement verifyPhNo;

	public WebElement getVerifyPhNo() {
		return verifyPhNo;
	}

	public WebElement getHeaderMsg() {
		return headerMsg; 
	}

	public WebElement getVerifyIndus() {
		return verifyIndus;
	}

	public WebElement getTypeVerify() {
		return typeVerify;
	}
	
	
	
	
	
	
}
