package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {

		@Test(dataProvider = "getData")
		public void readData(String name , String type , int qty , int price)
		{
			System.out.println(name+"--"+type+"--"+qty+"--"+price);
		}

		@DataProvider 
		public Object[][] getData()
		{
			Object[][] data =new Object[3][4];//3 different data sets with 4 details each
			
			data[0][0] = "Samsung";
			data[0][1]="A80";
			data[0][2]=12;
			data[0][3]=12000;
			
			data[1][0] = "Iphone";
			data[1][1]="S14";
			data[1][2]=15;
			data[1][3]=72000;
			
			data[2][0] = "Vivo";
			data[2][1]="V21";
			data[2][2]=16;
			data[2][3]=30000;
			
			return data;
			
		}
}


