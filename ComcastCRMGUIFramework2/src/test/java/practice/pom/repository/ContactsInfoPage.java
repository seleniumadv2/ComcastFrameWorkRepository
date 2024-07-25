package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInfoPage {
	
	 WebDriver driver;
		
		public ContactsInfoPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(className ="dvHeaderText")
		private WebElement headerMsg;
		
		@FindBy(id = "dtlview_Last Name")
		private WebElement lastnameVerify;
		
		@FindBy(id="dtlview_Support Start Date")
		private WebElement verifystartDate;
		
		@FindBy(id="dtlview_Support End Date")
		private WebElement verifyendDate;

		public WebElement getVerifystartDate() {
			return verifystartDate;
		}

		public WebElement getVerifyendDate() {
			return verifyendDate;
		}

		public WebDriver getDriver() {
			return driver;
		}

		public WebElement getHeaderMsg() {
			return headerMsg;
		}

		public WebElement getLastnameVerify() {
			return lastnameVerify;
		}

		
}
