package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import groovy.util.ConfigObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by SANDEEP on 1/6/2016.
 */
public class OrderConfirmationPage extends BasePageObject {
    @FindBy(css = ".box")
    private WebElement confirmationText;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
        uniqueElement = By.cssSelector(".order-confirmation");
        // first reset the implicitwait to 0
        ((WebDriver)driver).manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        // then use wait for a specific element using WebDriverWait
        wait.until(ExpectedConditions.visibilityOfElementLocated((By) uniqueElement));
        // then set the implicitwait back to the required time.
        ((WebDriver)driver).manage().timeouts().implicitlyWait(
                new Long(((ConfigObject)config).get("IMPLICITWAIT_TIMEOUT").toString()), TimeUnit.SECONDS);

        return (By) uniqueElement;
    }

    public boolean isOrderConfirmed() {
        return (confirmationText.getText().contains("Your order on My Store is complete."));
    }
}
