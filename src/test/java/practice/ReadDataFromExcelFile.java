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

public class ReadDataFromExcelFile {

	private static Object val2;

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	
	{
		//Step 1 : Open the document in java readable format
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		
		//step 2 : Create a workbook
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3 : Navigate to required sheet
		Sheet sh = wb.getSheet("Organisations");
		
		//Step 4 : Navigate to required row
		Row rw = sh.getRow(1);
		
		//Step 5 : Navigate to required Cell
		Cell cel = rw.getCell(2);
		
		//Step 6 : Capture the value in the cell
		String value = cel.getStringCellValue();
		System.out.println(value);
		

	}

}
