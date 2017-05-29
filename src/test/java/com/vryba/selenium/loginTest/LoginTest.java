package com.vryba.selenium.loginTest;

import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.pageObjects.LoginPO;
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
    public void setUp(){baseUrl = "https://stackoverflow.com/";
    }
    @BeforeMethod
    public void methodSetup(){
        driver = BrowserFactory.startBrowser(BrowserFactory.BrowserType.FirefoxDriver, baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void loginButtonCheck(){
        HomePO homePage = new HomePO(driver);
        String tabCaption = homePage.loginButtonClick().getActiveTabCaptionValue();
        assertEquals(tabCaption, "Log in");
    }
    @Test
    public void invalidCredentialsCheck() {
        HomePO homePage = new HomePO(driver);
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("wrongEmail", "wrongPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.INCORRECT_EMAIL);
        assertTrue(isErrorDisplayed);
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