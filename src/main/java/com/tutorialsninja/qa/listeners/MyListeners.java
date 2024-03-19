package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentTest;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	
	//GLOBAL VARIBALE
	ExtentReports extentReport; 
	com.aventstack.extentreports.ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generateExtentReporter();
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing.");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {			
		extentTest.log(Status.PASS,result.getName()+" got successfully executed.");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
			
		 // to get driver
		WebDriver driver= null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath= Utilities.captureScreenshot(driver,result.getName());
		
		//below code for screenshot or also above code is for screenshot in which method called here and method created under utilities class.
		/*File srcScreenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath =System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		try {
			FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+" got failed.");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,result.getName()+" got skipped.");	
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport =System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport= new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
}
