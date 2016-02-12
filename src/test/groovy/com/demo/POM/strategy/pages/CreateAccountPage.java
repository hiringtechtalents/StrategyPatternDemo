/**
 * 
 */
package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.base.BasePageObject;
import com.demo.POM.strategy.util.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author SANDEEP
 *
 */
public class CreateAccountPage extends BasePageObject {
	
	@FindBy(id = "email_create")
	private WebElement email;
	
	@FindBy(id = "SubmitCreate")
	private WebElement CreateAccount;

	/**
	 * @param driver
	 */
	public CreateAccountPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}

	/* (non-Javadoc)
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		return By.cssSelector("#create-account_form > h3.page-subheading");
	}
	
	public PersonalInfoPage signUp(String email) {
		/*this.email.click();
		this.email.clear();
		this.email.sendKeys(email);*/
		util.enterText(this.email, email);
		
		this.email.submit();
		
		return new PersonalInfoPage((WebDriver) driver);
	}
	
	public boolean isValidEmail(String email) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(EMAIL_REGEX);
	}

}
