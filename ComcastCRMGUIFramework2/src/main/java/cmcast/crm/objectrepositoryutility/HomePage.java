package cmcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Products")
	private WebElement productlnk;
	
	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
	@FindBy(linkText = "Contacts")
	private WebElement contactlnk;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignlnk;
	
	@FindBy(linkText = "More")
	private WebElement morelnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText  = "Sign Out")
	private WebElement signOutLnk;

	public WebElement getCampaignlnk() {
		return campaignlnk;
	}

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getProductlnk() {
		return productlnk;
	}

	public WebElement getAdminImg() {
		return adminImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}

	public WebElement getContactlnk() {
		return contactlnk;
	}



	public WebElement getMorelnk() {
		return morelnk;
	}



	public void navigateToCampaignPAge() {
		Actions act = new Actions(driver);
		act.moveToElement(morelnk).perform();
		campaignlnk.click();
	}
	
	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOutLnk.click();
	}
	
}
