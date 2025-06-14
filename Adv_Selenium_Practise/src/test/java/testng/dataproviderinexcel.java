package testng;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataproviderinexcel {
	
	@Test(dataProvider = "LoginDetails")
	public void login(String userName, String password) {
		
		System.out.println(userName+"---->"+password);
	}
	
	
	@DataProvider
	public Object[][] LoginDetails() throws EncryptedDocumentException, IOException{
		
		FileInputStream fis = new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DataProvider");
		int rowCount = sh.getLastRowNum();
		System.out.println(rowCount);
		
		Object[][] objArr=new Object[rowCount][2];
		for(int i=0;i<rowCount;i++) {
			
			objArr[i][0]=sh.getRow(i+1).getCell(0).getStringCellValue();
			objArr[i][1]=sh.getRow(i+1).getCell(1).getStringCellValue();
			
		}
		return objArr;
	} 

}
