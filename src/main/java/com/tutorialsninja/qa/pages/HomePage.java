package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//i[@class='fa fa-search']")
	private WebElement searchButton;
	
	
    public HomePage (WebDriver driver) {
	this.driver = driver;	
	PageFactory.initElements(driver,this);
}
    //Actions 
    
   
    public void clickOnMyAccountDropMenu() {
    	myAccountDropMenu.click();
    }
    
    public LoginPage selectLoginOption() {
    	loginOption.click();
    	return new LoginPage(driver);
    }
     //or
    public LoginPage navigateToLoginPage() { //above 2clickOnMyAccountDropMenu() and selectLoginOption() methods are
    	myAccountDropMenu.click();
    	loginOption.click();                 //combined here to optmisie code
    	return new LoginPage(driver);
    }   
    
    public RegisterPage selectRegisterOption() {
    	registerOption.click();
    	return new RegisterPage(driver);
}
    
    public RegisterPage navigateToRegisterPage() {
        myAccountDropMenu.click();
    	registerOption.click();
    	return new RegisterPage(driver);
    }
    
    
    public void enterProductIntoSearchBoxField(String ProductText) {
    	searchBoxField.sendKeys(ProductText);
    }
    
    public SearchPage clickOnSearchButton() {
    	searchButton.click();
    	return new SearchPage(driver);
    }  
    
    public SearchPage searchForAProduct(String ProductText) {
    	searchBoxField.sendKeys(ProductText);
    	searchButton.click();
    	return new SearchPage(driver);
    }
}