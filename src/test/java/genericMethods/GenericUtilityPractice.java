package genericMethods;

import java.io.IOException;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException 
	{
		//test script
		
		
		PropertyFileUtility putil = new PropertyFileUtility() ;
		String browservalue = putil.ReadDatafromPropertyFile("browser");
		System.out.println(browservalue);
		String urlvalue = putil.ReadDatafromPropertyFile("url");
		System.out.println(urlvalue);
		String unvalue = putil.ReadDatafromPropertyFile("username");
		System.out.println(unvalue);
		String pwdvalue = putil.ReadDatafromPropertyFile("password");
		System.out.println(pwdvalue);
		
		JavaUtility jUtil = new JavaUtility();
		String date = jUtil.GetSystemDateFormat();
		System.out.println(date);
		
		int ran = jUtil.getRandomNumber();
		System.out.println(ran);
		
		ExcelFileUtility eUtil = new ExcelFileUtility();
		String Orgname = eUtil.ReadFromExcelFile("Organisations", 1, 2);
		String OrgWithRand = Orgname+ran;
		System.out.println(OrgWithRand);
		
	}
	
}

