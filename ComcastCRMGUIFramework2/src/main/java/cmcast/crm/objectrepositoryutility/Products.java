package cmcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Products {

	WebDriver driver;
	public Products(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@alt='Create Product...']")
	private WebElement createProductImgBtn;
	
	@FindBy(xpath = "(//input[@type='button'])[1]")
	private WebElement searchTextEle1;
	
	@FindBy(xpath = "(//input[@type='button'])[1]")
	private WebElement searchBtnEle2;
	
	public WebElement getCreateProductImgBtn() {
		return createProductImgBtn;
	}

	public WebElement getSearchBtnEle2() {
		return searchBtnEle2;
	}

	public WebElement getSearchTextEle1() {
		return searchTextEle1;
	}
	
	
	
}
