package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by SANDEEP on 1/4/2016.
 */
public class ChequePaymentPage extends BasePageObject implements PaymentMethodStrategy {

    @FindBy(css = "button.btn-default.button-medium")
    private WebElement confirmOrder;

    public ChequePaymentPage(WebDriver driver) {
        super(driver);

        // not required as using this in the method creating the ChequePaymentPage object
        //PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        By uniqueElement = By.xpath("//h3[contains(text(), 'Check')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(uniqueElement));
        return uniqueElement;
    }

    @Override
    public OrderConfirmationPage confirmOrder() {
        this.confirmOrder.click();

        return new OrderConfirmationPage((WebDriver) driver);
    }
}
