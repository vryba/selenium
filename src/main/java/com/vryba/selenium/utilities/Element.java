package com.vryba.selenium.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class for WebElement manipulation
 */
public class Element {
    public static final long IMPLICITLY_WAIT_TIMEOUT = 3;
    private final String TEXT_CONTENT_ATTRIBUTE = "textContent";
    private final String ERROR_WEB_ELEMENT_NOT_VISIBLE = "! ERROR\tWeb element is invisible. Location: %s";
    private final String ERROR_WEB_ELEMENT_NOT_FOUND = "! ERROR\tWeb element is missing. Location: %s";
    private final By locator;
    private WebElement webElement;
    private Logger LOG = LogManager.getLogger(Element.class);

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
     * Constructor
     *
     * @param locator
     */
    private Element(By locator) {
        this.locator = locator;
        try {
            this.webElement = BrowserFactory.getWebDriver().findElement(locator);
        } catch (NoSuchElementException e) {
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
     * Clicks on an element only if it is available on a page
     *
     * @return
     */
    public Element click() {
        LOG.info("Clicked on element found by ("+locator.toString()+")");
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
        LOG.info("Move mouse cursor to an element located at x,y coordinate and click");
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
        LOG.info("Clear than Enter text into the element found by ("+locator.toString()+")");
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
        LOG.info("Add text to element found by ("+locator.toString()+")");
        getVisibleWebElement().sendKeys(text);
        return this;
    }

    /**
     * Returns visible text value of this element
     *
     * @return text
     */
    public String getText() {
        LOG.info("Getting displayed text of element found by ("+locator.toString()+")");
        return getVisibleWebElement().getText();
    }

    /**
     * Returns containing text value of invisible element
     *
     * @return
     */
    public String getContainingText() {
        LOG.info("Getting text of invisible element found by ("+locator.toString()+")");
        return getPresentWebElement().getAttribute(TEXT_CONTENT_ATTRIBUTE);
    }

    /**
     * Returns given attribute value of this element
     *
     * @param attribute
     * @return string
     */
    public String getAttribute(String attribute) {
        LOG.info("Getting attribute value of element found by ("+locator.toString()+")");
        return getPresentWebElement().getAttribute(attribute);
    }

    /**
     * Check is this element visibility match the given value
     *
     * @param isVisible
     * @return
     */
    public Boolean isVisibility(Boolean isVisible) {
        LOG.info("Checking if element visibility matches the given value");
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
        LOG.info("Checking if element presence matches the given value");
        Boolean presenceResult;
        if (isVisible) {
            presenceResult = waitForPresence() != null;
        } else {
            presenceResult = waitForStaleness();
        }
        return presenceResult;
    }

    /**
     * Expectation for checking that an element is present in the DOM of a page
     * and visible.
     *
     * @return
     */
    private WebElement getVisibleWebElement() {
        LOG.info("Checking if Expected element present in DOM and visible");
        WebElement webelement = waitForVisibility();
        if (webelement == null) {
            throw new RuntimeException(String.format(ERROR_WEB_ELEMENT_NOT_VISIBLE, locator));
        }
        return webelement;
    }

    /**
     * Expectation for checking that an element is present in the DOM of a page
     * and visibility isn't require.
     *
     * @return
     */
    private WebElement getPresentWebElement() {
        LOG.info("Checking if Expected element present in DOM, not necessarily visible");
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
        LOG.info("Wait until element becomes visible");
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getWebDriver(), IMPLICITLY_WAIT_TIMEOUT);
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
        LOG.info("Wait until element becomes visible");
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
        LOG.info("Wait until element becomes visible");
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
        LOG.info("Wait for element staleness");
        if (webElement != null) {
            return new WebDriverWait(BrowserFactory.getWebDriver(), IMPLICITLY_WAIT_TIMEOUT)
                    .until(ExpectedConditions.stalenessOf(webElement));
        } else {
            return true;
        }
    }

    /**
     *  Move mouse to WebElement
     *
     * @return
     */
    public void mouseOver(){

        Actions act = new Actions(BrowserFactory.getWebDriver());
        act.moveToElement(waitForVisibility()).build().perform();
    }

    /**
     * Mouse drag and drop
     *
     * @return
     */
    public void mouseDragAndDrop(Element destination){

        new Actions(BrowserFactory.getWebDriver())
                .moveToElement(waitForVisibility())
                .clickAndHold()
                .moveToElement(destination.getWebElement())
                .release().build().perform();
    }

    /**
     * Return instance of an WebElement
     *
     * @return
     */
    protected WebElement getWebElement() {
        return waitForPresence();
    }
}

/*
    public boolean isTextPresentInElementLocated(final By locator, final String text) {
        boolean result = true;
        String elementText = Element.find(locator).getText();
        return elementText.contains(text);
    }

    public String toString() {
        return String.format("text ('%s') to be present in element found by %s", text, locator);
    }*/