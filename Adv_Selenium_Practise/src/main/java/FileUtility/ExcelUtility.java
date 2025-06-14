package FileUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	//read data from excel
	public String readDataFromExcelFile (String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx"); //stored the file in eclipse under configAppData folder. hence (./) referes to current directory, folder name and then file name
		Workbook wb=WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		wb.close();
		return data;
		
	}
	
	public int getrowcount (String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis1=new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();		
		return rowcount;
		
	}
	
	

}
