/**
 * 
 */
package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

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
	
	public AddressPage signUp(String email, Map<String, String> info, Map<String, String> addressDetails,
                        Map<String, String> otherDetails) {
		PersonalInfoPage personalInfo;

		if(createAccount.isValidEmail(email)) {
			personalInfo = createAccount.signUp(email);
		}else {
			throw new RuntimeException("Invalid email address entered.");
		}

		return personalInfo.enterPersonalInfo(info)
				.enterAddressDetails(addressDetails)
				.enterOtherDetails(otherDetails)
				.register();
	}

	public AddressPage login(String email, String password) {
        return login.signInAsExistingUserDuringCheckout(email, password);
	}
}
