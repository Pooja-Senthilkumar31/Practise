package baseclass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import FileUtility.FileUtility;
import objectrepository.HomePage;
import objectrepository.LoginPage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	public FileUtility pLib = new FileUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver=null; //only static obj or using method name we can call in other pacakges, 
//but here these are annotation class and if we make driver as static, while dng parallel execution (static will have 1 copy at a time)
//so it won't wrk, hence created sdriver as static and in below class initialized sdriver=driver.
	
	@BeforeSuite (groups= {"Smoke","Regression"})
	public void beforeSuite() {
		System.out.println("Establish the database connection");

	}

	@BeforeTest (groups= {"Smoke","Regression"})
	public void beforeTest() {
		System.out.println("precondition");
	}
 
//	@Parameters("Browser") //passing the parameters and it's name argument here to pass the value and in method name pass this by substitute var
	@BeforeClass (groups= {"Smoke","Regression"})
	public void beforeClass() throws IOException { //passed String Browser (which is ref varibale) for parameters arguments
		String browser = pLib.readDataFromPropertiesFile("Browser");  //bcz of parallel executoin using cross browser testing
		if (browser.equalsIgnoreCase("CHROME"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();
		System.out.println("Launch the browser");
		sdriver=driver;
	}

	@BeforeMethod (groups= {"Smoke","Regression"})
	public void beforeMethod() throws IOException {
		String url = pLib.readDataFromPropertiesFile("URL");
		String username = pLib.readDataFromPropertiesFile("Username");
		String password = pLib.readDataFromPropertiesFile("Password");
	     LoginPage lp = new LoginPage(driver);
		lp.Login(url, username, password);
		System.out.println("Login");
	}

	@AfterMethod (groups= {"Smoke","Regression"})
	public void afterMethod() {
        HomePage hp = new HomePage(driver);
		hp.logout();
		System.out.println("Logout");
	}

	@AfterClass (groups= {"Smoke","Regression"})
	public void afterClass() {
		driver.quit();
		System.out.println("Close the browser");
	}

	@AfterTest (groups= {"Smoke","Regression"})
	public void afterTest() {
		System.out.println("Post condition");
	}

	@AfterSuite (groups= {"Smoke","Regression"})
	public void afterSuite() {
		System.out.println("Close database connection");
		System.out.println("Modify");
		System.out.println("Modify");
	}

}