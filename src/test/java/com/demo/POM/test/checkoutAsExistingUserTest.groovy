package com.demo.POM.test

import com.demo.POM.BaseTest
import com.demo.POM.pages.ChequePaymentPage
import com.demo.POM.pages.HomePage
import com.demo.POM.pages.PaymentPage
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
/**
 * Created by SANDEEP on 1/6/2016.
 */
class checkoutAsExistingUserTest extends BaseTest {
    private def cart

    private def personalInfo, addressInfo, otherDetails

    checkoutAsExistingUserTest() {super()}

    @BeforeMethod
    def doTestSetup() {
        super.beforeMethod();

        cart = new HomePage(driver).navigateToMainCategoryPage().clickOnProduct().addProductToCart().navigateToCart()

        setupPersonalInfo();
        setupAddressInfo();
        setupOtherInfo();
    }

    private def setupPersonalInfo() {
        personalInfo = ["KEYS.TITLE.name()":"Mr",
                        "KEYS.FNAME.name()":"Automation",
                        "KEYS.LNAME.name()":"Practice",
                        "KEYS.PASSWORD.name()":"test1234",
                        "KEYS.DAY.name()":"12",
                        "KEYS.MONTH.name()":"March",
                        "KEYS.YEAR.name()":"1967"]
    }

    private def setupAddressInfo() {
        addressInfo = ["KEYS.ADDRESS.name()":"4175 Admirality Way", "KEYS.CITY.name()":"Marina del Rey",
                       "KEYS.STATE.name()":"California", "KEYS.ZIP.name()":"90292"]
    }

    private def setupOtherInfo() {
        otherDetails = ["KEYS.HOMEPHONE.name()":"310886659", "KEYS.ALIAS.name()":"MyTestAddress1"]

        //otherDetails << ["KEYS.MOREINFO.name()", ""];
        //otherDetails << ["KEYS.MOBILE.name()", ""];
    }

    @Test
    def testCheckoutAsNewUserChequePayment() {
        // initialize objects
        def shippingPage

        def addressPage = cart.toAuthenticationPage().login("testautomation.practice1@yopmail.com", "test1234")

        if(addressPage.validateAddress("delivery") && addressPage.validateAddress("billing"))
            shippingPage = addressPage.submit()

        def payment = shippingPage.selectTerms().navigateToPaymentPage()
                .makePaymentBy(PaymentPage.PAYMENTMODE.CHEQUE, ChequePaymentPage.class)

        if(payment.confirmOrder().isOrderConfirmed())
            println"Order Confirmed. Test Successful!!!!!"
    }
}