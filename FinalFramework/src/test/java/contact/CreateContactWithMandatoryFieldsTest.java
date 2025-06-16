package contact;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import FileUtility.ExcelUtility;
import FileUtility.FileUtility;
import javautility.JavaUtility;
import objectrepository.CampaignPage;
import objectrepository.ContactPage;
import objectrepository.CreateContactPage;
import objectrepository.HomePage;
import objectrepository.LoginPage;
import objectrepository.SelectCampaignPage;
import webdriverutility.WebDriverUtility;

public class CreateContactWithMandatoryFieldsTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		FileUtility pLib = new FileUtility();
		String browser = pLib.readDataFromPropertiesFile("Browser");
		String url = pLib.readDataFromPropertiesFile("URL");
		String username = pLib.readDataFromPropertiesFile("Username");
		String password = pLib.readDataFromPropertiesFile("Password");

		ExcelUtility eLib = new ExcelUtility();
		String campName = eLib.readDataFromExcelFile("Contact", 1, 2);
		String targetSize = eLib.readDataFromExcelFile("Contact", 1, 3);
		String organization = eLib.readDataFromExcelFile("Contact", 1, 4);
		String title = eLib.readDataFromExcelFile("Contact", 1, 5);
		String contact = eLib.readDataFromExcelFile("Contact", 1, 6);
		String mobile = eLib.readDataFromExcelFile("Contact", 1, 7);
		String windowTitle = eLib.readDataFromExcelFile("Contact", 1, 8);

		JavaUtility jLib = new JavaUtility();
		int randomInt = jLib.getRandomNumber();
		String campaignName = campName + randomInt;
		System.out.println(campaignName);

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
		ContactPage contactsPage = new ContactPage(driver);
		CreateContactPage createContactPage = new CreateContactPage(driver);
		SelectCampaignPage selectCampaignPage = new SelectCampaignPage(driver);

		lp.Login(url, username, password);

		// Create Campaign
		campaignPage.createCampaign(campaignName, targetSize);
		campaignPage.getCreateCampaignSubmitBtn().click();

		// Creating contact
		hp.getContactsBtn().click();
		wLib.waitforwebelemnttoload(driver, hp.getToastMsg());
		String msg = hp.getToastMsg().getText(); 
		  if (msg.contains(campaignName)) 
		   System.out.println("Campaign Created"); 
		  else 
		   System.out.println("Campaign Not Created");
		hp.getCloseToastMsg().click();

		contactsPage.getCreateContactBtn().click();

		createContactPage.getOrganizationNameTF().sendKeys(organization);
		createContactPage.getTitleTF().sendKeys(title);
		createContactPage.getContactNameTF().sendKeys(contact);
		createContactPage.getMobileTF().sendKeys(mobile);
		
		createContactPage.getPlusBtn().click();
		String parentId = driver.getWindowHandle();
		wLib.switchToWindowOnTitle(driver, windowTitle);
		WebElement campaignDD = selectCampaignPage.getCampaignDD();
		wLib.select(campaignDD, 1);
		selectCampaignPage.getSearchBar().sendKeys(campaignName);
		selectCampaignPage.getSelectBtn().click();
		driver.switchTo().window(parentId);
		createContactPage.getCreateContactSubmitBtn().click();

		// Validation
		wLib.waitforwebelemnttoload(driver, hp.getToastMsg());
		String msg1 = hp.getToastMsg().getText();
		if (msg1.contains(contact))
			System.out.println("Contact Created");
		else
			System.out.println("Contact Not Created");
		hp.getCloseToastMsg().click();
		// Logout
		hp.logout();
		driver.quit();

	}

}