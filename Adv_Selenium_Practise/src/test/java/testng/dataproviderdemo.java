package testng;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


//data provider--> to execute the test case multiple times with different set of data
//Object[][] refvar=new Object[row/ no. times to execute][column/ no. of arguments to pass]
//below, 1 TC (@test method) but it got ran 4 times with diff data provided

public class dataproviderdemo {
	
	@Test(dataProvider = "logindetails" )
	public void login(String Username, String Password)
	{
		Reporter.log(Username+"-->"+Password, true);
	}

	@DataProvider
	public Object[][] logindetails()
	{
		Object[][] objArr=new Object[4][2];
		
		objArr[0][0]="Pooja";
		objArr[0][1]="p@123";
		objArr[1][0]="Arjun";
		objArr[1][1]="a@123";
		objArr[2][0]="Juuu";
		objArr[2][1]="j@123";
		objArr[3][0]="Meenu";
		objArr[3][1]="m@123";
		
		return objArr;
		
	}
}
