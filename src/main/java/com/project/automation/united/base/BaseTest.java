package com.project.automation.united.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;



public class BaseTest {
	protected WebDriver driver;
	protected Logger log;
	@BeforeMethod(alwaysRun= true)
	@Parameters({"browser"})
    public void setUp(@Optional("chrome") String browser, ITestContext ctx) {
		String testName= ctx.getCurrentXmlTest().getName();
		log= LogManager.getLogger(testName);
		BrowserDriverFactory factory = new BrowserDriverFactory(browser,log);
		driver= factory.createDriver();
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtils.PAGE_LOAD_TIME));
    }
	
	@AfterMethod(alwaysRun= true)
	public void tearDown() {
		System.out.println("Test complete. Exiting");
		driver.quit();
	}
	

}
