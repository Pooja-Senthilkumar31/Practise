package listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listeners in TestNG are used to track test execution and perform custom actions 
//(like logging, screenshots, or reporting) when certain events occur (e.g., test start, pass, fail, skip).

import baseclass.BaseClass;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

	// right click, source, override/implement methods, select ISuite and
	// ITestResult (select the below methods as options from that)

	//declaring the variable globally to use it anywhere. The below are for extent report
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report Configuration", true);
		//below code is for extent report
		Date d = new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		
		spark=new ExtentSparkReporter("./AdvanceReports/report_"+newDate+".html"); //file path, provided file name with date and in html extension
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark); //to attach the changes and updated which we need to make (all are updated in spark, hence used the var here)
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "Edge"); //these both are just giving info as key and values
				
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush(); //to get the backup/ save the reports
		Reporter.log("Report Backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		test= report.createTest(result.getMethod().getMethodName()); //written create test here bcz this method is the execution start point
		//the above is assigned to test bcz, It returns an object of type ExtentTest, which we store in test. We use test to log pass/fail/skip/info steps for that particular test.
		test.log(Status.INFO, "===========" + result.getMethod().getMethodName() + " Execution STARTED======="); //log(status) method from extenttest, used in all pass/fail/skip etc
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "==========" + result.getMethod().getMethodName() + " SUCCESS===========");
	}

	@Override
	public void onTestFailure(ITestResult result) { // updated the take screenshot steps here, so that we can take the failure page SS
		String testName = result.getMethod().getMethodName(); // stored the method name in a variable, so we can use wherever required
		test.log(Status.FAIL, "==========" + testName + " FAILURE===========");
		Date d = new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64); //to take report we need in base64 format
		test.addScreenCaptureFromBase64String(src, testName+newDate); //SS will be saved with TC name and date
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "==========" + result.getMethod().getMethodName() + " SKIPPED===========");
	}

}
