package com.tutorialsninja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	public static final  int Implicit_WAIT_time=10;
	public static final int PAGELOAD_WAIT_TIME=10;
	
	public static String generateEmailTimeStamp() {
	Date date = new Date();
String timeStamp =date.toString().replaceAll(" ", "_").replaceAll(":", "_");
return "aishu"+timeStamp+"@gmail.com";
	
	}
	
	
	public static Object[][] getDataFromExcelFile(String sheetName)  {
		
		File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\testdata\\tutorialsninjaTestdata.xlsx");
		
		XSSFWorkbook workbook=null;
		try{FileInputStream fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
        XSSFSheet sheet = workbook.getSheet(sheetName);
	int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				
				CellType cellType = cell.getCellType();
				switch(cellType) {
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				case NUMERIC:
					data[i][j]=cell.getNumericCellValue();
					break;
				}
				
			}
			
		}
		
		return data;
	}
	
	public static String screenCapture(WebDriver driver,String testName) {
		
	File srcFile =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	String destFile = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";	
	try {
		FileUtils.copyFile(srcFile, new File(destFile));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return destFile;
	}
	
	
}
