package com.vryba.selenium.loginTest;

import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.pageObjects.LoginPO;
import com.vryba.selenium.utilities.BrowserFactory;
import com.vryba.selenium.utilities.BrowserUtilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest {
    private String baseUrl = "https://stackoverflow.com/";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    //DOMConfigurator.configure("log4j.xml");
    //Log.startTestCase("Selenium_Test_001");

    @BeforeMethod
    public void methodSetup() {
        BrowserFactory.startBrowser(BrowserFactory.BrowserType.FirefoxDriver, baseUrl);
    }

    @AfterMethod
    public void testCleanUp() {
        new BrowserUtilities().executeJSCode(BrowserUtilities.CLEAR_LOCAL_STORAGE);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        BrowserFactory.closeSession();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Test
    public void emptyEmail() {
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("", "wrongPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.EMPTY_EMAIL);
        assertTrue(isErrorDisplayed);
    }

    @Test
    public void emptyPassword() {
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("you@me.me", "")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.EMPTY_PASSWORD);
        assertTrue(isErrorDisplayed);
    }

    @Test
    public void invalidEmail() {
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("wrongEmail", "correctPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.INVALID_EMAIL);
        assertTrue(isErrorDisplayed);
    }

    @Test
    public void capsMessage() {
        HomePO homePage = new HomePO();
        String capsNoteText = homePage
                .loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "CAPS")
                .getCapsLockMessageText();
        assertEquals(capsNoteText, "Caps lock is on");
    }

    @Test
    public void invalidPassword() {
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("vryba@comcast.net", "wrongPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.INVALID_PASSWORD);
        assertTrue(isErrorDisplayed);
    }

    @Test
    public void validCredentialsCheck() {
        HomePO homePage = new HomePO();
        homePage.loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "abc12345")
                .submitButtonClick();
        assertEquals("vryba", homePage.getAvatarTitle());
    }
}