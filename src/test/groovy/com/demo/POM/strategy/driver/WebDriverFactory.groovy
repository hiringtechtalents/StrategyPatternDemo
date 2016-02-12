package com.demo.POM.strategy.driver

import org.openqa.selenium.WebDriver


public final class WebDriverFactory {
	private static WebDriverFactory instance
	private def localDriverInstance, mobileDriverInstance, remoteDriverInstance, sauceLabsDriverInstance

	private ThreadLocal<WebDriver> driver

	private WebDriverFactory() {}

	public static WebDriverFactory getInstance() {
		if (instance == null) {
			synchronized(WebDriverFactory.class) {
				if (instance == null) instance = new WebDriverFactory();
			}
		}
		return instance;
	}

	public WebDriver getDriver(String driverType) throws Exception {
		//log.info("Entering getDriver method with the param driverType: ${driverType}")
		driver = new ThreadLocal<WebDriver>() {
			@Override
			protected WebDriver initialValue() {
				switch (driverType) {
					case "local":
						//log.info("In local driver type case block")
						return getLocalDriverInstance() as WebDriver
					case "remote":
						//log.info("In remote driver type case block")
						return getRemoteDriverInstance() as WebDriver
					case "mobile":
						//log.info("In mobile driver type case block")
						return getMobileDriverInstance() as WebDriver
					case "saucelabs":
						//log.info("In saucelabs driver type case block")
						return getSauceLabsDriverInstance() as WebDriver
					default:
						//log.info("UnSupported driver type requested: ${driverType}")
						throw new RuntimeException("UnSupported driver type requested: ${driverType}")
				}
			}
		}

		//log.info("exiting getDriver method of ${this.class.simpleName} class")

		driver.get()
	}

	private def getLocalDriverInstance() {
		//log.info("creating a singleton local driver instance ...")
		if (localDriverInstance == null) {
			synchronized (WebDriverFactory.class) {
				if (localDriverInstance == null) localDriverInstance = new LocalDriver().createDriver()
			}
		}

		localDriverInstance
	}

	private def getRemoteDriverInstance() {
		//log.info("creating a singleton remote driver instance ...")
		if (remoteDriverInstance == null) {
			synchronized (WebDriverFactory.class) {
				if (remoteDriverInstance == null) remoteDriverInstance = new RemoteDriver().createDriver()
			}
		}

		remoteDriverInstance
	}

	private def getMobileDriverInstance() {
		//log.info("creating a singleton mobile driver instance ...")
		if (mobileDriverInstance == null) {
			synchronized (WebDriverFactory.class) {
				if (mobileDriverInstance == null) mobileDriverInstance = new MobileDriver().createDriver()
			}
		}

		mobileDriverInstance
	}

	private def getSauceLabsDriverInstance() {
		//log.info("creating a singleton Sauce driver instance ...")
		if (sauceLabsDriverInstance == null) {
			synchronized (WebDriverFactory.class) {
				if (sauceLabsDriverInstance == null) sauceLabsDriverInstance = new SauceLabsDriver().createDriver()
			}
		}

		sauceLabsDriverInstance
	}

	public void closeDriver() {
		//log.info("Entering closeDriver method of ${this.class.simpleName} class")
		//log.info("quitting the current active WebDriver instance")
		driver.get().quit()

		//log.info("De-registering the WebDriver instance from ThreadLocal instance")
		driver.remove()

		//log.info("Exiting closeDriver method of ${this.class.simpleName} class")
	}
}