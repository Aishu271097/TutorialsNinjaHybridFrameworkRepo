package com.tutorialsninja.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
		
		 prop = new Properties();
	File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\config\\config.properties");
	try {
	FileInputStream fis1= new FileInputStream(propFile);
	prop.load(fis1);
	}catch(Throwable e){
		e.printStackTrace();
	}
	
	dataProp = new Properties();
	File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\testdata\\testdata.properties");
	try {
	FileInputStream fis2= new FileInputStream(dataPropFile);
	dataProp.load(fis2);
	}catch(Throwable e){
		e.printStackTrace();
	}
	}
	
	public  WebDriver intializeBrowserAndOpeningApplicationUrl(String browser) {
		

		
		if(browser.equalsIgnoreCase("chrome")) {
			
			driver =new ChromeDriver();
		}if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			
		}if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_WAIT_time));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT_TIME));
			driver.get(prop.getProperty("url"));
			return driver;
	}
	
}
