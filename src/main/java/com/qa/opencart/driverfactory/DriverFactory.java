package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.AutomationException;

public class DriverFactory {

	private WebDriver driver;
	private Properties prop;
	private OptionsManager opt;
	// To make sure of every thread gets their own copy of WebDriver
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
	public static String highlight = null;

	public WebDriver init(Properties prop) {
		opt = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");
		highlight = prop.getProperty("highlight");		// String browserName = System.getProperty("browser"); //Reading properties from
		// maven parameter via cmd

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tDriver.set(new ChromeDriver(opt.getChromeOptions()));
			break;

		case "firefox":
			tDriver.set(new FirefoxDriver(opt.getFirefoxOptions()));
			break;

		case "edge":
			tDriver.set(new EdgeDriver(opt.getEdgeOptions()));
			break;

		case "safari":
			tDriver.set(new SafariDriver());
			break;

		default:
			throw new AutomationException("Please pass a valid browser type");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(App));
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static WebDriver getDriver() {
		return tDriver.get();
	}

	public Properties initProp() {
		// mvn clean install -Denv="UAT
		prop = new Properties();
		FileInputStream fis = null;

		// It reads the system varibles passing from maven
		String env = System.getProperty("env");
		System.out.println("Environment name is " + env);

		try {
			if (env == null) {
				fis = new FileInputStream("./src/test/resource/Configuration/config.properties");
			} else {
				switch (env.toLowerCase().trim()) {
				case "uat":
					fis = new FileInputStream("./src/test/resource/Configuration/config.uat.properties");
					break;
				case "ci":
					fis = new FileInputStream("./src/test/resource/Configuration/config.ci.properties");
					break;
				default:
					throw new AutomationException("Please pass the right environment");
				}
			}
		} catch (FileNotFoundException f) {
			System.out.println(f.getMessage());
		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + methodName + "_" + System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	}

