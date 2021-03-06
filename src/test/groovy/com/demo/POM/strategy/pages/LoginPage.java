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
public class LoginPage extends BasePageObject {
    @FindBy(id = "email")
    private WebElement txtEmail;

    @FindBy(id = "passwd")
    private WebElement txtPassword;

    @FindBy(id = "SubmitLogin")
    private WebElement btnSubmit;

	/**
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

    /* (non-Javadoc)
         * @see BasePageObject#getUniqueElement()
         */
	@Override
	protected By getUniqueElement() {
		return By.cssSelector(".lost_password > a");
	}

    /**
     * The method allows for the user to specify which page object they want to be redirected to
     * based on whether the user tried to log in during the checkout process
     * or during the
     * @param email email address
     * @param password password for the account
     * @return instance of the page object requested via the clazz instance
     */
    public <T extends BasePageObject> T signIn(String email, String password, Class<T> clazz) {
        util.enterText(txtEmail, email);
        util.enterText(txtPassword, password);

        btnSubmit.click();

        return PageFactory.initElements((WebDriver) driver, clazz);
    }
}
