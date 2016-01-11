package com.demo.POM.strategy.driver
/**
 * The base class for creating a WebDriver instance based on whether the
 * requested driver is local, remote or mobile
 */

/**
 * @author SANDEEP
 *
 */
import org.openqa.selenium.WebDriver
import com.demo.POM.strategy.FrameworkConfig

abstract class Driver {
	
	protected driver;
	protected config;
	
	public Driver() {
		config = FrameworkConfig.getInstance().getConfig();
	}
	
	protected abstract WebDriver createDriver();
}
