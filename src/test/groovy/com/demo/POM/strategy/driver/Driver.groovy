package com.demo.POM.strategy.driver

import com.demo.POM.strategy.base.FrameworkConfig
import org.openqa.selenium.WebDriver

/**
 * The base class for creating a WebDriver instance based on whether the
 * requested driver is local, remote or mobile
 */

/**
 * @author SANDEEP
 *
 */
abstract class Driver {
	
	protected driver;
	protected config;
	
	public Driver() {
		config = FrameworkConfig.getInstance().getConfig();
	}
	
	protected abstract WebDriver createDriver();
}
