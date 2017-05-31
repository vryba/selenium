package com.vryba.selenium.loginTest;

import com.vryba.selenium.pageObjects.HomePO;
import com.vryba.selenium.utilities.BrowserFactory;
import com.vryba.selenium.utilities.BrowserUtilities;
import org.testng.annotations.*;
import static org.testng.Assert.fail;

public class SearchTest {

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    //DOMConfigurator.configure("log4j.xml");
    //Log.startTestCase("Selenium_Test_001");

    @BeforeClass(alwaysRun = true)
    public void setUp(){baseUrl = "https://stackoverflow.com/";
    }
    @BeforeMethod
    public void methodSetup(){
        BrowserFactory.startBrowser(BrowserFactory.BrowserType.FirefoxDriver, baseUrl);
    }
    @AfterMethod
    public void testCleanUp(){
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
    public void checkSearchFunctioanality(){

        HomePO homePO = new HomePO();
        homePO.insertStringInSearchField("Selenium WebDriver");
    }
}
