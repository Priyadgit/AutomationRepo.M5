package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to iTestListener Interface of TestNG
 * 
 * @param args
 * 
 */
public class ListernersImplementationClass implements ITestListener
{
	ExtentReports report ;
	ExtentTest test ;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Execution Start------");
		test=report.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Pass------");
		test.log(Status.PASS, methodName+"-----Test Pass------");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Fail------");
		System.out.println(result.getThrowable());
		
		test.log(Status.FAIL, methodName+"-----Test Fail------");
		test.log(Status.INFO, result.getThrowable());
		
		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();
		//String ScreenshotName = methodName+jUtil.GetSystemDateFormat();
		String ScreenshotName = methodName+jUtil.GetSystemDateFormat();
		try {
			String path=wUtil.captureScreenshot(BaseClass.sdriver, methodName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"-----Test Skip------");
		System.out.println(result.getThrowable());
		test.log(Status.SKIP, methodName+"-----Test Skip------");
		test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Suite Execution Started");
		
		JavaUtility jUtil = new JavaUtility();
		
		//Extent Report configuration
		ExtentSparkReporter htmlreport = new ExtentSparkReporter(".\\ExtentReports\\"+jUtil.GetSystemDateFormat()+".html");
		htmlreport.config().setDocumentTitle("Execution Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("QCO-SOEAJD-M5");
		
		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base Browser", "Edge");
		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base env", "Testing");
		report.setSystemInfo("Base URL", "https://localhost:8888");
		report.setSystemInfo("Reporter name", "Test admin");
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Suite Execution Finished");
		report.flush();
		
	}

}
