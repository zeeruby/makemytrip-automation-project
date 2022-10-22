package com.project.automation.united.pages;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.automation.united.base.TestUtils;

public class HomePage extends BasePage {
	public static String pageUrl = "https://www.makemytrip.com/?ccde=us";
	// identify parent element with ./.. expression in xpath
	// parent.findElements(By.xpath("./child::*"))
	// path=//tagname[contains(@Attribute,‘Value’)]
	private By fromCityLocator = (By.id("fromCity"));
	private By toCityLocator = (By.id("toCity"));
	private By originLocator = By.xpath("//input[@placeholder='From']");
	private By destinationLocator = (By.xpath("//input[@placeholder='To']"));
	private By suggestionsBoxLocator = (By.xpath("//p[contains(text(),'SUGGESTIONS')]"));
	private By originOptionsLocator = By.xpath("//li[contains(@id,'react-autowhatever-1-section-0-item')]");
	private By travellersAndClassLocator = By.xpath("//span[@class='lbl_input latoBold appendBottom5']");
	private By selectAdultsLocator = By.xpath("//li[contains(@data-cy,'adult')]");
	private By selectChildrenLocator = By.xpath("//li[contains(@data-cy,'children')]");
	private By selectInfantsLocator = By.xpath("//li[contains(@data-cy,'infant')]");
	private By travellerClassLocator = By.xpath("//ul[@class='guestCounter classSelect font12 darkText']/li");
	private By applyButton = By.xpath("//button[@data-cy =travellerApplyBtn']");
	private By totalTravellerNumberLocator = By.xpath("//label[@for='travellers']/descendant::p[1]");

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	//open url
	public void openPageUrl() {
		openPage(pageUrl);
	}

	//finding actual origin
	public String getOrigin() {
		return find(fromCityLocator).getAttribute("value").toLowerCase();

	}

	////finding actual destination
	public String getDestination() {
		return find(toCityLocator).getAttribute("value").toLowerCase();

	}

	//open the selector for origin selection
	private void getOriginSelector(String originStr) throws InterruptedException {
		find(fromCityLocator).click();
		WebElement elem = find(originLocator);
		elem.clear();
		elem.sendKeys(originStr);
		Thread.sleep(3000);
	}
	
    //Select desired origin
	public void selectOrigin(String originStr, String origin) throws InterruptedException {
		getOriginSelector(originStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> originOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originOptionsLocator));
		for (WebElement option : originOptions) {
			String optionText = option.getText().toLowerCase();
			if (optionText.contains(origin)) {
				option.click();
				break;
			} 
		}
	}

	//open destination options selector
	private void getDestinationSelector(String destinationStr) throws InterruptedException {
		WebElement elem = find(destinationLocator);
		elem.click();
		elem.clear();
		elem.sendKeys(destinationStr);
		Thread.sleep(3000);
	}
    
	//Select destination
	public void selectDestination(String destinationStr, String destination) throws InterruptedException {
		getDestinationSelector(destinationStr);
		waitForVisibilityOf(suggestionsBoxLocator, Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> destinationOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originOptionsLocator));
		for (WebElement option : destinationOptions) {
			if (option.getText().toLowerCase().contains(destination)) {
				option.click();
				break;
			}
		}

	}
    
	//Add desired number of adults
	public String addAdults(String desiredAdultNumber) {
		String currentAdultsNumber ="0";
		waitForVisibilityOf(travellersAndClassLocator, Duration.ofSeconds(20));
		find(travellersAndClassLocator).click();
		List<WebElement> adultsCounter = findAll(selectAdultsLocator);
		for (WebElement adult : adultsCounter) {
			if (adult.getAttribute("data-cy").contains(desiredAdultNumber)) {
				adult.click();
				currentAdultsNumber= adult.getAttribute("data-cy");
				
			}
		}
		return currentAdultsNumber;
	}

	//Add desired number of children	
	public String addChildren(String desiredChildrenNumber) {
		String currentChildrenNumber ="0";
		List<WebElement> childrensCounter = findAll(selectChildrenLocator);
		for (WebElement child : childrensCounter) {
			if (child.getAttribute("data-cy").contains(desiredChildrenNumber)) {
				child.click();
				currentChildrenNumber= child.getAttribute("data-cy");
				break;
			}
		}
		return currentChildrenNumber;
	}
    
	////Add desired number of infants
	public String addInfants(String desiredInfantNumber) {
		String currentInfantNumber ="0";
		List<WebElement> infantsCounter = findAll(selectInfantsLocator);
		for (WebElement infant : infantsCounter) {
			if (infant.getAttribute("data-cy").contains(desiredInfantNumber)) {
				infant.click();
				currentInfantNumber= infant.getAttribute("data-cy");
				break;
			}
		}
		return currentInfantNumber;
	}
    
	//Select travel class
	public String selectTravellerClass(String desiredTravellerClass) {
		String actualTravellerClass= "";
    	List<WebElement> travellerClassList = findAll(travellerClassLocator);
    	for(WebElement travellerClass:travellerClassList) {
    		if(travellerClass.getText().toLowerCase().equals(desiredTravellerClass)){
    			travellerClass.click();
    			actualTravellerClass= travellerClass.getText().toLowerCase();
    			break;
    		}
    	}
    	return actualTravellerClass;
    }

	//press apply button after selecting travellers and their class
	public void applyTravelerNumberAndClass() {
		find(applyButton).click();
	}

	// get the actual total number to assert
	public String getTotalTravellersNumber() {
		WebElement totalTravellersNumber = find(totalTravellerNumberLocator);
		String actualTotalTravellerNumber= totalTravellersNumber.getText().split(" ")[0];
		
		return actualTotalTravellerNumber;
	}

}
