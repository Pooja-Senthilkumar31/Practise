package assertions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertions {
	
//	Definition: Even if a soft assertion fails, test continues to execute. 
//  			You must call assertAll() at the end to collect all assertion results.

//	Use Case: Useful when you want to validate multiple things in a single test.
	
	//non static methods, must create the obj creation. assertAll() at the end should be must.
	//sysntax: SoftAssert softassert= new SoftAssert; softassert.equals(expected results, expected results); softassert.assertAll();
	//here the expected failed yet also the nxt lines executed, sysout statements are printed and pgm got executed.
	
	//types: 1. assertEquals(), 2. assertNotEquals(), 3. assertTrue(), 4. assertFalse(), 5. assertNull(), 6. assertNotNull(), 7. assertAll()
	
	@Test
	public void demo()
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		String actualtitle = driver.getTitle();
		String expectedtitle = "Facebook";
		
		SoftAssert softAssert=new SoftAssert();
		System.out.println(actualtitle);
		softAssert.assertEquals(actualtitle, expectedtitle);
		System.out.println("Next line executed");
		driver.quit();
		softAssert.assertAll(); //it collects all the failures and throws an exception if any assertion failed. This acts like a hard stop at that point. So anything after assertAll() won't execute
		
		
	}

}
