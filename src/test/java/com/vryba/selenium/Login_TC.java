package com.vryba.selenium;

import com.vryba.selenium.dataProvider.UsersData;
import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.pageObjects.LoginPO;
import com.vryba.selenium.utilities.BrowserFactory;
import com.vryba.selenium.utilities.BrowserUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class Login_TC {
    private String baseUrl = "https://stackoverflow.com/";
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger LOG = LogManager.getLogger(Login_TC.class);

    @BeforeMethod
    public void methodSetup() {
        BrowserFactory.startBrowser(BrowserFactory.BrowserType.FirefoxDriver, baseUrl);
        LOG.info("Start browser");
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
        LOG.info("Closed browser");
    }

    @Test
    public void emptyEmail() {
        LOG.info("Start Test: emptyEmail");
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials(new UsersData("emptyEmail"))
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.EMPTY_EMAIL);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void emptyPassword() {
        LOG.info("Start Test: emptyPassword");
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("you@me.me", "")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.EMPTY_PASSWORD);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void invalidEmail() {
        LOG.info("Start Test: envalidEmail");
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("wrongEmail", "correctPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.INVALID_EMAIL);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void capsMessage() {
        LOG.info("Start Test: capsMessage");
        HomePO homePage = new HomePO();
        String capsNoteText = homePage
                .loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "CAPS")
                .getCapsLockMessageText();
        Assert.assertEquals(capsNoteText, "Caps lock is on");
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void invalidPassword() {
        LOG.info("Start Test: invalidPassword");
        HomePO homePage = new HomePO();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("vryba@comcast.net", "wrongPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPO.INVALID_PASSWORD);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void validCredentialsCheck() {
        LOG.info("Start Test: validCredentialsCheck");
        HomePO homePage = new HomePO();
        homePage.loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "abc12345")
                .submitButtonClick();
        Assert.assertEquals("vryba", homePage.getAvatarTitle());
        LOG.info("Successful Login");
    }
}