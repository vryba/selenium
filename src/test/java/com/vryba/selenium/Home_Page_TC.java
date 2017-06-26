package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.utilities.BrowserFactory;
import com.vryba.selenium.utilities.BrowserUtilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class Home_Page_TC {

        private String baseUrl = "https://stackoverflow.com/";
        private boolean isStackBoxDisplayed = true;
        private StringBuffer verificationErrors = new StringBuffer();
        private Logger LOG = LogManager.getLogger(Home_Page_TC.class);

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
        public void checkDismissButton() {
            LOG.info("Start Test: checkDismissButton");
            HomePO homePage = new HomePO();
            homePage.dismissButtonClick();
            Assert.assertTrue(isStackBoxDisplayed);
        }

        @Test
        public void loginWarningMessage() {
            LOG.info("Start Test: loginWarningMessage");
            HomePO homePage = new HomePO();
            String warningText = homePage
                    .askQButtonClick()
                    .getLoginWarningText();
            Assert.assertEquals(warningText,"You must be logged in to ask a question on Stack Overflow" );
        }

    public void capsMessage() {
        HomePO homePage = new HomePO();
        String capsNoteText = homePage
                .loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "CAPS")
                .getCapsLockMessageText();
        Assert.assertEquals(capsNoteText, "Caps lock is on");
        }
}
