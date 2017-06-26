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
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class Search_TC {

    private String baseUrl = "https://stackoverflow.com/";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private Logger LOG = LogManager.getLogger(Search_TC.class);

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
    public void checkSearchFunctionality() {
        LOG.info("Start Test: checkSearchFunctionality");
        String searchText="WebDriver";
        HomePO homePO = new HomePO();
        boolean searchResult = homePO.insertStringInSearchField(searchText).areListItemsHaveText(searchText);
        Assert.assertTrue(!searchResult);
    }
}
