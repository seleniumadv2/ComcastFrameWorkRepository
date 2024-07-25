package comcast.crm.listenerutility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import comcast.crm.baseclass.BaseClass;
import comcast.crm.generic.webdriverutility.JavaUtility;
import comcast.crm.generic.webdriverutility.UtilityClassObject;


public class ListenerImpClass implements ITestListener , ISuiteListener ,IRetryAnalyzer {

	JavaUtility jlib = new JavaUtility();
	

	public ExtentSparkReporter spark;
	public ExtentReports report ;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report Configuration");
		
		//Spark report Config
		spark = new ExtentSparkReporter("./AdvanceReport/report"+jlib.getCurrentTime()+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//Add Env Info & Create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window-10");
		report.setSystemInfo("BROWSER", "CHROME-120");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======>"+result.getMethod().getMethodName()+">=========");
			 test = report.createTest(result.getMethod().getMethodName());
			 UtilityClassObject.setTest(test);
			 test.log(Status.INFO, result.getMethod().getMethodName()+"===> STARTED <===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("======>"+result.getMethod().getMethodName()+">=====END=====");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"===> COMPLETED <===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String testName = result.getMethod().getMethodName();
		
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		
		//String date = jlib.getSystemDateYYYYMMDD();
		String time = jlib.getCurrentTime();
		test.addScreenCaptureFromBase64String(filepath, testName+"_"+time);
		 test.log(Status.FAIL, result.getMethod().getMethodName()+"===> FAILED <===");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

	
	
	int count = 0;
	int limitCount = 5;	 
	@Override
	public boolean retry(ITestResult result) {
		if (count<limitCount) {
			count++;
			return true;
		}
		return false;
	}

		
}
