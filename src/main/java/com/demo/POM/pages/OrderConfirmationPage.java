package com.demo.POM.pages;

import com.demo.POM.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return By.cssSelector("p.cheque-indent > strong");
    }

    public boolean isOrderConfirmed() {
        return (confirmationText.getText().contains("Your order on My Store is complete."));
    }
}
