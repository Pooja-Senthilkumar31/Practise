package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

//***********annotation
//invocation--> to execute the test case multiple times with same set of data. By default it is 1 and values should be positive.
//if we give the negative value or 0, it will skip that method

public class invocationdemo {
	
	@Test (invocationCount = 2)
	public void Pooja() 
	{
		Reporter.log("Pooja", true);
	}

	@Test(priority = 10)
	public void Arjun() {
		Reporter.log("Arjun", true);
	}

	@Test(priority = 2, invocationCount = 3)
	public void arjun() {
		Reporter.log("arjun", true);
	}

	@Test(priority = -10) 
	public void pooja() {
		Reporter.log("pooja", true);
	}

}
