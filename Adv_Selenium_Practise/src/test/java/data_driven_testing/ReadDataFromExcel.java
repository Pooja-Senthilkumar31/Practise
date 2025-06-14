package data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		
		//Open the workbook in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//get the control of the sheet
		Sheet sh = wb.getSheet("Campaign");
		
		//get the control of the row (row and cell values starts from 0)
		Row r = sh.getRow(1);
		
		//get the control of the cell
		Cell c = r.getCell(2);
		String campaignname = c.getStringCellValue();
		System.out.println(campaignname);
		
		//to read the numeric data we can use getnumericvalue, but it's return type is double.
		//so we can't get the exact value, it will return as 3.0 like that
		//to make it as int, we need to do type casting
		//instead of that we can change the numeric value to string by adding single quote in excel before the val
		
		//this is another way, we can directly get the cell value and value in it
		String targetsize = r.getCell(3).getStringCellValue();
		System.out.println(targetsize);
		
		
		

	}

}
