package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	
	
	@FindBy(linkText = "HP LP3065")
	private WebElement searchProductdisplay;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement invalidSearchProductWarning;
	
	
	
	public boolean retrieveSearchProductDisplay() {
		boolean productDisplay =searchProductdisplay.isDisplayed();
		return productDisplay;
	}
	
	
	public String retrieveInvalidSearchProductWarning() {
		String invalidProductWarning =invalidSearchProductWarning.getText();
		return invalidProductWarning;
	}
	
	
	
	WebDriver driver;
	public SearchPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
}
