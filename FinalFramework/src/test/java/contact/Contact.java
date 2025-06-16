package contact;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import FileUtility.ExcelUtility;
import baseclass.BaseClass;
import javautility.JavaUtility;
import objectrepository.CampaignPage;
import objectrepository.ContactPage;
import objectrepository.CreateContactPage;
import objectrepository.HomePage;
import objectrepository.SelectCampaignPage;
import webdriverutility.WebDriverUtility;

public class Contact extends BaseClass {

	public class CreateContact extends BaseClass {
		@Test (groups= {"Smoke","Regression"})
		public void createcontact() throws EncryptedDocumentException, IOException {
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

			CampaignPage campaignPage = new CampaignPage(driver);
			ContactPage contactsPage = new ContactPage(driver);
			CreateContactPage createContactPage = new CreateContactPage(driver);
			SelectCampaignPage selectCampaignPage = new SelectCampaignPage(driver);
			WebDriverUtility wLib = new WebDriverUtility();
			HomePage hp = new HomePage(driver);

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

		}
	}
}
