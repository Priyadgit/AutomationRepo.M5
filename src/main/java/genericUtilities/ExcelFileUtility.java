package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 

{
	public String ReadFromExcelFile(String sheetname , int rowno , int cellno) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		 Workbook wb = WorkbookFactory.create(fis);
		 
		 String value = wb.getSheet(sheetname).getRow(rowno).getCell(cellno).getStringCellValue();
		 
		 return value;
			
	}
	
	
	//This method will read multiple data from excel and provides data to data provider
	public Object[][] ReadMultipleDataFromExcelFile(String sheetname) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Testdata.xlsx");
		 Workbook wb = WorkbookFactory.create(fis);
		 
		 Sheet sh = wb.getSheet(sheetname);
		 int lastrow = sh.getLastRowNum();
		 int lastcell= sh.getRow(0).getLastCellNum();
		 
		 Object[][] data=new Object[lastrow][lastcell];
		 
		 for(int i =0 ;i<lastrow ;i++)
		 {
			 for(int j=0;j<lastcell;j++)
			 {
				 data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
			 }
		 }
			return data;
	}
}
