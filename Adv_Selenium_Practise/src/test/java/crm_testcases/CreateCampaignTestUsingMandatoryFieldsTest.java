package crm_testcases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampaignTestUsingMandatoryFieldsTest {
//since this is TC, provided the TC name as above with Test at last
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://49.249.28.218:8098/");
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
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
		System.out.println("Modify");
		

	}

}
