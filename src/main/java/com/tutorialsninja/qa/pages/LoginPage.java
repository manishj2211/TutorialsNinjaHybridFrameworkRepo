package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

WebDriver driver;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement emailAddressField;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")  //
	private WebElement loginButton;
	
	@FindBy(xpath=("//div[contains(@class,'alert-dismissible')]"))
	private WebElement emailPasswordNoMatchingWarning;
	
	
    public LoginPage (WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver,this);
}
    //Actions 
    
    public void enterEmailAddress(String emailText) {
    	emailAddressField.sendKeys(emailText);
    }
    
    public void enterPassword(String passwordText) {
    	passwordField.sendKeys(passwordText);
    }
    
    public AccountPage clickOnLoginButton() {
    	loginButton.click();
    	return new AccountPage(driver);
    }
     //or
    public AccountPage login(String emailText,String passwordText) {// here, above 3 methods are used instead of 
    	emailAddressField.sendKeys(emailText);              //use of 3 separate methods to optimise code
    	passwordField.sendKeys(passwordText);
    	loginButton.click();
    	return new AccountPage(driver);
    	
    }
    
    
    public String retrieveEmailPasswordNotMatchingWarningMessageText() {
    	String warningText= emailPasswordNoMatchingWarning.getText();
    	return warningText;
}
}