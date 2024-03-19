package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

WebDriver driver;
	
	@FindBy(xpath="//input[@name='firstname']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//input[@name='telephone']")
	private WebElement telephoneField;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@placeholder='Password Confirm']")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@type='checkbox']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//div[text()='Last Name must be between 1 and 32 characters!']")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//div[text()='E-Mail Address does not appear to be valid!']")
	private WebElement emailWarning;
	
	@FindBy(xpath="//div[text()='Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//div[text()='Password must be between 4 and 20 characters!']")
	private WebElement passwordWarning;
	
	
    public RegisterPage (WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
    }
    //Actions 
    
    public void enterFirstName(String firstNameText) {
    	firstNameField.sendKeys(firstNameText);
    }
    
    public void enterLastName(String lastNameText) {
    	lastNameField.sendKeys(lastNameText);
    }
    
    public void enterEmail(String emailText) {
    	emailAddressField.sendKeys(emailText);
    }
    
    public void enterTelephone(String telephoneText ) {
    	telephoneField.sendKeys(telephoneText);
    }
    
    public void enterPassword(String PasswordText) {
    	passwordField.sendKeys(PasswordText);
    }
    
    public void enterConfirmPassword(String enterConfirmPasswordText) {
    	passwordConfirmField.sendKeys(enterConfirmPasswordText);
    }
    
    public void selectYesNewsLetterOption() {
    	yesNewsLetterOption.click();
    }
    
    public void selectPrivacyPolicy() {
    	privacyPolicyField.click();
    }
    
    public AccountSuccessPage clickOnContinueButton() {
    	continueButton.click();
    	return new AccountSuccessPage(driver);
    }
    
    public String retrieveDuplicateEmailAddressWarning() {
    	String duplicateEmailWarningText =duplicateEmailAddressWarning.getText(); 
    return duplicateEmailWarningText;
}
    public String retrievePrivacyPolicyWarning() {
    	String privacyPolicyWarningText=privacyPolicyWarning.getText();
    	return privacyPolicyWarningText;
    }
    
 // here, above methods are used instead of under this methods for optimise code.
    public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String PasswordText,String enterConfirmPasswordText) {
    	firstNameField.sendKeys(firstNameText);
    	lastNameField.sendKeys(lastNameText);
    	emailAddressField.sendKeys(emailText);
    	telephoneField.sendKeys(telephoneText);
    	passwordField.sendKeys(PasswordText);
    	passwordConfirmField.sendKeys(enterConfirmPasswordText);
    	privacyPolicyField.click();
    	continueButton.click();
    	return new AccountSuccessPage(driver);
    }
      
 // here, above methods are used instead of under this methods for optimise code.
    public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String PasswordText,String enterConfirmPasswordText) {
    	firstNameField.sendKeys(firstNameText);
    	lastNameField.sendKeys(lastNameText);
    	emailAddressField.sendKeys(emailText);
    	telephoneField.sendKeys(telephoneText);
    	passwordField.sendKeys(PasswordText);
    	passwordConfirmField.sendKeys(enterConfirmPasswordText);
    	yesNewsLetterOption.click();
    	privacyPolicyField.click();
    	continueButton.click();
    	return new AccountSuccessPage(driver);
    }
   
    //this method is used on register method for optimize code
    public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastNameWarning, String expectedEmailWarning, String retriveTelephoneWarning, String expectedPasswordWarning) {
		boolean privacyPolicyWarningStatus= privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
    	boolean firstNameWarningStatus= firstNameWarning.getText().equals(expectedFirstNameWarning);
    	boolean lastNameWarningStatus= lastNameWarning.getText().equals(expectedLastNameWarning);
		boolean emailWarningStatus= emailWarning.getText().equals(expectedEmailWarning);
		boolean telephoneWarningStatus= telephoneWarning.getText().equals(retriveTelephoneWarning);
		boolean passwordWarningStatus= passwordWarning.getText().equals(expectedPasswordWarning);
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
    	
    }
    
    
    
    public String retriveFirstNameWarning() {
    	String firstNameWarningText=firstNameWarning.getText();
    	return firstNameWarningText;
    }
    
    public String retriveLastNameWarning() {
    	String lastNameWarningText=lastNameWarning.getText();
    	return lastNameWarningText;
    }
    
    public String retriveEmailWarning() {
    	String emailWarningText=emailWarning.getText();
    	return emailWarningText;
    }
    
    public String retriveTelephoneWarning() {
    	String telephoneWarningText=telephoneWarning.getText();
    	return telephoneWarningText;
    }
    public String retrivePasswordWarning() {
    	String passwordWarningText=passwordWarning.getText();
    	return passwordWarningText;
    }
}