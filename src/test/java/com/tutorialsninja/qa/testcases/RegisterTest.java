package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.titorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest() {
		super();
	}	
	
	@BeforeMethod
		public void setup() {
	
		driver= initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName")); //jya browser mdhun run krayche tyache nav yat taka
		HomePage homePage= new HomePage(driver); //Homepage page object creation
		registerPage=homePage.navigateToRegisterPage();
		
		//#here, instead of below 2 line, use above single line of code to optimise code.
		//registerPage= homePage.clickOnMyAccountDropMenu();
		//registerPage= homePage.selectRegisterOption();
			
	}
	
   @AfterMethod
	public void teardown() {
		driver.quit();		
	}
	
	@Test (priority=1)
	public void verifyRegisteringAccountWithMandatoryFields() {

		//register page object creation.
		
		accountSuccessPage= registerPage.registerWithMandatoryFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("telephoneNumber"), prop.getProperty("ValidPassword"), prop.getProperty("ValidPassword"));
		
		
		//#here, instead of below 8 line, used use above a single line of code to optimise code.
		/*
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("ValidPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerPage.selectPrivacyPolicy(); 
		accountSuccessPage=registerPage.clickOnContinueButton();
		 */	
		
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessPageHeading(), dataprop.getProperty("accountSuccessfullyCreatedHeading"),"Account success is not displayed.");
		
		//OR YOU CAN USE BELOW 2 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.
		/*String actualSuccessHeading = accountSuccessPage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataprop.getProperty("accountSuccessfullyCreatedHeading"),"Account success is not displayed.");			
	*/
	}

	@Test (priority=2)
	public void verifyRegesteringAccountByProvidingAllFields() {
		
		accountSuccessPage= registerPage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataprop.getProperty("telephoneNumber"), prop.getProperty("ValidPassword"), prop.getProperty("ValidPassword"));
		
		//#here, instead of below 9 line, used use above a single line of code to optimize code.
		/*
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("ValidPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
	    */
		
		Assert.assertEquals(accountSuccessPage.retriveAccountSuccessPageHeading(),dataprop.getProperty("accountSuccessfullyCreatedHeading"),"Account success is not displayed.");	

		//OR YOU CAN USE BELOW 2 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.
		/*String actualSuccessHeading = accountSuccessPage.retriveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataprop.getProperty("accountSuccessfullyCreatedHeading"),"Account success is not displayed.");	
	    */
	}

	@Test (priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		accountSuccessPage= registerPage.registerWithAllFields(dataprop.getProperty("firstName"), dataprop.getProperty("lastName"), prop.getProperty("ValidEmail"), dataprop.getProperty("telephoneNumber"), prop.getProperty("ValidPassword"), prop.getProperty("ValidPassword"));
		
		/*
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("ValidEmail"));
		registerPage.enterTelephone(dataprop.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("ValidPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("ValidPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage=registerPage.clickOnContinueButton();
		*/
	
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataprop.getProperty("duplicateEmailWarning")),"Warning message regarding Duplicate Email is not dispalyed.");
		
		//OR YOU CAN USE BELOW 2 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.
		/*
		String actualWarning =registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataprop.getProperty("duplicateEmailWarning")),"Warning message regarding Duplicate Email is not dispalyed.");
		*/
     }
  
	@Test (priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		
		registerPage.clickOnContinueButton();	
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataprop.getProperty("privacyPolicyWarning"), dataprop.getProperty("firstNameWarning"), dataprop.getProperty("lastNameWarning"), dataprop.getProperty("emailWarning"), dataprop.getProperty("telephoneWarning"), dataprop.getProperty("passwordWarning")));
			
		//instead of below 6 line of code, we used above single code for optimize.and method for this is created in RegisterPage.
	   /* Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataprop.getProperty("privacyPolicyWarning")), "Privacy Policy Message is not displayed.");		    		
	    Assert.assertEquals(registerPage.retriveFirstNameWarning(),dataprop.getProperty("firstNameWarning"),"First Name Warning is not displayed.");	
	    Assert.assertEquals(registerPage.retriveLastNameWarning(),dataprop.getProperty("lastNameWarning"),"Last Name Warning is not displayed.");	
	    Assert.assertEquals(registerPage.retriveEmailWarning(),dataprop.getProperty("emailWarning"),"Email Warning is not displayed.");	
	    Assert.assertEquals(registerPage.retriveTelephoneWarning(),dataprop.getProperty("telephoneWarning"),"Telephone Warning is not displayed.");	
	    Assert.assertEquals(registerPage.retrivePasswordWarning(),dataprop.getProperty("passwordWarning"),"Password Warning is not displayed.");
	*/
	
	}}
