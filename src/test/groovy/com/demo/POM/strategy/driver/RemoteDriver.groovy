package com.demo.POM.strategy.driver
/**
 * The class is responsible for creating a concrete RemoteWebDriver instance
 * 
 */
import org.openqa.selenium.Platform
import org.openqa.selenium.WebDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * @author SANDEEP
 *
 */
class RemoteDriver extends Driver {
	
	public RemoteDriver() {
		super()
	}

	/* (non-Javadoc)
	 * @see com.test.driver.Driver#createDriver(java.lang.String)
	 */
	@Override
	WebDriver createDriver() {
		def browser = config.seleniumConfigs.remote.browser
		def hostAddress = config.seleniumConfigs.remote.ip
		def hostPort = config.seleniuConfigs.remote.port
		def platform = config.seleniumConfigs.remote.platform
		def version = config.seleniumConfigs.remote.version
		def capabilities
		
		if(driver == null) {
			if (browser.equals("firefox")) {
				capabilities = DesiredCapabilities.firefox()
			} else if (browser.contains("internet")) {
				capabilities = DesiredCapabilities.internetExplorer()
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true)
				capabilities.setCapability("ie.ensureCleanSession", true);
			} else if (browser.equals("chrome")) {
				capabilities = DesiredCapabilities.chrome()
			} else if (browser.equals('safari')) {
				capabilities = DesiredCapabilities.safari()
			} else {
				throw new RuntimeException("Browser type unsupported")
			}
		} else { return driver }

		capabilities.version = version
		capabilities.platform = Platform.fromString(platform)
		return (new RemoteWebDriver(
				new URL("http://${hostAddress}:${hostPort}/wd/hub"), capabilities))
	}
}
