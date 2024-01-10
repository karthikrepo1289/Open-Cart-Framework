package com.qa.opencart.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;

	}

	/**
	 * Utitlity method to get the title of a web page using java script
	 * 
	 * @param jsScript
	 * @return
	 */
	public String getTitleByJavaScript(String jsScript) {
		String title = (String) js.executeScript(jsScript);
		return title;
	}

	/**
	 * Utitlity method to get the URL of a webpage using Java Script
	 * 
	 * @param jsScript
	 * @return
	 */
	public String getUrlByJavaScript(String jsScript) {
		String webPageURL = js.executeScript(jsScript).toString();
		return webPageURL;
	}

	/**
	 * Utility method to generate alert using java script
	 * 
	 * @param msg
	 */
	public void generateJSAlert(String msg) {
		String jsScript = "alert('" + msg + "')";
		js.executeScript(jsScript);
	}

	/**
	 * Utility method to generate confirmation pop up using java script
	 * 
	 * @param msg
	 */
	public void generateJSConfirm(String msg) {
		String jsScript = "confirm('" + msg + "')";
		js.executeScript(jsScript);
	}

	/**
	 * Utility method to generate prompt pop up using java script
	 * 
	 * @param msg
	 * @param value
	 */
	public void generateJSPrompt(String msg, String value) {
		String jsScript = "prompt('" + msg + "')";
		js.executeScript(jsScript);

		Alert prompt = driver.switchTo().alert();
		prompt.sendKeys(value);
		prompt.accept();
	}

	/**
	 * 
	 * @param enterRedirectionType (forward,back,refresh)
	 */
	public void navigateUsingJSScript(String enterRedirectionType) {
		String redirection = enterRedirectionType.toLowerCase().trim();

		switch (redirection) {
		case "forward":
			js.executeScript("history.go(1)");
			break;
		case "back":
			js.executeScript("history.go(-1)");
			break;
		case "refresh":
			js.executeScript("history.go(0)");
			break;
		}
	}

	/**
	 * Utility method to get inner text of a complete webpage using java script
	 * 
	 * @param jsScript
	 * @return String
	 */
	public String getPageInnerText(String jsScript) {
		String pageInnerText = js.executeScript(jsScript).toString();
		return pageInnerText;
	}

	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public void scrollPageDown(int height) {
		js.executeScript("window.scrollTo(0," + height + ");");
	}

	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}

	public void scrollPageUp(int height) {
		js.executeScript("window.scrollTo(" + height + ",0);");
	}

	public void scrollMiddleOfthePage() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2);");
	}

	public void scrollToElement(WebElement elmnt) {
		// arguments[0].scrollIntoView(true)
		js.executeScript("arguments[0].scrollIntoView(true);", elmnt);
	}

	public void zoomChrome(String zoomPercentage) {
		// document.body.style = ''
		String jsScript = "document.body.style='" + zoomPercentage + "%';";
		js.executeScript(jsScript);
	}

	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border = '6px solid red';", element);
	}

	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor(element, "rgb(0,400,0)");
			changeColor(element, bgColor);
		}
	}

	private void changeColor(WebElement elmnt, String color) {
		js.executeScript("arguments[0].style.backgroundColor ='" + color + "'", elmnt);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {

		}
	}

	public void clickElement(WebElement element) {
		// arguments[0].click();
		js.executeScript("arguments[0].click();", element);
	}

	public void sendKeysUsingWithId(String id, String value) {
		// document.getElementById('id').value=''
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
}