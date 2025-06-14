package data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignwithExpectedCloseDate {

	public static void main(String[] args) throws IOException {
		
		//Read data from Properties file
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Properties File\\LoginwithNinza.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String browser = prop.getProperty("Browser");
		String url = prop.getProperty("URL");
		String UN = prop.getProperty("Username");
		String PW = prop.getProperty("Password");
		
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver();
		else if(browser.equalsIgnoreCase("EDGE"))
			driver=new EdgeDriver();
		else if(browser.equalsIgnoreCase("Firefox"))
			driver=new FirefoxDriver();
		else if(browser.equalsIgnoreCase("Safari"))
			driver=new SafariDriver();
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		
		//Login
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Generating random number
		Random random=new Random();
		int ranInt=random.nextInt(200);
		System.out.println(ranInt);
		
		//Getting the date after 30 days for expected close date field
		Date date=new Date(); //to get the current date 
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy"); // to set the format and MM should always be caps
		sim.format(date); //to set the current date in mentioned format
		Calendar cal = sim.getCalendar(); //getting the calender to set the date
		cal.add(Calendar.DAY_OF_MONTH, 30); //in days of month add 30 days, to get after 30 days
		String reqdate = sim.format(cal.getTime()); //gettime is used to get the above calculated date
		
		//Read data from Excel file		
		FileInputStream fis1=new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Campaign");
		Row r = sh.getRow(4);
		
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		
		String campaignname = r.getCell(2).getStringCellValue();
		driver.findElement(By.name("campaignName")).sendKeys(campaignname+ranInt);
		String targetsize = r.getCell(3).getStringCellValue();
		driver.findElement(By.name("targetSize")).sendKeys(targetsize);
//		driver.findElement(By.xpath("//input[@type='date']")).sendKeys(reqdate);
		WebElement expecteddate = driver.findElement(By.xpath("//input[@type='date']"));
		Actions action=new Actions(driver);
		action.click(expecteddate).sendKeys(reqdate).perform();
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		WebElement AlertMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(AlertMsg));
		String Msg = AlertMsg.getText();
		
		System.out.println(Msg);
		
		if(Msg.contains("Pooja_"+ranInt))
		{
			System.out.println("The creation is succesfull");
		}
		else
		{
			System.out.println("The creation is not sucessfull");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
			
			
		WebElement Hover = driver.findElement(By.className("user-icon"));
//		Actions action=new Actions(driver); //above we already created action class
		action.moveToElement(Hover).build().perform();
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		driver.quit();
		
		
		

	}

}
