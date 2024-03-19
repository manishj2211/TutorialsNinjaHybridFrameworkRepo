package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.titorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;


// Updated comment

public class LoginTest extends Base{
	
	public LoginTest() {
		super();
	}	
	
	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	
	@BeforeMethod
	public void setup() {
		
		driver= initializeBrowserAndOpenApplicationURL (prop.getProperty("browserName"));  // jya browser mdhun run krayche tyache nav yat taka.
		HomePage homePage= new HomePage (driver);
		loginPage= homePage.navigateToLoginPage();
		
		//#here, instead of below 2 line, use use above a line of code single line of code to optimise code.
		//homePage.clickOnMyAccountDropMenu();
		//loginPage= homePage.selectLoginOption();				
	}	
	
	@AfterMethod
	public void tearDown() { //it will execute after every method even TC is passed or fail.
		driver.quit();
	}	
	
	@Test (priority=1, dataProvider="validCredentialsSupplier") //priority start with -ve then 0 then +ve
    public void verifyLoginWithValidCredentials(String email, String password) {
		
		//login page object creation
		AccountPage accountPage =loginPage.login(email, password);
		
		//#here, instead of below 2 line, used use above a single line of code to optimise code.
		//loginPage.enterEmailAddress(email);
		//loginPage.enterPassword(password);
		//AccountPage accountPage = loginPage.clickOnLoginButton();
	
		//account page object creation
	    Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your Info again.");	
}
	
	@DataProvider (name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		
		Object[][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
		
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
		//login page object creation		
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("invalidPassword"));
		
		//#here, instead of below 3 line, use single line of code to optimize code and use above a line of code.
		//loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		//loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		
	    Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning Message is not displayed.");
		
		//OR YOU CAN USE BELOW 3 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.
		/*String actualWarningMessage= loginPage.retrieveEmailPasswordNotWarningMessageText();
		  String  expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		  Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed.");
  	    */
	}
	
	@Test (priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
		//login page object creation
		loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("ValidPassword"));
		
		//#here, instead of below 3 line, use single line of code to optimize code and use above a line of code.
		//loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		//loginPage.enterPassword(prop.getProperty("ValidPassword"));
		//loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning Message is not displayed.");
		
		//OR YOU CAN USE BELOW 3 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same..
		//  String actualWarningMessage= loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		 // String  expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		 // Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed.");	 
			
	}
	
	@Test (priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		//login page object creation
		loginPage.login(prop.getProperty("ValidEmail"),dataprop.getProperty("invalidPassword"));
		
		//#here, instead of below 3 line, use single line of code to optimize code and use above a line of code.
		//loginPage.enterEmailAddress(prop.getProperty("ValidEmail"));
		//loginPage.enterPassword(dataprop.getProperty("invalidPassword"));
		//loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning Message is not displayed.");
		
	    //OR, YOU CAN USE BELOW 3 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.
		/*String actualWarningMessage= loginPage.retrieveEmailPasswordNotWarningMessageText();
		String  expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed.");
		*/
	}
	
	@Test (priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		
		//login page object creation
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataprop.getProperty("emailPasswordNoMatchWarning")),"Expected Warning Message is not displayed.");
	   
		//OR, YOU CAN USE BELOW 3 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.
		/*String actualWarningMessage= loginPage.retrieveEmailPasswordNotWarningMessageText();
		String  expectedWarningMessage= dataprop.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning Message is not displayed.");
        */		
	}
		
}
