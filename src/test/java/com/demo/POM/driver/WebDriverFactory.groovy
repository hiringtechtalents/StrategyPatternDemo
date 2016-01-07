package com.demo.POM.driver;

import org.openqa.selenium.WebDriver;


public final class WebDriverFactory {
	private static WebDriverFactory instance;
	
	private WebDriverFactory() {}
	
	public static WebDriverFactory getInstance() {
		if (instance == null) {
			synchronized(WebDriverFactory.class) {
				if(instance == null) instance = new WebDriverFactory();
			}
		}
		return instance;
	}
	
	public WebDriver getDriver(String driverType) throws Exception {
		switch (driverType) {
			case "local":
				return new LocalDriver().createDriver();
			case "remote":
				return new RemoteDriver().createDriver();
			case "mobile":
				return new MobileDriver().createDriver();
			case "saucelabs":
				return new SauceLabsDriver().createDriver();
			default: throw new Exception ("UnSupported driver type requested: " + driverType);
		}
	}
}