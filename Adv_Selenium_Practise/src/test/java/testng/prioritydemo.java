package testng;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class prioritydemo {

	//***********annotation
	// if we want to run all, we can click below or usual method. Or particular test we can click on run which is present in each test

	// priority--> used to run based on the given priority. Least the number is first priority. By default priority is 0
	public class Demo {

		@Test
		public void Pooja() // default priority is 0, hence after negative value(if any) this will run
		{
			Reporter.log("Pooja", true);
		}

		@Test(priority = 10)
		public void Arjun() {
			Reporter.log("Arjun", true);
		}

		@Test(priority = 2)
		public void arjun() {
			Reporter.log("arjun", true);
		}

		@Test(priority = -10) // this is negative value(least number less than 0), hence it will run first(if not any least negative num than this)
		public void pooja() {
			Reporter.log("pooja", true);
		}

	}

}
