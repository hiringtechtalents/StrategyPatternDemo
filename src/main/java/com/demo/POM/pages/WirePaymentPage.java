package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by SANDEEP on 1/4/2016.
 */
public class WirePaymentPage extends BasePageObject implements PaymentMethodStrategy {

    @FindBy(css = "button.btn-default.button-medium")
    private WebElement confirmOrder;

    public WirePaymentPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        return By.xpath("//h3[contains(text(), 'Bank-wire')]");
    }

    @Override
    public OrderConfirmationPage confirmOrder() {
        this.confirmOrder.click();

        return new OrderConfirmationPage(driver);
    }
}
