package com.vryba.selenium.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for WebElements (List of WebElements) manipulating
 */
public class Elements {
    private final String TEXT_CONTENT_ATTRIBUTE = "textContent";
    private final By locator;

    /**
     * constructor
     *
     * @param locator
     */
    protected Elements(By locator) {
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
