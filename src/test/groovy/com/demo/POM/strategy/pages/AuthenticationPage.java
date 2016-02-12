/**
 * 
 */
package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


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
		
		login = PageFactory.initElements(driver, LoginPage.class);
	}

	/*
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
		uniqueElement = By.cssSelector("#columns > .breadcrumb.clearfix > .navigation_page");
		return (By) uniqueElement;
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
        return login.signIn(email, password, AddressPage.class);
	}
}
