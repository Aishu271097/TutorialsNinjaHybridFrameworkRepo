package com.tutorialsninja.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement agreeField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueField;
	
	@FindBy(xpath ="//input[@name='newsletter'][1]")
	private WebElement newsLetterField;
	
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	
	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	
	
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telePhone) {
		telephoneField.sendKeys(telePhone);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}
	
	
	
	public void clickOnNewsLetter() {
		newsLetterField.click();
	}
	
	public void clickOnAgree() {
		agreeField.click();
		}
	
	public void clickOnContinue() {
		continueField.click();
		}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telePhoneText,String passwordText,String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telePhoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		agreeField.click();
		continueField.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telePhoneText,String passwordText,String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telePhoneText);
		passwordField.sendKeys(passwordText);
		confirmPasswordField.sendKeys(confirmPasswordText);
		newsLetterField.click();
		agreeField.click();
		continueField.click();
		return new AccountSuccessPage(driver);
		
	}
	public String retrieveDuplicateEmailWarning() {
		String warning =duplicateEmailWarning.getText();
		return warning;
	}
	
	public String retrievePrivacyPolicyWarning() {
		String warning =privacyPolicyWarning.getText();
		return warning;
	}
	
	public String retrieveFirstNameWarning() {
		String privacywarning =firstNameWarning.getText();
		return privacywarning;
	}
	
	public String retrievelastNameWarning() {
		String lastNamewarning =lastNameWarning.getText();
		return lastNamewarning;
	}
	
	public String retrieveEmailWarning() {
		String emailWarningMessage =emailWarning.getText();
		return emailWarningMessage;
	}
	
	public String retrieveTelephoneWarning() {
		String telephoneWarningMessage =telephoneWarning.getText();
		return telephoneWarningMessage;
	}
	
	public String retrievePasswordWarning() {
		String passwordWarningmessage =passwordWarning.getText();
		return passwordWarningmessage;
	}
	
	

	
	
	
	
	
	
	
	
	WebDriver driver;
	public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
}
