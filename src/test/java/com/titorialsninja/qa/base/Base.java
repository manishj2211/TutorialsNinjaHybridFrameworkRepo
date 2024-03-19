package com.titorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public Base() { //constructor
		
	  //to load properties from properties file from congif.properties file.
		prop= new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
			FileInputStream fis= new FileInputStream(propFile);
			prop.load(fis);
			} catch (Throwable e) {
				e.printStackTrace();				
			}
		
		//to load properties from properties file from testdata.properties file.
		dataprop =new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		
		try {
			FileInputStream  dataFis= new FileInputStream(dataPropFile);
			dataprop.load(dataFis);
		} catch (Throwable e) {			
			e.printStackTrace();
		}
	
}
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			driver= new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("safari")) {
			driver= new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().to(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		return driver;
		
	}
}
