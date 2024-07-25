package comcast.crm.testNg;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryListenerTest {

	@Test(retryAnalyzer = comcast.crm.listenerutility.ListenerImpClass.class)
	public void createInvoiceText() {
		System.out.println("Execute createInvoiceText");
		Assert.assertEquals("", "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}
}
