package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.base.BasePageObject;
import com.demo.POM.strategy.util.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author SANDEEP
 *
 */
public class HomePage extends BasePageObject {
	@FindBy(css="#block_top_menu > ul > li > a")
	private List<WebElement> categories;
	
	@FindBy(css="#homefeatured a.button.lnk_view.btn.btn-default")
	private List<WebElement> productsOnHomePage;

	/**
	 * public constructor
	 * @param driver
	 */
	public HomePage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}

	/**
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		// as slider is the unique element on the home page.
		return By.id("homepage-slider");
	}
	
	/**
	 * The method clicks on a random category option in the global nav bar.
	 * this is done via the random number generated between 0 and the length
	 * of the categories WebElement array
	 * @return instance of CDP page to be initialized
	 */
	public CDPPage navigateToMainCategoryPage() {
		byte indexToClick = (byte) util.randomNumGenerator(0, categories.size());
		categories.get(indexToClick).click();
		
		return (new CDPPage((WebDriver) driver));
	}
	
	public PDPPage navigateTOProductDisplayPage() {
		byte indexToClick = (byte) util.randomNumGenerator(0, productsOnHomePage.size());
		productsOnHomePage.get(indexToClick).click();
		
		return (new PDPPage((WebDriver) driver));
	}

}
