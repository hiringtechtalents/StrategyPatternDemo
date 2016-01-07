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

    public PaymentPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cheque")));
        return By.cssSelector("a.cheque");
    }

    public <T extends BasePageObject> T makePaymentBy(Class<T> clazz) {
        // if the passed class is of type ChequePaymentPage then call the appropriate method
        if(clazz.getSimpleName().equals("ChequePaymentPage")) {
            payByCheque.click();

            return clazz.cast(new ChequePaymentPage(driver));
        }
        if(clazz.getSimpleName().equals("WirePaymentPage")) {
            payByBankWire.click();

            return clazz.cast(new WirePaymentPage(driver));
        }
        return null;
    }
}
