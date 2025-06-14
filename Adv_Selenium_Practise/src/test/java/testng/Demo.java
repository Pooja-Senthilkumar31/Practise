package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

//if we want to run all, we can click below or usual method. Or particular test we can click on run which is present in each test

//it will execute and print in the alphabetical order of method name (ASCII value)

public class Demo {

	@Test
	public void Pooja() {
		Reporter.log("Pooja", true);
	}

	@Test
	public void Arjun() {
		Reporter.log("Arjun", true);
	}

	@Test
	public void arjun() {
		Reporter.log("arjun", true);
	}

	@Test
	public void pooja() {
		Reporter.log("pooja", true);
	}

}
