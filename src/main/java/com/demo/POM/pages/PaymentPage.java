package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by SANDEEP on 1/4/2016.
 */
public class PaymentPage extends BasePageObject {
    @FindBy(css = ".bankwire")
    private WebElement payByBankWire;

    @FindBy(css = ".cheque")
    private WebElement payByCheque;

    public enum PAYMENTMODE {
        BANKWIRE, CHEQUE
    }

    public PaymentPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cheque")));
        return By.cssSelector("a.cheque");
    }

    private WirePaymentPage makePaymentByWire() {
        payByBankWire.click();
        return new WirePaymentPage(driver);
    }

    private ChequePaymentPage makePaymentByCheque() {
        payByCheque.click();

        return new ChequePaymentPage(driver);
    }

    public <T extends BasePageObject> T makePaymentBy(PAYMENTMODE paymentMode, Class<T> clazz) {
        if(paymentMode.name().equalsIgnoreCase("cheque")) {
            return (clazz.cast(makePaymentByCheque()));
        } else if(paymentMode.name().equalsIgnoreCase("BANKWIRE")) {
            return (clazz.cast(makePaymentByWire()));
        }
        return null;
    }
}
