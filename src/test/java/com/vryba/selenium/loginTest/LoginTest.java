package com.vryba.selenium.loginTest;

import com.vryba.selenium.utilities.BrowserFactory;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class LoginTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp(){
        baseUrl = "https://stackoverflow.com/";
    }
    @BeforeMethod
    public void methodSetup(){
        driver = BrowserFactory.startBrowser(BrowserFactory.BrowserType.FirefoxDriver, baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void loginButtonCheck(){
        WebElement loginButton = driver.findElement(By.xpath("//a[@class='login-link btn-clear']"));
        loginButton.click();
        WebElement activeTab = driver.findElement(By.xpath(".//*[@id='tabs']/a[@class='youarehere']"));
        String activeTabTextValue = activeTab.getText();
        assertEquals(activeTabTextValue, "Log in");
    }
    @Test
    public void invalidCredentialsCheck() {
        WebElement loginButton = driver.findElement(By.xpath("//a[@class='login-link btn-clear']"));
        loginButton.click();
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginSubmitButton = driver.findElement(By.id("submit-button"));
        emailField.clear();
        emailField.sendKeys("wrongEmail");
        passwordField.clear();
        passwordField.sendKeys("wrongPassword");
        loginSubmitButton.click();
        WebElement errMessage = driver.findElement(By.xpath("//div[text()='The email is not a valid email address.']"));
        assertTrue(errMessage.isDisplayed());
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}