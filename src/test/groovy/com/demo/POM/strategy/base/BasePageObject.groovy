package com.demo.POM.strategy.base

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert

import java.util.concurrent.TimeUnit

abstract class BasePageObject {
	protected def driver
	protected WebDriverWait wait

	protected def config = FrameworkConfig.instance.config

	protected def uniqueElement

	public BasePageObject(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(this.driver, config.WEBDRIVERWAIT_TIMEOUT, config.WEBDRIVERWAIT_POLL)

		isLoaded();
	}

	/**
	 * Each page object must implement this method to return the identifier of a unique WebElement on that page.
	 * The presence of this unique element will be used to assert that the expected page has finished loading
	 * @return the By locator of unique element on the page
	 */
	protected abstract By getUniqueElement();

	protected void isLoaded() throws Error{
		//Define a list of WebElements that match the unique element locator for the page
		List<WebElement> uniqueElement = driver.findElements(getUniqueElement())

		// Assert that the unique element is present in the DOM
		Assert.assertTrue((uniqueElement.size() > 0),
				"Unique Element \'${getUniqueElement().toString()}\' not found for ${this.class.simpleName}")

		// Wait until the unique element is visible in the browser and ready to use. This helps make sure the page is
		// loaded before the next step of the tests continue.
		// first reset the implicitwait to 0
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS)
		// then use wait for a specific element using WebDriverWait
		wait.until(ExpectedConditions.visibilityOfElementLocated(getUniqueElement()))
		// then set the implicitwait back to the required time.
		driver.manage().timeouts().implicitlyWait(config.IMPLICITWAIT_TIMEOUT, TimeUnit.SECONDS)
	}
}
