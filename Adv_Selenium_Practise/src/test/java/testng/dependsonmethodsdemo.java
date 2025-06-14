package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

//***********annotation
//depends on methods--> used to define the dependency between the test cases
//eg., if we need to run a method after a particular method, we can use this.
//also, in a TC there are 100 methods and we need to execute the 100th method first and then all, we need to write in all method that it depends on 100th method
//below, as per testng, adv selenium will execute first then basic and then student. But as per given depends on methods, student will get excute then basic and then adv
//if we use priority, if the students method code got failed also it will start to execute other methods. but this will stop the eecution and skip other methods

public class dependsonmethodsdemo {
	
	WebDriver driver;
	
	@Test
	public void student()
	{
		Reporter.log("Student", true);
		driver = new ChromeDriver();
	}
	
	@Test(dependsOnMethods = "student")
	public void basicselenium()
	{
		Reporter.log("BasicSelenium", true);
		driver.get("https://www.instagram.com/");
	}
	
	@Test(dependsOnMethods = {"student","basicselenium"})
	public void advanceselenium()
	{
		Reporter.log("AdvanceSelenium", true);
		driver.findElement(By.name("username")).sendKeys("Pooja");
		
	}

}
