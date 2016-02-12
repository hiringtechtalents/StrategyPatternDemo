package com.demo.POM.strategy.test

import com.demo.POM.strategy.base.BaseTest
import com.demo.POM.strategy.pages.*
import com.demo.POM.strategy.util.KEYS
import com.demo.POM.strategy.util.util
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

public class checkoutAsNewUserTest extends BaseTest {
    private def cart

    public checkoutAsNewUserTest() {super()}

    @BeforeMethod
    public void doTestSetup() {
        super.beforeMethod()

        cart = new HomePage(driver).navigateToMainCategoryPage().clickOnProduct().addProductToCart().navigateToCart()

        setupPersonalInfo()
        setupAddressInfo()
        setupOtherInfo()
    }

    private void setupPersonalInfo() {
        PersonalInfoPage.personalInfo.put(KEYS.TITLE.name(), "MR")
        PersonalInfoPage.personalInfo.put(KEYS.FNAME.name(), "Automation")
        PersonalInfoPage.personalInfo.put(KEYS.LNAME.name(),"Practice")
        PersonalInfoPage.personalInfo.put(KEYS.PASSWORD.name(),"test1234")
        PersonalInfoPage.personalInfo.put(KEYS.DAY.name(),"12")
        PersonalInfoPage.personalInfo.put(KEYS.MONTH.name(),"March")
        PersonalInfoPage.personalInfo.put(KEYS.YEAR.name(),"1967")
    }

    private void setupAddressInfo() {
        PersonalInfoPage.addressInfo.put(KEYS.ADDRESS.name(),"4175 Admirality Way")
        PersonalInfoPage.addressInfo.put(KEYS.CITY.name(),"Marina del Rey")
        PersonalInfoPage.addressInfo.put(KEYS.STATE.name(),"California")
        PersonalInfoPage.addressInfo.put(KEYS.ZIP.name(),"90292")
    }

    private void setupOtherInfo() {
        PersonalInfoPage.otherInfo.put(KEYS.HOMEPHONE.name(),"310886659")
        PersonalInfoPage.otherInfo.put(KEYS.ALIAS.name(),"MyTestAddress1")

        //PersonalInfoPage.otherInfo << ["KEYS.MOREINFO.name()", ""]
        //PersonalInfoPage.otherInfo << ["KEYS.MOBILE.name()", ""]
    }

	@Test
	public void testCheckoutAsNewUserWirePayment() {
        // initialize objects
        ShippingPage shippingPage = null

        AddressPage addressPage = cart.toAuthenticationPage().signUp("testautomation.practice" +
                util.randomNumGenerator(0,999) + ".@yopmail.com")

        if(addressPage.validateAddress("delivery") && addressPage.validateAddress("billing"))
                shippingPage = addressPage.submit()

        PaymentMethodStrategy payment = shippingPage.selectTerms().navigateToPaymentPage()
                .makePaymentBy(WirePaymentPage.class)

        if(payment.confirmOrder().isOrderConfirmed())
            println("Order Confirmed. Test Successful!!!!!")
	}
}