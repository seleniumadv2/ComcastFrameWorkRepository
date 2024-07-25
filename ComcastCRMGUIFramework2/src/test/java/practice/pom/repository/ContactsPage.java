package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

WebDriver driver;
	
	public ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createNewContactButton;
	
	@FindBy(name = "search_text")
	private WebElement searchtextEdit;
	
	@FindBy(id = "bas_searchfield")
	private WebElement searchDd;
	
	@FindBy(name = "submit")
	private WebElement searchBtn;

	public WebElement getsearchBtn() {
		return searchBtn;
	}

	public WebElement getSearchDd() {
		return searchDd;
	}

	public WebElement getSearchtextEdit() {
		return searchtextEdit;
	}

	public WebElement getCreateNewContactButton() {
		return createNewContactButton;
	}
	
	
	

}
