package data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
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

public class WorkingwithExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Campaign");
		Row r = sh.getRow(1);
		
		String browser = r.getCell(4).getStringCellValue();		
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("Chrome"))
			driver=new ChromeDriver();
		else if(browser.equalsIgnoreCase("EDGE"))
			driver=new EdgeDriver();
		else if(browser.equalsIgnoreCase("Firefox"))
			driver=new FirefoxDriver();
		else if(browser.equalsIgnoreCase("Safari"))
			driver=new SafariDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String URL = r.getCell(5).getStringCellValue();
		driver.get(URL);
		
		String UN = r.getCell(6).getStringCellValue();
		driver.findElement(By.id("username")).sendKeys(UN);
		
		String PW = r.getCell(7).getStringCellValue();
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Random random=new Random();
		int ranInt=random.nextInt(200);
		System.out.println(ranInt);
		
		driver.findElement(By.xpath("//span[contains(text(),'Create')]")).click();
		
		String campaignname = r.getCell(2).getStringCellValue();
		driver.findElement(By.name("campaignName")).sendKeys(campaignname+ranInt);
		
		String targetsize = r.getCell(3).getStringCellValue();
		driver.findElement(By.name("targetSize")).sendKeys(targetsize);
		
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
		Actions action=new Actions(driver);
		action.moveToElement(Hover).build().perform();
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		driver.quit();
		
		
		
		
		
		
		

	}

}
