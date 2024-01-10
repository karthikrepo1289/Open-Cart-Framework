package com.qa.opencart.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.exceptions.AutomationException;
import com.qa.opencart.exceptions.SelectValidElementType;

public class ElementUtility {

	private WebDriver driver;
	private JavaScriptUtil js;

	private void isHighlight(WebElement elmnt) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			js.flash(elmnt);
		}
	}
	public ElementUtility(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		js = new JavaScriptUtil(driver);
	}

	/**
	 * This method finds an element based on the locator and returns WebElement
	 * 
	 * @param By locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement elmnt = driver.findElement(locator);
		isHighlight(elmnt);
		return elmnt;
	}

	/**
	 * Overloaded method of getElement which finds an element
	 * 
	 * @param locatorType, locatorAddress
	 * @return WebElement
	 */
	public WebElement getElement(String locatorType, String locatorAddress) {
		WebElement elmnt = driver.findElement(getBy(locatorType, locatorAddress));
		isHighlight(elmnt);
		return elmnt;
	}

	/**
	 * This method sending the keys to element on webpage
	 * 
	 * @param By     locator
	 * @param String value
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	/**
	 * Overloaded Method of doSendKeys that accepts locator type and its address.
	 * also value that needs to be sent.
	 * 
	 * @param :
	 * @param locatorType
	 * @param locatorAddress
	 * @param value          to pass
	 */
	public void doSendKeys(String locatorType, String locatorAddress, String value) {
		getElement(getBy(locatorType, locatorAddress)).sendKeys(value);
	}

	/**
	 * This method performs click operation on WebElement.
	 * 
	 * @param By locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * Overloaded Method of doClick that accepts locator type and its address and
	 * Performs click operation.
	 * 
	 * @param locatorType
	 * @param locatorAddress
	 */
	public void doClick(String locatorType, String locatorAddress) {
		getElement(getBy(locatorType, locatorAddress)).click();
	}

	/**
	 * Method which returns the visible text of an element.
	 * 
	 * @param locator
	 * @return String
	 */

	public String dogetElementText(By locator) {

		return getElement(locator).getText();
	}

	/**
	 * Overloaded Method which returns the visible text of an element.
	 * 
	 * @param locatorType, locatorAddress
	 * @return String
	 */
	public String dogetElementText(String locatorType, String locatorAddress) {
		return getElement(getBy(locatorType, locatorAddress)).getText();
	}

	/**
	 * }
	 * 
	 * @param String, String
	 * @return By
	 * 
	 *         This method is used to return the By class instance by accepting
	 *         locator type and locator address
	 */
	public By getBy(String locatorType, String locatorAddress) {

		By by = null;

		switch (locatorType.toLowerCase().trim()) {

		case "id":
			by = By.id(locatorAddress);
			break;

		case "name":
			by = By.name(locatorAddress);
			break;

		case "classname":
			by = By.className(locatorAddress);
			break;

		case "xpath":
			by = By.xpath(locatorAddress);
			break;

		case "cssselector":
			by = By.cssSelector(locatorAddress);
			break;

		case "linktext":
			by = By.linkText(locatorAddress);
			break;

		case "partiallinktext":
			by = By.partialLinkText(locatorAddress);
			break;

		case "tagName":
			by = By.tagName(locatorAddress);
			break;

		default:
			throw new SelectValidElementType("Please select valid locator type");
		}
		return by;
	}

	/**
	 * Method to get the attribute value of a WebElement
	 * 
	 * @param locator of type By, attributeName of type String
	 * @return Attribute value of type String
	 */
	public String doGetAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	/**
	 * Utility method to return the list of elements present in the web page
	 * 
	 * @param By
	 * @return List<WebElement>
	 */
	public List<WebElement> doGetElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * Utitlity method to find number of elements
	 * 
	 * @param By
	 * @return int
	 */
	public int doGetNumberOfElements(By locator) {
		return doGetElements(locator).size();
	}

	/**
	 * Utility method to find the text of elements
	 * 
	 * @param By
	 * @return List<String>
	 */
	public List<String> doGetElementsText(By locator) {
		WebElement lnk;
		List<WebElement> links = doGetElements(locator);
		List<String> listOfTitles = new ArrayList<String>();

		for (int i = 0; i < links.size(); i++) {
			lnk = links.get(i);
			if (lnk.getText().length() != 0) {
				listOfTitles.add(lnk.getText());
			}
		}
		return listOfTitles;
	}

	/**
	 * Utility method to find an attirbute value of list of webelements
	 * 
	 * @param By, String
	 * @return List<String>
	 */

	public List<String> getElementsAttributeList(By locator, String attribueName) {
		List<WebElement> elementsList = doGetElements(locator);
		List<String> attributeValues = new ArrayList<String>();
		int size = elementsList.size();

		for (int i = 0; i < size; i++) {
			WebElement elmnt = elementsList.get(i);
			attributeValues.add(elmnt.getAttribute(attribueName));
		}
		return attributeValues;
	}

	/**
	 * Utility method to click a particular suggestion among multiple options
	 * 
	 * @param searchBoxID
	 * @param textToSearch
	 * @param addressOfSuggesstions
	 * @param elementToClick
	 * @throws InterruptedException
	 */
	public void searchAndClick(By searchBoxID, String textToSearch, By addressOfSuggesstions, String elementToClick)
			throws InterruptedException {
		doSendKeys(searchBoxID, textToSearch);
		Thread.sleep(5000);

		List<WebElement> srch = doGetElements(addressOfSuggesstions);
		for (WebElement elmnt : srch) {
			if (elmnt.getText().equalsIgnoreCase(elementToClick)) {
				elmnt.click();
				break;
			}
		}

	}

	/**
	 * @param locator, elementToClick Utility function to capture all the required
	 *                 elements and click on desired element
	 *
	 */
	public void clickOnReqElement(By locator, String elementToClick) {
		List<WebElement> listOfElements = doGetElements(locator);
		for (WebElement elmnt : listOfElements) {
			if (elmnt.getText().contains(elementToClick)) {
				elmnt.click();
				break;
			}
		}
	}

	/**
	 * Utility method to check element is present or not.
	 * 
	 * @param locator
	 * @return boolean
	 */
	public boolean checkElementPresence(By locator) {
		return doGetElements(locator).size() == 1 ? true : false;
	}

	/**
	 * Utility method to check multiple elements are present or not.
	 * 
	 * @param locator
	 * @return boolean
	 */
	public boolean checkMultipleElements(By locator) {
		return doGetElements(locator).size() > 1 ? true : false;
	}

	/**
	 * Utility method that creates a Select object to work with drop downs
	 * 
	 * @param locator
	 * @return Select
	 */
	private Select createSelect(By locator) {
		return new Select(getElement(locator));
	}

	/**
	 * Utility method to select a value from a drop down.
	 * 
	 * @param locator
	 * @param selectBy
	 * @param valueToPass
	 */
	public Select doSelectValuesFromDropDown(By locator, String selectBy, String valueToPass) {

		// Select dropDown = new Select(getElement(locator));

		switch (selectBy.toLowerCase().trim()) {

		case "value":
			createSelect(locator).selectByValue(valueToPass);
			break;

		case "index":
			createSelect(locator).selectByIndex(Integer.parseInt(valueToPass));
			break;

		case "text":
			createSelect(locator).selectByVisibleText(valueToPass);
			break;

		}
		return createSelect(locator);
	}

	/**
	 * Utility method to select the dropdown values directly using option tag
	 * 
	 * @param locator
	 * @param valueToSelect
	 */
	public void selectDropDownValueUsingOption(By locator, String valueToSelect) {

		// Select dropDown = new Select(getElement(locator));
		List<WebElement> options = createSelect(locator).getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(valueToSelect)) {
				option.click();
				break;
			}
		}
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */
	public int getNumberOfOptionsInDropDown(By locator) {
		Select dropDown = new Select(getElement(locator));
		return dropDown.getOptions().size();
	}

	/**
	 * Utility method to capture all the visible values from dropdown.
	 * 
	 * @param locator
	 * @return List<String>
	 */
	public List<String> getAllTheDropDownOptions(By locator) {
		Select dropDown = new Select(getElement(locator));
		List<WebElement> options = dropDown.getOptions();
		List<String> textOfOptions = new ArrayList<String>();

		for (WebElement option : options) {
			textOfOptions.add(option.getText());
		}
		return textOfOptions;
	}

	/**
	 * Utility method to select the value from a drop down directly without select
	 * class
	 * 
	 * @param locator
	 * @param expectedOptionToSelect
	 */
	public void selectDropDownValue(By locator, String expectedOptionToSelect) {
		List<WebElement> options = doGetElements(locator);
		String text;
		for (WebElement option : options) {
			text = option.getText();
			if (text.equals(expectedOptionToSelect)) {
				option.click();
				break;
			}
		}
	}

	/*-----------------------------------------Multi Dropdown using Select Tag-----------------------------------------------------------*/

	/**
	 * Utility method to find a drop down is multi select or not.
	 * 
	 * @param locator
	 * @return booelan
	 */
	public boolean CheckIsItMultiDropdown(By locator) {
		return createSelect(locator).isMultiple();
	}

	/**
	 * Utility method to select 1. Single value 2. Multiple Values 3. All the
	 * values. but pass values as "all"
	 * 
	 * @param locator
	 * @param values
	 */
	public void selectDropDownWithMultiSelect(By locator, By optionLocator, String... values) {
		Select dropDown = createSelect(locator);

		if (CheckIsItMultiDropdown(locator) && values[0].equalsIgnoreCase("all")) {
			List<WebElement> options = doGetElements(optionLocator);
			for (WebElement e : options) {
				e.click();
			}
		} else {
			for (int i = 0; i < values.length; i++) {
				dropDown.selectByVisibleText(values[i]);
			}
		}
	}

	/***************************************************
	 * Selecting the values from non select tag dropdown
	 ***************************************/
	/**
	 * Utility method to select an option from non select dropdown
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectValueFromNonSelect(By locator, String value) {
		List<WebElement> lstOptions = doGetElements(locator);

		for (WebElement option : lstOptions) {
			String text = option.getText();
			if (text.equals(value)) {
				option.click();
				break;
			}
		}
	}

	/**
	 * Utility method to select multiple values from non select drop down. Pass
	 * "all" as parameter if you want to select all values
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectMultiValuesFromNonSelect(By locator, String... value) {
		List<WebElement> lstOptions = doGetElements(locator);

		if (value.length == 1 && value[0].equalsIgnoreCase("all")) {
			for (WebElement option : lstOptions) {
				try {
					option.click();
				} catch (ElementNotInteractableException e) {

				}
			}
		}
	}

//***************************************** Action Utility ************************************************//

	private Actions createActionsInstance() {
		Actions act = new Actions(driver);
		return act;
	}

	/**
	 * Utility Method to mouse hover on parent menu and click on sub menu.
	 * 
	 * @param parentMenuAddress
	 * @param childMenuAddress
	 * @throws InterruptedException
	 */

	public void parentChildMenu(By parentMenuAddress, By childMenuAddress) throws InterruptedException {

		Actions act = createActionsInstance();
		WebElement parentMenu = getElement(parentMenuAddress);
		act.moveToElement(parentMenu).build().perform();
		Thread.sleep(2000);
		doClick(childMenuAddress);
	}

	/**
	 * 
	 * @param parentLocator
	 * @param numberOfChildLocators
	 * @throws InterruptedException
	 */

	public void multiMenuHandle(By parentLocator, ArrayList<By> numberOfChildLocators) throws InterruptedException {

		Actions act = new Actions(driver);
		doClick(parentLocator);
		Thread.sleep(4000);

		for (int i = 0; i < numberOfChildLocators.size(); i++) {
			WebElement elmnt = getElement(numberOfChildLocators.get(i));
			if (i != numberOfChildLocators.size() - 1) {
				act.moveToElement(elmnt).build().perform();
				Thread.sleep(3000);
				continue;
			}
			act.moveToElement(elmnt).click().build().perform();
		}

	}

	/**
	 * Utility method to send the values to input box using Actions class sendkeys
	 * method Actions class sendkeys first clicks the elment then pass the values
	 * 
	 * @param elementAddress
	 * @param valueToPass
	 */
	public void doActionSendKeys(By elementAddress, String valueToPass) {
		Actions act = new Actions(driver);
		WebElement val = getElement(elementAddress);
		act.sendKeys(val, valueToPass).build().perform();
	}

	/**
	 * Utility method to click the element using Actions class click method Actions
	 * class click method first performs moveToElement(WebElement elmnt) then clicks
	 * the element.
	 * 
	 * @param elementToClick
	 */
	public void doActionClick(By elementToClick) {
		Actions act = new Actions(driver);
		WebElement elmnt = getElement(elementToClick);
		act.click(elmnt).build().perform();

	}

	/**
	 * Utility Method that sends the keys to input area with some pause using
	 * Actions class
	 * 
	 * @param location
	 * @param value
	 */
	public void doActionSendKeysWithPause(By location, String value) {
		WebElement elmnt = getElement(location);
		Actions act = new Actions(driver);
		char[] vals = value.toCharArray();

		for (int i = 0; i < vals.length; i++) {
			act.sendKeys(elmnt, String.valueOf(vals[i])).pause(500).build().perform();
		}
	}

	/**
	 * Utility method to move scroll to particular element using Actions class
	 * scrollToElement method
	 * 
	 * @param locator
	 */
	public void doActionScrollToElement(By locator) {
		WebElement elmnt = getElement(locator);
		Actions act = new Actions(driver);
		act.scrollToElement(elmnt).build().perform();
	}

	/*********************************
	 * Synchronization-Explicit Wait
	 ************************************/

	/**
	 * Method to check the given element present in the dom with in specified tom.
	 * It doesn't check the element visibility on WebPage
	 * 
	 * @param locator
	 * @param timeInSeconds
	 * @return
	 */
	public WebElement checkPresenceOfElementwithWait(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	/**
	 * This method checks the element is present in dom or not and element visiblity
	 * on webpage with specified time.
	 * 
	 * @param locator
	 * @param timeInSeconds
	 * @return
	 */
	public WebElement checkElementVisibilitywithWait(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * This method checks the visibility of element with specified time and with
	 * specified interval
	 * 
	 * @param locator
	 * @param timeInSeconds
	 * @param pollingInterval
	 * @return
	 */
	public WebElement checkElementVisibilitywithWait(By locator, int timeInSeconds, int pollingInterval) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds),
				Duration.ofSeconds(pollingInterval));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * This method first checks the element visibility on webpage and If it is
	 * visible with in specified time then it clicks on the element
	 * 
	 * @param locator
	 * @param maxTime
	 */
	public void doClickWithWait(By locator, int maxTime) {
		checkElementVisibilitywithWait(locator, maxTime).click();
	}

	/**
	 * This method first checks the element visibility on webpage and If it is
	 * visible with in specified time then it enters the value on the element
	 * 
	 * @param locator
	 * @param maxTime
	 * @param valueToEnter
	 */
	public void doSendKeysWithWait(By locator, int maxTime, String valueToEnter) {
		checkElementVisibilitywithWait(locator, maxTime).sendKeys(valueToEnter);
	}

	/**
	 * Utility method to check the title of a webpage with time specified and
	 * returns the title if found
	 * 
	 * @param title
	 * @param timeOut
	 * @return WebPageTitle
	 */
	public String waitForTitleContains(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		boolean isTitleDisplayed = wait.until(ExpectedConditions.titleContains(title));
		try {
			if (isTitleDisplayed) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Given value is not present");
			e.getMessage();
		}
		return null;
	}

	/**
	 * Utility method that find the title of a page in given specified time and If
	 * it is found it returns title it not returns null
	 * 
	 * @param title
	 * @param timeOut
	 * @return TitleOfWebpage
	 */
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		boolean isTitleDisplayed = wait.until(ExpectedConditions.titleIs(title));
		try {
			if (isTitleDisplayed) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Given value is not present");
			e.getMessage();
		}
		return null;
	}

	/**
	 * 
	 * @param expURL
	 * @param timeOut
	 * @return
	 */
	public String waitForURL(String expURL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean isURLMatches = wait.until(ExpectedConditions.urlContains(expURL));
		try {
			if (isURLMatches) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println("URL Didn't match");
			e.getMessage();
		}
		return null;
	}

	public String waitForURLTOBE(String expURL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		boolean isURLMatches = wait.until(ExpectedConditions.urlToBe(expURL));
		try {
			if (isURLMatches) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println("URL Didn't match");
			e.getMessage();
		}
		return null;
	}

	private Alert waitAlertIsPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		Alert alrt = wait.until(ExpectedConditions.alertIsPresent()); // driver.switchTo().alert();
		try {
			if (alrt != null) {
				return alrt;
			}
		} catch (NoAlertPresentException e) {
			System.out.println("Alert is not present");
			e.getMessage();
		}
		return null;
	}

	public void waitacceptJSAlert(int timeOut) {
		Alert a = waitAlertIsPresent(timeOut);
		if (a != null)
			a.accept();
	}

	public void waitdismissJSAlert(int timeOut) {
		Alert a = waitAlertIsPresent(timeOut);
		if (a != null)
			a.dismiss();
	}

	public String waitgetTextJSAlert(int timeOut) {
		Alert a = waitAlertIsPresent(timeOut);
		return a.getText();
	}

	public void waitAlert_enterValueOnJSscript(int timeOut, String value) {
		waitAlertIsPresent(timeOut).sendKeys(value);
	}

	public void waitAndSwitchToFrame(By locator, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Checks the frame presence and wait max 10 sec. If frame available then it
		// switch to the frame. If not TimeOutException
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitAndSwitchToFrame(String IDOrName, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Checks the frame presence and wait max 10 sec. If frame available then it
		// switch to the frame. If not TimeOutException
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void waitAndSwitchToFrame(int index, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Checks the frame presence and wait max 10 sec. If frame available then it
		// switch to the frame. If not TimeOutException
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitAndSwitchToFrame(WebElement frameElement, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Checks the frame presence and wait max 10 sec. If frame available then it
		// switch to the frame. If not TimeOutException
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public boolean waitAndCheckWindowVisibility(int timeOut, int expNumberOfWindowsToBeOpened) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			boolean isWindowOpened = wait.until(ExpectedConditions.numberOfWindowsToBe(expNumberOfWindowsToBeOpened));
			if (isWindowOpened) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("Window not opened");
		}
		return false;
	}

	public void clickElementWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			// Returns WebElement once it is enabled and visible
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (TimeoutException e) {
			System.out.println("Element is not enabled");
		}
	}

	public void waitForElementWithFluentWait(By address, int timeOut, int interval) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(interval)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(address));
	}

	public void waitForFrameWithFluentWait(String nameorID, int timeOut, int interval) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(interval)).ignoring(NoSuchFrameException.class);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameorID));
	}

	public void waitForAlertWithFluentWait(int timeOut, int interval) {
		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(interval))
				.pollingEvery(Duration.ofSeconds(interval)).ignoring(NoAlertPresentException.class);

		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**************************
	 * Custom Wait
	 ***********************************************/
	public WebElement retryingElement(By locator, int timeOut) {
		WebElement elmnt = null;
		int numberOfAttempts = 0;

		while (numberOfAttempts < timeOut) {
			try {
				elmnt = getElement(locator);
				System.out.println(locator + " Element is found ");
				break;
			} catch (NoSuchElementException exception) {
				System.out.println(locator + " Element is not found" + "Current attempt is " + numberOfAttempts);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			numberOfAttempts++;
		}

		if (elmnt == null) {
			System.out.println(
					"Unable to find the element " + locator + " Number of attempts we made is " + numberOfAttempts);
			throw new AutomationException("Element Not Found");
		}
		return elmnt;
	}

	public WebElement retryingElement(By locator, int timeOut, int pollingTime) {
		WebElement elmnt = null;
		int numberOfAttempts = 0;

		while (numberOfAttempts < timeOut) {
			try {
				elmnt = getElement(locator);
				System.out.println(locator + " Element is found ");
				break;
			} catch (NoSuchElementException exception) {
				System.out.println(locator + " Element is not found" + "Current attempt is " + numberOfAttempts);
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			numberOfAttempts++;
		}

		if (elmnt == null) {
			System.out.println(
					"Unable to find the element " + locator + " Number of attempts we made is " + numberOfAttempts);
			throw new AutomationException("Element Not Found");
		}
		return elmnt;
	}
}