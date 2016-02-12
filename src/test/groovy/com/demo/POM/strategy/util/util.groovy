package com.demo.POM.strategy.util

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
/**
 * Created by SANDEEP on 1/1/2016.
 */
public class util {

    private util() {}

    public static void enterText(WebElement textbox, String text) {
        textbox.click()
        textbox.clear()
        textbox.sendKeys(text)
    }

    public static void selectByText(WebElement element, final String textToSelect) {
        Select toSelect = new Select(element)
        List<WebElement> options = toSelect.getOptions()

        options.forEach( {
            if (textToSelect.equalsIgnoreCase(it.getText().trim())) {
                it.click()
            }
        })
    }

    public static void selectByValue(WebElement element, String valToSelect) {
        new Select(element).selectByValue(valToSelect)
    }

    /**
     * @param minimum value for the range
     * @param maximum value for the range
     * @return the random number generated within the range
     */
    public static int randomNumGenerator(int minimum, int maximum) {
        Random random = new Random()
        //int range = maximum - minimum + 1
        int range = maximum - minimum

        return random.nextInt(range + minimum)
    }
}
