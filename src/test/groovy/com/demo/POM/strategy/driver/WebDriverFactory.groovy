package com.demo.POM.strategy.driver

import org.openqa.selenium.WebDriver


public final class WebDriverFactory {
	private static WebDriverFactory instance

	private ThreadLocal<WebDriver> driver
	
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
				driver = new ThreadLocal<>() {
					@Override
					protected WebDriver initialValue() {
						new LocalDriver().createDriver()
					}
				}
                break
			case "remote":
                driver = new ThreadLocal<>() {
                    @Override
                    protected WebDriver initialValue() {
                        new RemoteDriver().createDriver()
                    }
                }
                break
			case "mobile":
                driver = new ThreadLocal<>() {
                    @Override
                    protected WebDriver initialValue() {
                        new MobileDriver().createDriver()
                    }
                }
                break
			case "saucelabs":
                driver = new ThreadLocal<>() {
                    @Override
                    protected WebDriver initialValue() {
                        new SauceLabsDriver().createDriver()
                    }
                }
                break
			default: throw new Exception ("UnSupported driver type requested: " + driverType);

			return driver.get()
		}
	}

    public void closeDriver() {
        driver.get().quit()

        driver.remove()
    }
}