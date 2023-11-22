package practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericUtilities.BaseClass;
import objectRepository.ContactsInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateNewContactPage;
import objectRepository.HomePage;

public class AssertPractice 
{
@Test
	public void AssertPractice() 
	{
		System.out.println("step 1");
		System.out.println("Step 2");
		Assert.assertEquals(1, 1);
		{
			System.out.println("Step 3");
		}

	}

@Test
public void SoftAssertPractice()
{
	SoftAssert sa = new SoftAssert();
	System.out.println("step 1");
	System.out.println("Step 2");
	sa.assertEquals("A", "A");
	{
		System.out.println("Step 3");
	}
	
	sa.assertAll();
	
	sa.assertEquals("A", "B");
	{
		System.out.println("Step 3");
	}
}

}
