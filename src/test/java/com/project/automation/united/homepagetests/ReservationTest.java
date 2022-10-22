package com.project.automation.united.homepagetests;


import java.util.Map;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import com.project.automation.united.base.CsvDataProvider;
import com.project.automation.united.base.TestUtils;
import com.project.automation.united.pages.HomePage;

public class ReservationTest extends TestUtils {

	@Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProvider.class)
	public void flightReservationTest(Map<String, String> testData) throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		String desiredOriginStr = testData.get("desiredOriginStr");
		String desiredOrigin = testData.get("desiredOrigin");
		String desiredDestinationStr = testData.get("desiredDestinationStr");
		String desiredDestination = testData.get("desiredDestination");
		String desiredAdultNumber = testData.get("desiredAdultNumber");
		String desiredChildrenNumber = testData.get("desiredChildrenNumber");
		String desiredInfantNumber = testData.get("desiredInfantNumber");
		// String waitForClickability= testData.get("waitForClickability");
		String desiredTotalTravellers = testData.get("desiredTotalTravellers");
		log.info(desiredTotalTravellers);
		String desiredTravellerClass = testData.get("desiredTravellerClass");
		log.info("Starting flight reservation test");

		// opening homepage
		HomePage homePage = new HomePage(driver, log);
		homePage.openPageUrl();
		homePage.selectOrigin(desiredOriginStr, desiredOrigin);
		homePage.selectDestination(desiredDestinationStr, desiredDestination);
		String actualAdultsNumber = homePage.addAdults(desiredAdultNumber);
		String actualChildrenNumber = homePage.addChildren(desiredChildrenNumber);
		String actualInfantsNumber = homePage.addInfants(desiredInfantNumber);
		String actualTravellerClass = homePage.selectTravellerClass(desiredTravellerClass);
		String actualTotalTravellers = homePage.getTotalTravellersNumber();

		softAssert.assertTrue(homePage.getOrigin().contains(desiredOrigin),
				" origin is not as expected. Desired origin: " + desiredOrigin + " Actual origin: "
						+ homePage.getOrigin());
		softAssert.assertTrue(homePage.getDestination().contains(desiredDestination),
				" origin is not as expected. Desired origin: " + desiredDestination + " Actual origin: "
						+ homePage.getDestination());
		softAssert.assertTrue(actualTotalTravellers.equals(desiredTotalTravellers),
				"Total travellers number is not matching. desired number is:  " + desiredTotalTravellers
						+ " while the actual number is: " + homePage.getTotalTravellersNumber());
		softAssert.assertTrue(actualAdultsNumber.contains(desiredAdultNumber), " adults number not matching ");
		softAssert.assertTrue(actualChildrenNumber.contains(desiredChildrenNumber), " Children number not matching ");
		softAssert.assertTrue(actualInfantsNumber.contains(desiredInfantNumber), " Infants number not matching ");
		softAssert.assertTrue(actualTotalTravellers.equals(desiredTotalTravellers), " total number not matching ");
		softAssert.assertTrue(actualTravellerClass.contains(desiredTravellerClass), " class not matching ");
		softAssert.assertAll();
	}

}
