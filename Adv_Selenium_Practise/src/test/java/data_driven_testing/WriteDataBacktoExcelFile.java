package data_driven_testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataBacktoExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// to write the data in excel
		
		FileInputStream fis= new FileInputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet("Contact");
		Row r = sh.getRow(1);
		Cell c = r.createCell(4);
		c.setCellType(CellType.STRING); //soon this one will deprecated setcelltype
		c.setCellValue("Arjun"); //4th cell will be updated as Arjun
		
		FileOutputStream fos= new FileOutputStream("D:\\QSpider\\Advance Selenium\\Excel File\\CRM_TestScriptData.xlsx");
		wb.write(fos);
		wb.close();

	}

}
