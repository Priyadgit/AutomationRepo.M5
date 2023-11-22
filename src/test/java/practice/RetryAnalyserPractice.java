package practice;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractice 
{
		@Test (retryAnalyzer = genericUtilities.RetryAnalysisImplementation.class)
		public void sample()
		{
			Assert.fail();
			System.out.println("Hi");
		}
		@Test
		public void sample1()
		{
			System.out.println("Hello");
		}

}
