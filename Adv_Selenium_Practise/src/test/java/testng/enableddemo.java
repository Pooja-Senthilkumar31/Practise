package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

//***********annotation
//enabled--> used to skip the test method in run

public class enableddemo {
	
	@Test (invocationCount = 5, threadPoolSize = 2, enabled = false)  
	public void Pooja() 
	{
		Reporter.log("Pooja", true);
		WebDriver driver=new ChromeDriver();
	}

	@Test(priority = 10)
	public void Arjun() {
		Reporter.log("Arjun", true);
	}

	@Test(priority = 2, invocationCount = 3, threadPoolSize = 2, enabled = false) 
	public void arjun() {
		Reporter.log("arjun", true);
		WebDriver driver=new ChromeDriver();
	}

	@Test(priority = -10) 
	public void pooja() {
		Reporter.log("pooja", true);
	}


}
