package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.utilities.BrowserFactory;
import com.vryba.selenium.utilities.BrowserUtilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class HomePageTest {

        private String baseUrl = "https://stackoverflow.com/";
        private boolean isStackBoxDisplayed = true;
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
        public void checkDismissButton() {
            HomePO homePage = new HomePO();
            homePage.dismissButtonClick();
            assertTrue(isStackBoxDisplayed);
        }

        @Test
        public void loginWarningMessage() {
            HomePO homePage = new HomePO();
            String warningText = homePage
                    .askQButtonClick()
                    .getLoginWarningText();
            System.out.println(warningText);
        }

    public void capsMessage() {
        HomePO homePage = new HomePO();
        String capsNoteText = homePage
                .loginButtonClick()
                .enterCredentials("vrybalko@comcast.net", "CAPS")
                .getCapsLockMessageText();
        assertEquals(capsNoteText, "Caps lock is on");
        }
}
