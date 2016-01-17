package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by SANDEEP on 1/4/2016.
 */
public class ShippingPage extends BasePageObject {
    @FindBy(id = "cgv")
    private WebElement chkboxTermsConditions;

    @FindBy(className = "delivery_option_radio")
    private WebElement deliveryOptionRadio;

    @FindBy(css =  "button.button:nth-child(4)")
    private WebElement toPayment;

    public ShippingPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        return By.id("uniform-cgv");
    }

    public ShippingPage selectTerms() {
        if(!chkboxTermsConditions.isSelected())
            chkboxTermsConditions.click();

        return this;
    }

    public PaymentPage navigateToPaymentPage() {
        //chkboxTermsConditions.submit();
        toPayment.click();

        return new PaymentPage((WebDriver) driver);
    }
}
