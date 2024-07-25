package comcast.crm.testNg;

import org.testng.Assert;
import org.testng.annotations.Test;

import comcast.crm.baseclass.BaseClass;


public class InvoiceTestSC extends BaseClass{

	@Test
	public void createInvoiceText() {
		System.out.println("Execute createInvoiceText");
		String actTitle = driver.getTitle(); 
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
	
	@Test
	public void createInvoiceWithContactText() {
		System.out.println("Execute createInvoiceWithContactText");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
