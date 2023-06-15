package com.tutorialsninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports extentReportGenerator()  {
		
		
		ExtentReports extent = new ExtentReports();
		File extentFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setDocumentTitle("TutorialsNinja Application");
		sparkReporter.config().setReportName("TN reports");
		sparkReporter.config().setTimeStampFormat("dd//mm//yy hh:mm::ss");
		
	extent.attachReporter(sparkReporter);
	
	Properties configProp = new Properties();
	File configFile = new File(System.getProperty("user.dir")+"\\com\\tutorialsninja\\config\\config.proprties");
	try {
		FileInputStream fis = new FileInputStream(configFile);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
extent.setSystemInfo("Application URL", configProp.getProperty("url"));
extent.setSystemInfo("BrowserName", configProp.getProperty("browser"));
extent.setSystemInfo("Email", configProp.getProperty("validEmail"));
extent.setSystemInfo("Password", configProp.getProperty("valid Password"));
extent.setSystemInfo("Operating System", System.getProperty("os.name"));
extent.setSystemInfo("Java Version", System.getProperty("java.version"));
extent.setSystemInfo("UserName", System.getProperty("user.name"));
		return extent;
	}
	
}
