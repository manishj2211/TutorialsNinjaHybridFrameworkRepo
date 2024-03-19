package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.titorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
    
	public WebDriver driver;
	SearchPage searchPage;
	LoginPage loginPage;
	HomePage homePage;
	
	public SearchTest() {
		super();
	}
	
	@BeforeMethod
    public void setup() {
		driver= initializeBrowserAndOpenApplicationURL (prop.getProperty("browserName"));// jya browser mdhun run krayche tyache nav yat taka.
		homePage= new HomePage (driver);
		loginPage= homePage.navigateToLoginPage();
		
		//#here, instead of below 2 line, use use above a line of code single line of code to optimise code.
		//homePage.clickOnMyAccountDropMenu();
		//loginPage=homePage.selectLoginOption();
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	@Test (priority=1)
	public void searchWithValidProduct() {
		
		searchPage= homePage.searchForAProduct(dataprop.getProperty("validProduct"));
		
		//#here, instead of below 2 line, used use above a single line of code to optimise code.
		//homePage.enterProductIntoSearchBoxField(dataprop.getProperty("validProduct"));
		//searchPage=homePage.clickOnSearchButton();
			
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid PrdoctHP is not displayed.");
	}
	
	@Test (priority=2)
	public void verifySearchWithInvalidProduct() {
		
		searchPage= homePage.searchForAProduct(dataprop.getProperty("invalidProduct"));
		
		//#here, instead of below 2 line, used use above a single line of code to optimise code.
		//homePage.enterProductIntoSearchBoxField(dataprop.getProperty("invalidProduct"));
		//searchPage=homePage.clickOnSearchButton();
		
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataprop.getProperty("noProductTextInSearchResults"),"No Product message in search result is not displayed.");
		
		//OR YOU CAN USE BELOW 2 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.			
		/*String actualSearchMessage= searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,dataprop.getProperty("noProductTextInSearchResults"),"No Product message in search result is not displayed.");
	*/ 
		Assert.fail(); //this syntax is used only for forcefully fail the test to check listener on failed method.
	} 

	@Test (priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct"}) // here dependsOnMethods is used to forcefi=ully skip this method to check listener for skip method.
	public void verifySearchWithoutAnyProduct() {
		
		//homePage.enterProductIntoSearchBoxField(dataprop.getProperty("invalidProduct"));
		searchPage=homePage.clickOnSearchButton();
		
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataprop.getProperty("noProductTextInSearchResults"),"No Product message in search result is not displayed.");
			
		//OR YOU CAN USE BELOW 2 LINE CODE INSTEAD OF ABLVE CODE 1 LINE CODE Both works same.	
		/*String actualSearchMessage= searchPage.retrieveNoProductMessageText();
		  Assert.assertEquals(actualSearchMessage,dataprop.getProperty("noProductTextInSearchResults"),"No Product message in search result is not displayed.");
		*/
}	
}