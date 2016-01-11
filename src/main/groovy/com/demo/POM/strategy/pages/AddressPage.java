package com.demo.POM.strategy.pages;

import com.demo.POM.strategy.BasePageObject;
import com.demo.POM.strategy.util.KEYS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by SANDEEP on 1/4/2016.
 */
public class AddressPage extends BasePageObject {
    @FindBy(id = "address_delivery")
    private WebElement deliveryAddress;

    @FindBy(id = "address_invoice")
    private WebElement billingAddress;

    @FindBy(css = ".cart_navigation .button.btn-default")
    private WebElement submit;

    /*@FindBy(id = "addressesAreEquals")
    private WebElement shippingSameAsBilling;

    @FindBy(css = ".address_add.submit > .button.btn-default")
    private WebElement btnAddNewAddress;

    By addNewAddress;*/

    public AddressPage(WebDriver driver) {
        super(driver);

        //PageFactory.initElements(driver, this);
    }

    @Override
    protected By getUniqueElement() {
       wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.cssSelector(".address_add.submit > a")));
        return By.cssSelector(".address_add.submit > a");
    }

    public boolean validateAddress(String whichAddress) {
        boolean nameIsGood, addressIsGood; nameIsGood = addressIsGood = false;
        if(whichAddress.equalsIgnoreCase("delivery")) {
                String strDeliveryAddress = deliveryAddress.getText();
            if(strDeliveryAddress.contains(PersonalInfoPage.personalInfo.get(KEYS.FNAME.name())) &&
                    strDeliveryAddress.contains(PersonalInfoPage.personalInfo.get(KEYS.LNAME.name()))) {
                nameIsGood = true;
            }
            if(strDeliveryAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.ADDRESS.name())) &&
                    strDeliveryAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.CITY.name())) &&
                    strDeliveryAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.STATE.name())) &&
                    strDeliveryAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.ZIP.name()))) {
                addressIsGood = true;
            }
        }
        if(whichAddress.equalsIgnoreCase("billing")) {
            String strBillingAddress = billingAddress.getText();

            if(strBillingAddress.contains(PersonalInfoPage.personalInfo.get(KEYS.FNAME.name())) &&
                    strBillingAddress.contains(PersonalInfoPage.personalInfo.get(KEYS.LNAME.name()))) {
                nameIsGood = true;
            }
            if(strBillingAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.ADDRESS.name())) &&
                    strBillingAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.CITY.name())) &&
                    strBillingAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.STATE.name())) &&
                    strBillingAddress.contains(PersonalInfoPage.addressInfo.get(KEYS.ZIP.name()))) {
                addressIsGood = true;
            }
        }

        return (nameIsGood && addressIsGood);
    }

    public ShippingPage submit() {
        submit.click();

        return new ShippingPage(driver);
    }

    /*public AddressPage unCheckShippingSameAsBilling() {
        if(shippingSameAsBilling.isSelected()) shippingSameAsBilling.click();

        addNewAddress = By.cssSelector(".button.button-small.btn.btn-default");

        super.wait.until(ExpectedConditions.elementToBeClickable(addNewAddress));

        return this;
    }*/
}
