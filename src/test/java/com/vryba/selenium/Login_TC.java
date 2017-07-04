package com.vryba.selenium;

import com.vryba.selenium.dataProvider.UsersData;
import com.vryba.selenium.pageObjects.HomePage;
import com.vryba.selenium.pageObjects.LoginPage;
import com.vryba.selenium.utilities.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_TC extends TestBase {

    private Logger LOG = LogManager.getLogger(Login_TC.class);

    @Test
    public void activeTabsCheck() {
        LOG.info("Start Test: activeTabsCheck");
        HomePage homePage = new HomePage();
        String activeTabCaptionValue = homePage
                .loginButtonClick()
                .getActiveTabCaptionValue();
        Assert.assertEquals(activeTabCaptionValue, "Log in");
    }

    @Test
    public void emptyEmail() {
        LOG.info("Start Test: emptyEmail");
        HomePage homePage = new HomePage();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials(new UsersData("emptyEmail"))
                .submitButtonClick()
                .isErrorDisplayed(LoginPage.EMPTY_EMAIL);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void emptyPassword() {
        LOG.info("Start Test: emptyPassword");
        HomePage homePage = new HomePage();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("you@me.me", "")
                .submitButtonClick()
                .isErrorDisplayed(LoginPage.EMPTY_PASSWORD);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void invalidEmail() {
        LOG.info("Start Test: envalidEmail");
        HomePage homePage = new HomePage();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("wrongEmail", "correctPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPage.INVALID_EMAIL);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void capsMessage() {
        LOG.info("Start Test: capsMessage");
        HomePage homePage = new HomePage();
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
        HomePage homePage = new HomePage();
        boolean isErrorDisplayed = homePage
                .loginButtonClick()
                .enterCredentials("vryba@comcast.net", "wrongPass")
                .submitButtonClick()
                .isErrorDisplayed(LoginPage.INVALID_PASSWORD);
        Assert.assertTrue(isErrorDisplayed);
        LOG.info("Unsuccessful Login as expected");
    }

    @Test
    public void validCredentialsCheck() {
        LOG.info("Start Test: validCredentialsCheck");
        HomePage homePage = new HomePage();
        homePage.loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "abc12345")
                .submitButtonClick();
        Assert.assertEquals("vryba", homePage.getAvatarTitle());
        LOG.info("Successful Login");
    }
}