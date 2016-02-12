package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.base.BasePageObject;
import groovy.util.ConfigObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by SANDEEP on 1/4/2016.
 */
public class WirePaymentPage extends BasePageObject implements PaymentMethodStrategy {

    @FindBy(css = "button.btn-default.button-medium")
    private WebElement confirmOrder;

    public WirePaymentPage(WebDriver driver) {
        super(driver);

        // not required as using this in the method creating the WirePaymentPage object
        //PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        uniqueElement = By.xpath("//h3[contains(text(), 'Bank-wire')]");

        // first reset the implicitwait to 0
        ((WebDriver)driver).manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        // then use wait for a specific element using WebDriverWait
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) uniqueElement));
        // then set the implicitwait back to the required time.
        ((WebDriver)driver).manage().timeouts().implicitlyWait(
                new Long(((ConfigObject)config).get("IMPLICITWAIT_TIMEOUT").toString()), TimeUnit.SECONDS);

        return (By) uniqueElement;
    }

    @Override
    public OrderConfirmationPage confirmOrder() {
        this.confirmOrder.click();

        return new OrderConfirmationPage((WebDriver) driver);
    }
}
