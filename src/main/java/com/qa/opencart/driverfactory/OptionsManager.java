package com.qa.opencart.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions opt;
	private FirefoxOptions fopt;
	private EdgeOptions eopt;
	private SafariOptions sopt; // Doesn't support incognito and headless

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		opt = new ChromeOptions();
//		String mvnHeadProp = System.getProperty("headless");
//		String mvnIncogProp = System.getProperty("incognito");

		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			opt.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			opt.addArguments("--incognito");

//		if (Boolean.parseBoolean(mvnHeadProp.trim()))
//			opt.addArguments("--headless");
//		if (Boolean.parseBoolean(mvnIncogProp.trim()))
//			opt.addArguments("--incognito");
		return opt;
	}

	public FirefoxOptions getFirefoxOptions() {
		fopt = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			fopt.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			fopt.addArguments("--incognito");
		return fopt;
	}

	public EdgeOptions getEdgeOptions() {
		eopt = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			eopt.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			eopt.addArguments("--incognito");
		return eopt;
	}

}
