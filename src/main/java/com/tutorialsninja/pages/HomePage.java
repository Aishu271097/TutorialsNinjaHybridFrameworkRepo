package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropDown;
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement loginButton;
	
	@FindBy(linkText = "Register")
	private WebElement registerButton;
	

	@FindBy(xpath="//input[@name='search']")
	private WebElement searchProduct;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public void enterSearchProduct(String productText) {
		searchProduct.sendKeys(productText);
		
	}
	
	public SearchPage clickOnSearch() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	
	public SearchPage searchForAProduct(String product) {
		searchProduct.sendKeys(product);
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public HomePage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnAccount() {
		myAccountDropDown.click();
	}
	
	public void clickOnLogin() {
		loginButton.click();
	}
	
	public LoginPage login() {
		myAccountDropDown.click();
		loginButton.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage register() {
		myAccountDropDown.click();
		registerButton.click();
		return new RegisterPage(driver);
	}
	
	
	public void clickOnRegister() {
		registerButton.click();
	}
}

