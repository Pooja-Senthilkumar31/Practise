package data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class WorkingwithPropertiesFile {

	public static void main(String[] args) throws IOException {

		//Create a Java representation object of physical file
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Properties File\\LoginwithNinza.properties");
		//Create an object of properties
		Properties prop=new Properties();
		//Load all the keys
		prop.load(fis);
		//the below is to just read the data from note pad (properties file)
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

		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("inputPassword")).sendKeys(PW);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Random random=new Random();
		int ranInt=random.nextInt(200);
		System.out.println(ranInt);
		
		driver.findElement(By.xpath("//span[contains (text(), 'Create')]")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Pooja"+ranInt);
		driver.findElement(By.name("targetSize")).sendKeys("2");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		WebElement AlertMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(AlertMsg));
		String Msg = AlertMsg.getText();
		
		System.out.println(Msg);
		
		if(Msg.contains("Pooja"+ranInt))
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
