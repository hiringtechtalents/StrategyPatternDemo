package com.demo.POM.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

/**
 * Created by SANDEEP on 1/1/2016.
 */
public class util {

    private util() {}

    public static void enterText(WebElement textbox, String text) {
        textbox.click(); textbox.clear();
        textbox.sendKeys(text);
    }

    public static void selectByText(WebElement element, final String textToSelect) {
        Select toSelect = new Select(element);
        List<WebElement> options = toSelect.getOptions();

        options.forEach((option) -> {
            if (textToSelect.equalsIgnoreCase(option.getText().trim())) {
                option.click();
            }
        });
        //new Select(element).selectByVisibleText(toSelect.trim());
    }

    public static void selectByValue(WebElement element, String valToSelect) {
        new Select(element).selectByValue(valToSelect);
    }

    /**
     * @param minimum value for the range
     * @param maximum value for the range
     * @return the random number generated within the range
     */
    public static int randomNumGenerator(int minimum, int maximum) {
        Random random = new Random();
        //int range = maximum - minimum + 1;
        int range = maximum - minimum;

        return random.nextInt(range + minimum);
    }
}
