package listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;

//Listeners in TestNG are used to track test execution and perform custom actions 
//(like logging, screenshots, or reporting) when certain events occur (e.g., test start, pass, fail, skip).

import baseclass.BaseClass;

public class ListenerImplementationClass implements ITestListener, ISuiteListener {

	// right click, source, override/implement methods, select ISuite and
	// ITestResult (select the below methods as options from that)

	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report Configuration", true);		
		
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report Backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("===========" + result.getMethod().getMethodName() + " Execution STARTED=======", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("==========" + result.getMethod().getMethodName() + " SUCCESS===========", true);
	}

	@Override
	public void onTestFailure(ITestResult result) { // updated the take screenshot steps here, so that we can take the failure page SS
		String testName = result.getMethod().getMethodName(); // stored the method name in a variable, so we can use wherever required
		Reporter.log("==========" + testName + " FAILURE===========", true);
		Date d = new Date();
		String newDate = d.toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/" + testName + newDate + ".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("==========" + result.getMethod().getMethodName() + " SKIPPED===========", true);
	}

}
