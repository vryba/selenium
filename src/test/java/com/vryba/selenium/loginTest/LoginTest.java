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
    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    @Test
    public void loginButtonCheck(){
        HomePO homePage = new HomePO();
        String tabCaption = homePage.loginButtonClick().getActiveTabCaptionValue();
        assertEquals(tabCaption, "Log in");
    }
    @Test
    public void invalidCredentialsCheck() {
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("wrongEmail", "wrongPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.INCORRECT_EMAIL);
        assertTrue(isErrorDisplayed);
    }
}