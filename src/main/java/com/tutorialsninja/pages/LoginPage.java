package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement clickButtonField;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordMatchingWarning;
	
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	public AccountPage clickOnLoginButton() {
		clickButtonField.click();
		return new AccountPage(driver);
	}
	
	public AccountPage enterEmailPasswordLogin(String emailText,String passwordText) {
		emailField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		clickButtonField.click();
		return new AccountPage(driver);
	}
	
	public String actualemailPasswordMatchingWarning() {
		String warningText =emailPasswordMatchingWarning.getText();
		return warningText;
	}

	
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
