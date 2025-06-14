package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

//***********annotation
//Thread Pool Size--> if we want to execute same test case multiple times with same data parallely
//invocation count should be there to describe thread pool size. default is 0 
//ex. inv=5, tps= 2--> parallely 2 times it will run first, then 2 parallel times, then remaining 1 alone

public class threadpoolsizedemo {
	
	@Test (invocationCount = 5, threadPoolSize = 2) //--> 2 opened first, then 2 and then 1 (here 2 specifies how many count in one round). 
	public void Pooja() 
	{
		Reporter.log("Pooja", true);
		WebDriver driver=new ChromeDriver();
	}

	@Test(priority = 10)
	public void Arjun() {
		Reporter.log("Arjun", true);
	}

	@Test(priority = 2, invocationCount = 3, threadPoolSize = 2) //--> first time 2 browser opened then second time 1, total 3 browser opened for 2 times
	public void arjun() {
		Reporter.log("arjun", true);
		WebDriver driver=new ChromeDriver();
	}

	@Test(priority = -10) 
	public void pooja() {
		Reporter.log("pooja", true);
	}


}
