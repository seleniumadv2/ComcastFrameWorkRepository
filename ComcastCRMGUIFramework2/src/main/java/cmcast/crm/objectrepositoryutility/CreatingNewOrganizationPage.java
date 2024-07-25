package cmcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdt;
	
	
	@FindBy(xpath =  "(//input[@class='crmbutton small save'])[1]")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDB;
	
	@FindBy(name = "accounttype")
	private WebElement typeDB;
	
	@FindBy(xpath = "//input[@id='phone']")
	private WebElement typePhNo;


	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
	}
	
		public WebElement getIndustryDB() {
		return industryDB;
	}


	public WebElement getTypeDB() {
		return typeDB;
	}
	
	public WebElement getTypePhNo() {
		return typePhNo;
	}


	public void createOrgWithIndu_Type(String orgName , String industry ,String type) {
		orgNameEdt.sendKeys(orgName);
		Select sel = new Select(industryDB);
		sel.selectByVisibleText(industry);
		Select sel1 = new Select(typeDB);
		sel1.selectByVisibleText(type);
		saveBtn.click();
	}
	
	public void createOrgWithPh(String orgName , String phoneNo) {
		orgNameEdt.sendKeys(orgName);
		typePhNo.sendKeys(phoneNo);
		saveBtn.click();
	}
}