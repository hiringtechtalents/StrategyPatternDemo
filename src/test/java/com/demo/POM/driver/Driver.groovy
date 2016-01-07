package com.demo.POM.driver
import com.demo.POM.FrameworkConfig;
/**
 * The base class for creating a WebDriver instance based on whether the
 * requested driver is local, remote or mobile
 */

/**
 * @author SANDEEP
 *
 */
import org.openqa.selenium.WebDriver

abstract class Driver {
	
	protected driver;
	protected config;
	
	public Driver() {
		config = FrameworkConfig.getInstance().getConfig();
	}
	
	protected abstract WebDriver createDriver();
}
