package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataIntoExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\WriteFile.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Contacts");
		Row rw = sh.createRow(1);
		Cell cl = rw.createCell(1);
		cl.setCellValue("Hello");
		FileOutputStream fos = new FileOutputStream(".\\src\\test\\resources\\WriteFile.xlsx");
		wb.write(fos);
		fos.flush();
		System.out.println("Write Successful");
		
		//verify if write successful
		Row rw2 = sh.getRow(1);
		Cell cl2 = rw2.getCell(1);
		String val2 = cl2.getStringCellValue();
		System.out.println(val2);
		
		
	}

}
