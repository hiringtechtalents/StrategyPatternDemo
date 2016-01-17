/**
 * 
 */
package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author SANDEEP
 *
 */
public class Cart extends BasePageObject {
	
	@FindBy(css = "p.cart_navigation > a.standard-checkout")
	private WebElement toSignInPage;

	/**
	 * @param driver
	 */
	public Cart(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}

	/**
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		return By.id("cart_summary");
	}
	
	public AuthenticationPage toAuthenticationPage() {
		toSignInPage.click();
		return new AuthenticationPage((WebDriver) driver);
	}

}
