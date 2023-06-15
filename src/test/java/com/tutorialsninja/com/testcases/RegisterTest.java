package com.tutorialsninja.com.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.pages.AccountSuccessPage;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.LoginPage;
import com.tutorialsninja.pages.RegisterPage;
import com.tutorialsninja.utils.Utilities;

public class RegisterTest extends Base {

	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}
	
 public WebDriver driver;
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	@BeforeMethod
	public void setUp() {
		driver = intializeBrowserAndOpeningApplicationUrl(prop.getProperty("browserName"));
		
		HomePage homePage = new HomePage(driver);
		 registerPage =homePage.register();
		
		
	}
	
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		accountSuccessPage =registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailTimeStamp(), dataProp.getProperty("telePhone"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		Assert.assertTrue(accountSuccessPage.retrieveAccountSuccessMessage().contains(dataProp.getProperty("accountSuccessMessage")), "Your Account Has Been Created! is not displayed");
		
		
	}
	
	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithAllFields() {
		
		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailTimeStamp(), dataProp.getProperty("telePhone"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		String actualMessage= accountSuccessPage.retrieveAccountSuccessMessage();
		
		Assert.assertTrue(actualMessage.contains(dataProp.getProperty("accountSuccessMessage")), "Your Account Has Been Created! is not displayed");
	
	}
	
	@Test(priority = 3)
	public void verifyRegisteringWithExistingEmail() {

		registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("telePhone"), prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		Assert.assertTrue(registerPage.retrieveDuplicateEmailWarning().contains(dataProp.getProperty("duplicateEmailWarning")), "warning message is not displayed");
	
	}
	
	
	@Test(priority = 4)
	public void verifyRegisteringWithoutProvidingDetails() {
		
		registerPage.clickOnContinue();
		

		
		Assert.assertEquals(registerPage.retrievePrivacyPolicyWarning(), dataProp.getProperty("privacyPolicyWarning"),"privacy policy warning not displayed");
		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"), "FirstName warning not displayed");
		
		Assert.assertEquals(registerPage.retrievelastNameWarning(), dataProp.getProperty("lastNameWarning"), "Lastname warning not displayed");
		
		Assert.assertEquals(registerPage.retrieveEmailWarning(), dataProp.getProperty("emailWarning"), "email warning not displayed");
		
		Assert.assertEquals(registerPage.retrieveTelephoneWarning(), dataProp.getProperty("telePhoneWarning"), "telephone warning not displayed");
		
		Assert.assertEquals(registerPage.retrievePasswordWarning(), dataProp.getProperty("passwordWarning"), "password warning not displayed");
	
		
	}
	
	
	
	
	
	
	
	
	
}
