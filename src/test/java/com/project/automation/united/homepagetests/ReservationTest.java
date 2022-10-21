package com.project.automation.united.homepagetests;

import java.util.List;
import java.util.Map;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.project.automation.united.base.CsvDataProvider;
import com.project.automation.united.base.TestUtils;
import com.project.automation.united.pages.HomePage;
import com.project.automation.united.pages.PassengerSelectorModal;

public class ReservationTest extends TestUtils{

	@Test(priority=1, dataProvider="csvReader", dataProviderClass= CsvDataProvider.class)
	public void flightReservationTest(Map<String, String> testData) throws InterruptedException {
		SoftAssert softAssert =new SoftAssert();
	   String desiredOriginStr= testData.get("desiredOriginStr");
	   String desiredOrigin= testData.get("desiredOrigin");
	   String desiredDestinationStr= testData.get("desiredDestinationStr");
	   String desiredDestination= testData.get("desiredDestination");
	  // String waitForClickability= testData.get("waitForClickability");
	   //String totalTravellers= testData.get("totalTravellers");
	    log.info("Starting flight reservation test");
	    
	    //opening homepage
	    HomePage homePage = new HomePage(driver, log);
	    homePage.openPageUrl();
		
		homePage.selectOrigin(desiredOriginStr, desiredOrigin);
	    homePage.selectDestination(desiredDestinationStr, desiredDestination);
		//PassengerSelectorModal passengerSelectorModal = homePage.getPassengerSelectorModal();
	   // passengerSelectorModal.addPassenger(testData);
	   // System.out.println(" the actual origin is: "+homePage.getOrigin());
	   // softAssert.assertTrue(homePage.getOrigin().contains(desiredOrigin), " origin is not as expected. Desired origin: " +desiredOrigin
	    	//	+" Actual origin: " + homePage.getOrigin());
	    softAssert.assertTrue(homePage.getDestination().contains(desiredDestination), " origin is not as expected. Desired origin: " +desiredDestination
	    		+" Actual origin: " + homePage.getDestination());
	    softAssert.assertAll();
	}
	

}
