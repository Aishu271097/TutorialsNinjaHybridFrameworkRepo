package com.tutorials.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.utils.ExtentReporter;
import com.tutorialsninja.utils.Utilities;

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		 extentReport= ExtentReporter.extentReportGenerator();
		 
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		 extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName()+"execution started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	//String testName =	result.getName();
extentTest.log(Status.PASS, result.getName()+"Test Execution Passed");
	
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		
		
		try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destFilePath = Utilities.screenCapture(driver, result.getName());
		extentTest.addScreenCaptureFromPath(destFilePath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName()+"Test Got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"Test Got Skipped");
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		File extentFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport.html");
		
		try {
			Desktop.getDesktop().browse(extentFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
