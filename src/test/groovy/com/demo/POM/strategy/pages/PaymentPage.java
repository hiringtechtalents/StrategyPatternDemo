package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.base.BasePageObject;
import groovy.util.ConfigObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

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
        uniqueElement = By.cssSelector("a.cheque");
        // first reset the implicitwait to 0
        ((WebDriver)driver).manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        // then use wait for a specific element using WebDriverWait
        wait.until(ExpectedConditions.elementToBeClickable((By) uniqueElement));
        // then set the implicitwait back to the required time.
        ((WebDriver)driver).manage().timeouts().implicitlyWait(
                new Long(((ConfigObject)config).get("IMPLICITWAIT_TIMEOUT").toString()), TimeUnit.SECONDS);

        return (By) uniqueElement;
    }

    public <T extends BasePageObject> T makePaymentBy(Class<T> clazz) {
        // if the passed class is of type ChequePaymentPage then click on payByCheque element
        // else click on payByBankWire element.
        if(clazz.getSimpleName().equals("ChequePaymentPage")) {
            payByCheque.click();
        }
        if(clazz.getSimpleName().equals("WirePaymentPage")) {
            payByBankWire.click();
        }
        return PageFactory.initElements((WebDriver) driver, clazz);
    }
}
