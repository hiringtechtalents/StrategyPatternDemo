package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        By uniqueElement = By.cssSelector(".order-confirmation");
        wait.until(ExpectedConditions.visibilityOfElementLocated(uniqueElement));
        return uniqueElement;
    }

    public boolean isOrderConfirmed() {
        return (confirmationText.getText().contains("Your order on My Store is complete."));
    }
}
