package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePage;
import com.vryba.selenium.utilities.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Home_Page_TC extends TestBase {

    private boolean isStackBoxDisplayed = true;
    private Logger LOG = LogManager.getLogger(Home_Page_TC.class);

    @Test
    public void loginWarningMessage() {
        LOG.info("Start Test: loginWarningMessage");
        //An object is created for the home page using the HomePage class
        HomePage homePage = new HomePage();
        //The homePage object uses the askButtonClick() method to open the spage
        String warningText = homePage
                .askQButtonClick()
                .getLoginWarningText();
        Assert.assertEquals(warningText, "You must be logged in to ask a question on Stack Overflow");
    }

    public void capsMessage() {
        HomePage homePage = new HomePage();
        String capsNoteText = homePage
                .loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "CAPS")
                .getCapsLockMessageText();
        Assert.assertEquals(capsNoteText, "Caps lock is on");
    }
}
