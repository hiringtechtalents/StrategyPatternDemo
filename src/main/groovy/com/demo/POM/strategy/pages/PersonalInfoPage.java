/**
 * 
 */
package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import com.demo.POM.strategy.util.KEYS;
import com.demo.POM.strategy.util.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SANDEEP
 *
 */
public class PersonalInfoPage extends BasePageObject {
	// various Personal Info fields
    @FindBy(id="id_gender1")
    private WebElement titleMr;
    @FindBy(id="id_gender2")
    private WebElement titleMrs;
    @FindBy(id="customer_firstname")
    private WebElement firstName;
    @FindBy(id="customer_lastname")
    private WebElement lastName;
    @FindBy(id="passwd")
    private WebElement password;
    @FindBy(id="days")
    private WebElement dayDOB;
    @FindBy(id="months")
    private WebElement monthDOB;
    @FindBy(id="years")
    private WebElement yearDOB;

    // various address fields
    @FindBy(id="firstname")
    private WebElement addressFirstName;
    @FindBy(id="lastname")
    private WebElement addressLastName;
    @FindBy(id="company")
    private WebElement addressCompany;
    @FindBy(id="address1")
    private WebElement addressAddress1;
    @FindBy(id="address2")
    private WebElement addressAddress2;
    @FindBy(id="city")
    private WebElement addressCity;
    @FindBy(id="id_state")
    private WebElement addressState;
    @FindBy(id="postcode")
    private WebElement addressZip;
    @FindBy(id="id_country")
    private WebElement addressCountry;

    @FindBy(id="other")
    private WebElement additionalInfo;
    @FindBy(id="phone")
    private WebElement homePhone;
    @FindBy(id="phone_mobile")
    private WebElement mobilePhone;
    @FindBy(id="alias")
    private WebElement addressAlias;

    @FindBy(id="submitAccount")
    private WebElement submit;

    public static Map<String, String> personalInfo = new HashMap<>();
    public static Map<String, String> addressInfo = new HashMap<>();
    public static Map<String, String> otherInfo = new HashMap<>();

    public enum GENDER {
        MR, MRS
    }

    /**
	 * @param driver
	 */
	public PersonalInfoPage(WebDriver driver) {
		super(driver);
		
		PageFactory.initElements(driver, this);
	}

	/* (non-Javadoc)
	 * @see BasePageObject#getUniqueElement()
	 */
	@Override
	protected By getUniqueElement() {
        new WebDriverWait(driver,10).until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("#account-creation_form > div:nth-child(1) > h3")));
		return By.cssSelector("#account-creation_form > div:nth-child(1) > h3");
	}

    public PersonalInfoPage enterPersonalInfo(Map<String, String> personalInfo) {
        // if the title is passed as "Mr" then click on the radio button for "Mr"
        // else click on radio button for "Mrs"
        if(personalInfo.get(KEYS.TITLE.name()).equalsIgnoreCase(GENDER.MR.name())){titleMr.click();}
        else{titleMrs.click();}
        util.enterText(firstName, personalInfo.get(KEYS.FNAME.name()));
        util.enterText(lastName, personalInfo.get(KEYS.LNAME.name()));
        util.enterText(password, personalInfo.get(KEYS.PASSWORD.name()));
        util.selectByValue(dayDOB, personalInfo.get(KEYS.DAY.name()));
        util.selectByText(monthDOB, personalInfo.get(KEYS.MONTH.name()));
        util.selectByValue(yearDOB, personalInfo.get(KEYS.YEAR.name()));
        return this;
    }

    public PersonalInfoPage enterAddressDetails(Map<String, String> addressInfo) {
        if(addressFirstName.getAttribute("value").trim().isEmpty() || !(addressFirstName.getAttribute("value")
                .equalsIgnoreCase(personalInfo.get(KEYS.FNAME.name())))) {
            util.enterText(addressFirstName, addressInfo.get(KEYS.FIRSTNAME.name()));
        }
        if((addressLastName.getAttribute("value").trim().isEmpty()) || !addressLastName.getAttribute("value")
                .equalsIgnoreCase(personalInfo.get(KEYS.LNAME.name()))) {
            util.enterText(addressLastName, addressInfo.get(KEYS.LASTNAME.name()));
        }
        // check if the map contains K,V for company then enter the text as the field is not compulsory
        if(addressInfo.containsKey(KEYS.COMPANY.name())) util.enterText(addressCompany,
                addressInfo.get(KEYS.COMPANY.name()));
        util.enterText(addressAddress1, addressInfo.get(KEYS.ADDRESS.name()));
        // check if the map contains K,V for address2 then enter the text as the field is not compulsory
        if(addressInfo.containsKey(KEYS.ADDRESS2.name())) util.enterText(addressAddress2,
                addressInfo.get(KEYS.ADDRESS2.name()));
        util.enterText(addressCity, addressInfo.get(KEYS.CITY.name()));
        util.selectByText(addressState, addressInfo.get(KEYS.STATE.name()));
        util.enterText(addressZip, addressInfo.get(KEYS.ZIP.name()));
        if(addressInfo.containsKey(KEYS.COUNTRY.name()))util.selectByText(addressCountry,
                addressInfo.getOrDefault(KEYS.COUNTRY.name(), "United States"));
        return this;
    }

    public PersonalInfoPage enterOtherDetails(Map<String, String> otherInfo) {
        if(otherInfo.containsKey(KEYS.MOREINFO.name())) util.enterText(additionalInfo,
                otherInfo.get(KEYS.MOREINFO.name()));
        if(otherInfo.containsKey(KEYS.HOMEPHONE.name())) util.enterText(homePhone,
                otherInfo.get(KEYS.HOMEPHONE.name()));
        if(otherInfo.containsKey(KEYS.MOBILE.name())) util.enterText(mobilePhone, otherInfo.get(KEYS.MOBILE.name()));
        util.enterText(addressAlias, otherInfo.get(KEYS.ALIAS.name()));
        return this;
    }

    public AddressPage register() {
        submit.click();

        return PageFactory.initElements(driver, AddressPage.class);
    }
}