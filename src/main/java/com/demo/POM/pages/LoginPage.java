/**
 * 
 */
package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import com.demo.POM.util.util;
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

		PageFactory.initElements(driver, this);
	}

	/* (non-Javadoc)
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		return By.cssSelector(".lost_password > a");
	}

    public AddressPage signInAsExistingUserDuringCheckout(String email, String password) {
        util.enterText(txtEmail, email);
        util.enterText(txtPassword, password);

        btnSubmit.click();

        // TODO: make this generic so that it can go to My Account if the user signs in first,
        // AddressPage if the user enters login info during checkout
        // or back to LoginPage if the user enters invalid information.
        return new AddressPage(driver);
    }
}
