package practice;

import static org.testng.Assert.fail;

import org.apache.hc.core5.util.Asserts;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

import dev.failsafe.internal.util.Assert;

public class TestNGPractice 
{
	@Test 
	public void CreateCustomer()
	{
		org.testng.Assert.fail();//fail
		System.out.println("Create Customer");
	}
	
	@Test (dependsOnMethods = "CreateCustomer")//skipped
	public void ModifyCustomer()
	{
		System.out.println("Modify Customer");
	}
	
	@Test 
	public void DeleteCustomer()//pass
	{
		System.out.println("Delete Customer");
	}
	
	
}
