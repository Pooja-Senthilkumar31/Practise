package crm.testcaseaftergenericutilityandpom;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import FileUtility.ExcelUtility;
import FileUtility.FileUtility;
import javautility.JavaUtility;
import objectrepository.CampaignPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import webdriverutility.WebDriverUtility;

public class CreateCampaignWithStatusTest {

	public static void main(String[] args) throws InterruptedException, IOException {

		FileUtility pLib = new FileUtility();
		String browser = pLib.readDataFromPropertiesFile("Browser");
		String url = pLib.readDataFromPropertiesFile("URL");
		String username = pLib.readDataFromPropertiesFile("Username");
		String password = pLib.readDataFromPropertiesFile("Password");

		ExcelUtility eLib = new ExcelUtility();
		String campName = eLib.readDataFromExcelFile("Campaign", 4, 2);
		String targetSize = eLib.readDataFromExcelFile("Campaign", 4, 3);
		String status = eLib.readDataFromExcelFile("Campaign", 4, 4);

		JavaUtility jLib = new JavaUtility();
		int randomInt = jLib.getRandomNumber();
		String campaignName = campName + randomInt;
		WebDriverUtility wLib = new WebDriverUtility();

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("CHROME"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		CampaignPage campaignPage = new CampaignPage(driver);

		lp.Login(url, username, password);

		// Create Campaign
		campaignPage.createCampaign(campaignName, targetSize);
		campaignPage.getCampaignStatusTF().sendKeys(status);
		campaignPage.getCreateCampaignSubmitBtn().click();

		// Validation
		wLib.waitforwebelemnttoload(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText();
		System.out.println(msg);
		if (msg.contains(campaignName))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		hp.getCloseToastMsg().click();
		// Logout
		hp.logout();
		driver.quit();

	}

}
