package com.vryba.selenium.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.fail;

public class TestBase {

    private String baseUrl = Constant.URL;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger LOG = LogManager.getFormatterLogger();

    @BeforeMethod
    public void methodSetup() {
        BrowserFactory.startBrowser(BrowserFactory.BrowserType.FirefoxDriver, baseUrl);
        LOG.info("Starting Browser");
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
        } else
            LOG.info("Browser Closed");
    }
}
