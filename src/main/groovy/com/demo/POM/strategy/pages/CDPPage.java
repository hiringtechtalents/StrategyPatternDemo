/**
 * 
 */
package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import com.demo.POM.strategy.util.util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author SANDEEP
 *
 */
public class CDPPage extends BasePageObject {
	@FindBy(css = "ul.product_list > li")
	private List<WebElement> products;
	
	private WebElement productToAdd;
	
	private byte whichItemToAdd;

	/**
	 * @param driver
	 */
	public CDPPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
		
		whichItemToAdd = (byte) (util.randomNumGenerator(0, products.size()) + 1);
		
		productToAdd = driver.findElement(By.cssSelector("ul.product_list > li:nth-child("
								+(whichItemToAdd)+") a.product_img_link"));
	}

	/** 
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		return By.cssSelector("span.category-name");
	}
	
	/**
	 * The method navigates the user to a random PDP from the CPD page
	 * @return instance of PDP page
	 */
	public PDPPage clickOnProduct() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", productToAdd);
		
		return new PDPPage((WebDriver) driver);
	}
}
