package com.tutorialsninja.com.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.base.Base;
import com.tutorialsninja.pages.HomePage;
import com.tutorialsninja.pages.SearchPage;

public class SearchTest extends Base {
	HomePage homePage;
	SearchPage searchPage;
	public SearchTest() {
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
		homePage = new HomePage(driver);
	}
	
	@Test(priority = 1)
	public void verifySearchingWithValidProduct() {
	
		
		 searchPage =homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		
		Assert.assertTrue(searchPage.retrieveSearchProductDisplay(), "Valid HP Product isnot displayed");
	
	}
	
	@Test(priority = 2)
	public void verifySearchingWithInvalidProduct() {
		
	
		searchPage =homePage.searchForAProduct(dataProp.getProperty("inValidProduct"));
		
//dataProp.getProperty("searchWarning")
	Assert.assertEquals(searchPage.retrieveInvalidSearchProductWarning(), "Invalid product Warning message not displayed");
	
	}
	@Test(priority = 3,dependsOnMethods = {"verifySearchingWithValidProduct","verifySearchingWithInvalidProduct"})
	public void verifySearchingWithNoProduct() {
		
		
	searchPage =	homePage.clickOnSearch();
		
	Assert.assertEquals(searchPage.retrieveInvalidSearchProductWarning(),dataProp.getProperty("searchWarning"), "Invalid product Warning message not displayed");
	
	}
	
	
}
