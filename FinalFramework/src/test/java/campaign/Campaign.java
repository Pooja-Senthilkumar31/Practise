package campaign;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import FileUtility.ExcelUtility;
import baseclass.BaseClass;
import javautility.JavaUtility;
import objectrepository.CampaignPage;
import objectrepository.HomePage;
import webdriverutility.WebDriverUtility;

//to achieve the batch execution, by using below campaign batch will execute first and broweser closed then other batch contact will run

//to achieve the group execution, name the group for each TC and create as per below suite file seperately for each group, in that suite file, 
//..manually include group, run, include tags and tyoe group inside that include. this needs to be done after first suite tag. If we need 
//.. exclude any TC which donot want to execute which has incldued to other group as well, type the group name inside the exclude tag. 
//.. since we have both smoke and regressin in base class, this wont work here.If it is not mentioned in base class, it will exclude

//to achieve parallel execution, 2 types- distributed and cross browser testing
//distributed--> distributing the test case and run to save time. in suite file creation window, give parallel as tests and thread count(based on no of TC)  and in suite file, crate requreid no. of tests and give unique test name and place required TC in each test method
//cross browser-->same as above and we need to give parameters in suite fileand some changes in base class for browser

//to create suite file, selected the class (Campaign and COntact) and right click, testng--> 
//convert to testng then change the name in first tab, then finish. XML filw will b created, and right click it run as tesng suite

@Listeners(listenerutility.ListenerImplementationClass.class) //syntax--> @listeners(pkgname.classname.class)
public class Campaign {
	
	public class CreateCampaignTest extends BaseClass {

		@Test(groups="Smoke")
		public void CreateCampaignWithMandatoryFieldsTest() throws InterruptedException, IOException {

			ExcelUtility eLib = new ExcelUtility();
			String campName = eLib.readDataFromExcelFile("Campaign", 1, 2);
			String targetSize = eLib.readDataFromExcelFile("Campaign", 1, 3);

			JavaUtility jLib = new JavaUtility();
			int randomInt = jLib.getRandomNumber();
			String campaignName = campName + randomInt;

			WebDriverUtility wLib = new WebDriverUtility();
	        HomePage hp = new HomePage(driver);
			CampaignPage campaignPage = new CampaignPage(driver);

			// Create Campaign
			campaignPage.createCampaign(campaignName, targetSize);
			campaignPage.getCreateCampaignSubmitBtn().click();

			// Validation
			wLib.waitforwebelemnttoload(driver, hp.getToastMsg());
			String msg = hp.getToastMsg().getText();
			Assert.assertEquals(msg,  "Campaign "+campaignName+" Successfully Added");
			hp.getCloseToastMsg().click();

		}
		
		@Test(groups="Regression")
		public void createCampaignWithStatusTest() throws EncryptedDocumentException, IOException {
			
			ExcelUtility eLib = new ExcelUtility();
			String campName = eLib.readDataFromExcelFile("Campaign", 4, 2);
			String targetSize = eLib.readDataFromExcelFile("Campaign", 4, 3);
			String status = eLib.readDataFromExcelFile("Campaign", 4, 4);

			JavaUtility jLib = new JavaUtility();
			int randomInt = jLib.getRandomNumber();
			String campaignName = campName + randomInt;
			WebDriverUtility wLib = new WebDriverUtility();

			CampaignPage campaignPage = new CampaignPage(driver);
	        HomePage hp = new HomePage(driver);


			// Create Campaign
			campaignPage.createCampaign(campaignName, targetSize);
			campaignPage.getCampaignStatusTF().sendKeys(status);
			campaignPage.getCreateCampaignSubmitBtn().click();

			// Validation
			wLib.waitforwebelemnttoload(driver, hp.getToastMsg());
			String msg = hp.getToastMsg().getText();
			Assert.assertEquals(msg,  "Campaign"+campaignName+"Successfully Added"); //expected-msg, actual got- campaign...sucesfully added
			hp.getCloseToastMsg().click();
		}
		
		@Test (groups="Smoke")
		public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
			ExcelUtility eLib = new ExcelUtility();
			String campName = eLib.readDataFromExcelFile("Campaign", 4, 2);
			String targetSize = eLib.readDataFromExcelFile("Campaign", 4, 3);

			JavaUtility jLib = new JavaUtility();
			int randomInt = jLib.getRandomNumber();
			String campaignName = campName + randomInt;

			WebDriverUtility wLib = new WebDriverUtility();
	        HomePage hp = new HomePage(driver);


			CampaignPage campaignPage = new CampaignPage(driver);

			// Create Campaign
			campaignPage.createCampaign(campaignName, targetSize);
			wLib.enterInput(driver, campaignPage.getExpectedCloseDateTF(), jLib.getRequiredDate(30));
			campaignPage.getCreateCampaignSubmitBtn().click();
			
			// Validation
			wLib.waitforwebelemnttoload(driver,hp.getToastMsg());
			String msg =  hp.getToastMsg().getText();
			Assert.assertEquals(msg,  "Campaign "+campaignName+" Successfully ");
			hp.getCloseToastMsg().click();
			System.out.println("Modify");
			System.out.println("Modify");
			System.out.println("Modify");
			System.out.println("Modify");

		}

	}

}
