package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.utilities.BrowserFactory;
import com.vryba.selenium.utilities.BrowserUtilities;
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
    //DOMConfigurator.configure("log4j2.xml");
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
    public void checkSearchFunctioanality() {
        String searchText="WebDriver";
        HomePO homePO = new HomePO();
        boolean searchResult = homePO.insertStringInSearchField(searchText).areListItemsHaveText(searchText);
        assertTrue(searchResult);
    }
}
