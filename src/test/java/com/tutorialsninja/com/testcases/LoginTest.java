package com.tutorialsninja.com.testcases;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.pages.AccountPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest extends Base {
	LoginPage loginPage;
	AccountPage accountPage;
	
	public LoginTest(){
		super();
	}
	
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver = intializeBrowserAndOpeningApplicationUrl(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		 loginPage =homePage.login();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	@Test(priority = 1,dataProvider="dataSupplier")
	public void  loginWithValidCredentials(String email,String password) {
	
		 accountPage =loginPage.enterEmailPasswordLogin(email, password);
		Assert.assertTrue(accountPage.displayAccountInformation(), "Edit your account information is not displayed");
		
	}

	@Test(priority = 2)
	public void loginWithInvalidEmailValidpassword() {
		loginPage.enterEmailPasswordLogin(Utilities.generateEmailTimeStamp(), prop.getProperty("validPassword"));
	
		Assert.assertTrue(loginPage.actualemailPasswordMatchingWarning().contains(dataProp.getProperty("noEmailPasswordMatchingWarning")));
		
	}
	
	@Test(priority = 3)
	public void loginWithValidEmailInvalidPassword() {
		loginPage.enterEmailPasswordLogin(prop.getProperty("validEmail"), dataProp.getProperty("inValidPassword"));
		Assert.assertTrue(loginPage.actualemailPasswordMatchingWarning().contains(dataProp.getProperty("noEmailPasswordMatchingWarning")));
		
	}
	@Test(priority = 4)
	public void loginWithInvalidEmailValidPassword() {
		loginPage.enterEmailPasswordLogin(Utilities.generateEmailTimeStamp(), prop.getProperty("validPassword"));

		Assert.assertTrue(loginPage.actualemailPasswordMatchingWarning().contains(dataProp.getProperty("noEmailPasswordMatchingWarning")));
		driver.quit();
	}
	
	@Test(priority = 5)
	public void loginWithoutCredentials() {
		loginPage.enterEmailPasswordLogin("", "");
		String actualMessage =loginPage.actualemailPasswordMatchingWarning();
		String expectedMessage = dataProp.getProperty("noEmailPasswordMatchingWarning");
		Assert.assertTrue(actualMessage.contains(expectedMessage));
		driver.quit();
	}
	
	
	@DataProvider(name="dataSupplier")
	public Object[][] getTestData() {
		
		Object[][] data = Utilities.getDataFromExcelFile("Login");
		return data;
	}
	
	
	
}
