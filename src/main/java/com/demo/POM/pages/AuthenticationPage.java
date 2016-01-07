/**
 * 
 */
package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author SANDEEP
 *
 */
public class AuthenticationPage extends BasePageObject {

    private CreateAccountPage createAccount;

    private LoginPage login;

	/**
	 * @param driver WebDriver instance
	 */
	public AuthenticationPage(WebDriver driver) {
		super(driver);
		
		createAccount = new CreateAccountPage(driver);
		
		login = new LoginPage(driver);
	}

	/*
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		return By.cssSelector("#columns > .breadcrumb.clearfix > .navigation_page");
	}
	
	public AddressPage signUp(String email) {
		PersonalInfoPage personalInfo;

		if(createAccount.isValidEmail(email)) {
			personalInfo = createAccount.signUp(email);
		}else {
			throw new RuntimeException("Invalid email address entered.");
		}

		return personalInfo.enterPersonalInfo(PersonalInfoPage.personalInfo)
				.enterAddressDetails(PersonalInfoPage.addressInfo)
				.enterOtherDetails(PersonalInfoPage.otherInfo)
				.register();
	}

	public AddressPage login(String email, String password) {
        return login.signInAsExistingUserDuringCheckout(email, password);
	}
}
