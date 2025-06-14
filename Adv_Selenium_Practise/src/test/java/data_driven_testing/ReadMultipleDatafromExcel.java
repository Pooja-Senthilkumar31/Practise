package data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDatafromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis=new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Practise");
		
		int rowcount = sh.getLastRowNum();//to get the total row count
		System.out.println(rowcount);
		
		for(int row = 1;row<=rowcount;row++)
		{
			String ProductID = sh.getRow(row).getCell(0).getStringCellValue();
			String ProductName = sh.getRow(row).getCell(1).getStringCellValue();
			System.out.println(ProductID+"-->"+ProductName);
		}
		
		
		
		

	}

}
