package com.vryba.selenium.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for WebElement manipulating
 */
public class Element {
    public static final long IMPLICITLY_WAIT_TIMEOUT = 3;
    private final String TEXT_CONTENT_ATTRIBUTE = "textContent";
    private final String ERROR_WEB_ELEMENT_NOT_VISIBLE = "! ERROR\tWeb element is invisible. Location: %s";
    private final String ERROR_WEB_ELEMENT_NOT_FOUND = "! ERROR\tWeb element is missing. Location: %s";
    private final By locator;
    private WebElement webElement;

    /**
     * Returns an instance of Element by given locator
     *
     * @param locator
     * @return
     */
    public static Element find(By locator) {
        return new Element(locator);
    }

    /**
     * Returns an instance of Elements by given locator
     *
     * @param locator
     * @return
     */
    public static Elements findAll(By locator) {
        return new Elements(locator);
    }

    /**
     * Constructor
     *
     * @param locator
     */
    private Element(By locator) {
        this.locator = locator;
        try {
            this.webElement = BrowserFactory.getWebDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println(String.format(ERROR_WEB_ELEMENT_NOT_FOUND, locator));
            this.webElement = null;
        }
    }

    /**
     * Constructor
     *
     * @param webElement
     */
    protected Element(WebElement webElement) {
        this.webElement = webElement;
        this.locator = null;
    }

    /**
     * Click on this element
     *
     * @return
     */
    public Element click() {
        getVisibleWebElement().click();
        return this;
    }

    /**
     * Click on this element by coordinates
     *
     * @param xOffset
     * @param yOffset
     * @return
     */
    public Element click(int xOffset, int yOffset) {
        Actions actions = new Actions(BrowserFactory.getWebDriver());
        actions.moveToElement(getVisibleWebElement(), xOffset, yOffset).build().perform();
        return this;
    }

    /**
     * Set given text value to this element
     *
     * @param text
     */
    public Element sendKeys(String text) {
        WebElement inputField = getVisibleWebElement();
        inputField.clear();
        inputField.sendKeys(text);
        return this;
    }

    /**
     * Add given text value to this element
     *
     * @param text
     */
    public Element addKeys(String text) {
        getVisibleWebElement().sendKeys(text);
        return this;
    }

    /**
     * Returns visible text value of this element
     *
     * @return text
     */
    public String getText() {
        return getVisibleWebElement().getText();
    }

    /**
     * Returns containing text value of invisible element
     *
     * @return
     */
    public String getContainingText() {
        return getPresentWebElement().getAttribute(TEXT_CONTENT_ATTRIBUTE);
    }

    /**
     * Returns given attribute value of this element
     *
     * @param attribute
     * @return string
     */
    public String getAttribute(String attribute) {
        return getPresentWebElement().getAttribute(attribute);
    }

    /**
     * Check is this element visibility match the given value
     *
     * @param isVisible
     * @return
     */
    public Boolean isVisibility(Boolean isVisible) {
        Boolean visibilityResult;
        if (isVisible) {
            visibilityResult = waitForVisibility() != null;
        } else {
            visibilityResult = waitForInvisibility();
        }
        return visibilityResult;
    }

    /**
     * Check is this element presence match the given value
     *
     * @param isVisible
     * @return
     */
    public Boolean isPresence(Boolean isVisible) {
        Boolean presenceResult;
        if (isVisible) {
            presenceResult = waitForPresence() != null;
        } else {
            presenceResult = waitForStaleness();
        }
        return presenceResult;
    }

    /**
     * Expectation for checking that an element is present on the DOM of a page
     * and visible.
     *
     * @return
     */
    private WebElement getVisibleWebElement() {
        WebElement webelement = waitForVisibility();
        if (webelement == null) {
            throw new RuntimeException(String.format(ERROR_WEB_ELEMENT_NOT_VISIBLE, locator));
        }
        return webelement;
    }

    /**
     * Expectation for checking that an element is present on the DOM of a page
     * and visibility isn't require.
     *
     * @return
     */
    private WebElement getPresentWebElement() {
        WebElement webelement = waitForPresence();
        if (webelement == null) {
            throw new RuntimeException(String.format(ERROR_WEB_ELEMENT_NOT_FOUND, locator));
        }
        return webelement;
    }

    /**
     * Wait for element visibility if locator provided
     *
     * @return
     */
    private WebElement waitForVisibility() {
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getWebDriver(),
                IMPLICITLY_WAIT_TIMEOUT);
        waitForPresence();
        if (locator != null) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } else {
            return wait.until(ExpectedConditions.visibilityOf(webElement));
        }
    }

    /**
     * Wait for element invisibility if locator provided
     *
     * @return
     */
    private Boolean waitForInvisibility() {
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getWebDriver(), IMPLICITLY_WAIT_TIMEOUT);
        waitForPresence();
        if (locator != null) {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } else {
            return wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
        }
    }

    /**
     * Wait for element presence if locator provided
     *
     * @return
     */
    private WebElement waitForPresence() {
        if (locator != null) {
            return new WebDriverWait(BrowserFactory.getWebDriver(), IMPLICITLY_WAIT_TIMEOUT)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } else {
            return webElement;
        }
    }

    /**
     * Wait for element staleness
     *
     * @return
     */
    private Boolean waitForStaleness() {
        if (webElement != null) {
            return new WebDriverWait(BrowserFactory.getWebDriver(), IMPLICITLY_WAIT_TIMEOUT)
                    .until(ExpectedConditions.stalenessOf(webElement));
        } else {
            return true;
        }
    }
}
