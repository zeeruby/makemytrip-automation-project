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
	private By fromCityLocator = (By.id("fromCity"));
	private By toCityLocator = (By.id("toCity"));
	private By originLocator = By.xpath("//input[@placeholder='From']");
	private By destinationLocator = (By.xpath("//input[@placeholder='To']"));
	private By suggestionsBoxLocator = (By.xpath("//p[contains(text(),'SUGGESTIONS')]"));
	//private By destinationOptionsLocator = By.xpath("//li[contains(@id, 'react-autowhatever-1-section-0-item-')]/div/div/p[1]");
	private By originOptionsLocator = By.xpath("//li[contains(@id,'react-autowhatever-1-section-0-item')]"); 
	// path=//tagname[contains(@Attribute,‘Value’)]
	//private By passengerSelectorModalLocator = By.xpath("//*[@id='passengerSelector']/button");

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public void openPageUrl() {
		openPage(pageUrl);
	}

	public String getOrigin() {
		return find(fromCityLocator).getAttribute("value").toLowerCase();
		
		
	}
	public String getDestination() {
		return find(toCityLocator).getAttribute("value").toLowerCase();
		
	}

	private void getOriginSelector(String originStr) throws InterruptedException {
		find(fromCityLocator).click();
		WebElement elem = find(originLocator);
		elem.clear();
		elem.sendKeys(originStr);
		Thread.sleep(3000);
	}

	public void selectOrigin(String originStr, String origin) throws InterruptedException {
		getOriginSelector(originStr);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> originOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originOptionsLocator));
		for (WebElement option : originOptions) {
			String optionText = option.getText().toLowerCase();
			System.out.println(optionText + "this is option text");
			if (optionText.contains(origin)) {
				option.click();
				//System.out.println(optionText + "this is origin option clicked");
				break;
			}else {
				System.out.println("nothing matches");
			}
		}
	}

	private void getDestinationSelector(String destinationStr) throws InterruptedException {
		WebElement elem = find(destinationLocator);
		elem.click();
		elem.clear();
		elem.sendKeys(destinationStr);
		Thread.sleep(3000);
	}
   
	public void selectDestination(String destinationStr, String destination) throws InterruptedException {
		getDestinationSelector(destinationStr);
		waitForVisibilityOf(suggestionsBoxLocator, Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		List<WebElement> destinationOptions = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(originOptionsLocator));
		for (WebElement option : destinationOptions) {
			if (option.getText().toLowerCase().contains(destination)) {
				System.out.println(option.getText().toLowerCase() + "this is destination option");
				option.click();
				//System.out.println(option.getText().toLowerCase() + "this is destination option clicked");
				Thread.sleep(3000);
				break;
			}
		}

	}

	/*
	 * public PassengerSelectorModal getPassengerSelectorModal() {
	 * find(passengerSelectorModalLocator).click(); return new
	 * PassengerSelectorModal(driver, log); }
	 */

}
