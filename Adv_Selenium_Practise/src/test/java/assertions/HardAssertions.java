package assertions;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertions {
	
//	Definition: If a hard assertion fails, test execution stops at that point.
//
//	Use Case: Use when further execution after failure doesn’t make sense.

	// Static methods, syntax: Assert.assertEquals(actual result, expected result);
	//here the expected got failed hence the sysout statement also not printed, nxt lines are not executed
	
	//types: 1. assertEquals(), 2. assertNotEquals(), 3. assertTrue(), 4. assertFalse(), 5. assertNull(), 6. assertNotNull()
	@Test
	public void demo()
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		String actualtitle = driver.getTitle();
		String expectedtitle = "FaceBook";
		Assert.assertEquals(actualtitle, expectedtitle);
		System.out.println(actualtitle);
		System.out.println("Next line executed");
		driver.quit();
	}
	

}
