package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePage;
import com.vryba.selenium.utilities.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Search_TC extends TestBase{

    private Logger LOG = LogManager.getLogger(Search_TC.class);

    @Test
    public void checkSearchFunctionality() {
        LOG.info("Start Test: checkSearchFunctionality");
        String searchKeyword = "Firefox WebDriver";
        HomePage homePage = new HomePage();
        boolean searchResult = homePage
                .insertStringInSearchField(searchKeyword)
                .areReturnResultsKeywordRelevant(searchKeyword);
        Assert.assertTrue(!searchResult);
    }

    @Test
    public void checkResultCountDisplayed() {
        LOG.info("Start Test: checkResultCount");
        String searchKeyword = "HEAD repository";
        HomePage homePage = new HomePage();
        boolean isTotalCountDisplayed = homePage
                .insertStringInSearchField(searchKeyword)
                .isTotalResultsCountDisplayed(searchKeyword);
        Assert.assertTrue(isTotalCountDisplayed);
    }

    @Test
    public void checkResultCountPositiveNumber() {
        LOG.info("Start Test: checkResultCountPositiveNumber");
        String searchKeyword = "GitHub repository";
        HomePage homePage = new HomePage();
        boolean isTotalResultsCountPositive = homePage
                .insertStringInSearchField(searchKeyword)
                .checkingResultsCount();
        Assert.assertTrue(isTotalResultsCountPositive);
   }
}
