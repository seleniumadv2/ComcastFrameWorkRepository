package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactsPage {
	
	WebDriver driver;
	public CreatingNewContactsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastnametextEdit;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement selectOrgBtn;
	
	@FindBy(id = "jscal_field_support_start_date")
	private WebElement startdateEdit;
	
	@FindBy(id = "jscal_field_support_end_date")
	private WebElement enddateEdit;
	
	public WebElement getStartdateEdit() {
		return startdateEdit;
	}

	public WebElement getEnddateEdit() {
		return enddateEdit;
	}

	public WebElement getLastnametextEdit() {
		return lastnametextEdit;
	}

	public WebElement getSelectOrgBtn() {
		return selectOrgBtn;
	}

	public WebElement getSearchtxtEdit() {
		return searchtxtEdit;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	@FindBy(xpath="//input[@name='search_text']")
	private WebElement searchtxtEdit;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	@FindBy(name = "assigned_user_id")
	private WebElement assignedToDd;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveBtn;
	
	public WebElement getAssignedToDd() {
		return assignedToDd;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	
}
