package testng;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class configAnnotationsdemo {
  
	@Test
	  public void test1() {
		  System.out.println("test");
	  }
	
  @BeforeMethod
  public void beforeMethod() 
  {
	  Reporter.log("Before Method-->Login", true);
  }

  @AfterMethod
  public void afterMethod() 
  {
	  Reporter.log("After Method-->Logout", true);
  }

  @BeforeClass
  public void beforeClass() 
  {
	  Reporter.log("Before Class-->Launch browser", true);
  }

  @AfterClass
  public void afterClass() 
  {
	  Reporter.log("After Class-->Close the browser", true);
  }

  @BeforeTest
  public void beforeTest() 
  {
	  Reporter.log("Before Test-->Pre Condition", true);
  }

  @AfterTest
  public void afterTest() 
  {
	  Reporter.log("After Test-->Post Condition", true);
  }

  @BeforeSuite
  public void beforeSuite() 
  {
	  Reporter.log("Before Suite-->Establish the database connection", true);
  }

  @AfterSuite
  public void afterSuite() 
  {
	  Reporter.log("After Suite-->Close the database connection", true);
  }

}
