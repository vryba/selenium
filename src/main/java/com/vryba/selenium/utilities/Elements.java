package com.vryba.selenium.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for WebElements (List of WebElements) manipulating
 */
public class Elements {
    private final String TEXT_CONTENT_ATTRIBUTE = "textContent";
    private final By locator;

    /**
     * Returns an instance of Elements by given locator
     *
     * @param locator
     * @return
     */
    public static Elements find(By locator) {
        return new Elements(locator);
    }

    /**
     * constructor
     *
     * @param locator
     */
    private Elements(By locator) {
        this.locator = locator;
    }

    /**
     * Returns an instance of Element by given index
     *
     * @param index
     * @return
     */
    public Element get(int index) {
        ArrayList<Element> elementsList = new ArrayList<Element>();
        for (WebElement element : getWebElements()) {
            elementsList.add(new Element(element));
        }
        return elementsList.get(index);
    }

    /**
     * Returns an array of this elements visible text values
     *
     * @return
     */
    public ArrayList<String> getTextList() {
        ArrayList<String> result = new ArrayList<String>();
        for (WebElement element : getWebElements()) {
            result.add(element.getText());
        }
        return result;
    }

    /**
     * Returns an array of this elements containing text values
     *
     * @return
     */
    public ArrayList<String> getContainingTextList() {
        ArrayList<String> result = new ArrayList<String>();
        for (WebElement element : getWebElements()) {
            result.add(element.getAttribute(TEXT_CONTENT_ATTRIBUTE));
        }
        return result;
    }

    /**
     * Check if all elements of the array contain expected text value
     *
     * @return
     */
    public boolean isStringHasText(String text) {
        boolean result = true;
        for (WebElement element : getWebElements()) {
            result = element.getText().toLowerCase().contains(text.toLowerCase()) && result;
        }
        return result;
    }

    /**
     * Returns an array of this elements given attribute values
     *
     * @param attribute
     * @return
     */
    public ArrayList<String> getAttributesList(String attribute) {
        ArrayList<String> result = new ArrayList<String>();
        for (WebElement element : getWebElements()) {
            result.add(element.getAttribute(attribute));
        }
        return result;
    }

    /**
     * Expectation for checking that an elements is present on the DOM of a page
     * and visible.
     *
     * @return
     */
    private List<WebElement> getWebElements() {
        new WebDriverWait(BrowserFactory.getWebDriver(), Element.IMPLICITLY_WAIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        return BrowserFactory.getWebDriver().findElements(locator);
    }
}
