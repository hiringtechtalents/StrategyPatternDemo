package com.demo.POM.strategy
import com.demo.POM.strategy.driver.WebDriverFactory
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod

import java.util.concurrent.TimeUnit

import static org.openqa.selenium.support.ThreadGuard.protect

class BaseTest {

	protected static def driver
	protected def config = FrameworkConfig.instance.config

	@BeforeClass
	public void beforeClass() throws Exception {
		// create a WebDriver instance on the basis of the settings
		// provided in Config.groovy class
		// using ThreadGuard.protect in case the tests are run in parallel.
		// in which case the test execution might get affected.
		driver = protect(WebDriverFactory.instance.getDriver(System.getProperty("DRIVERTYPE", "local")))
		driver.manage().timeouts().implicitlyWait(config.IMPLICITWAIT_TIMEOUT, TimeUnit.SECONDS)
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		loadApplication()
	}

	private void loadApplication() {
		//driver.manage().window().maximize()
		driver.get(config.url)
	}

	@AfterMethod(alwaysRun = true)
	public void deleteAllCookies() {
		driver.manage().deleteAllCookies()
	}

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		driver.quit()
	}
}
