package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePage;
import com.vryba.selenium.utilities.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Search_TC extends TestBase{

    //private boolean acceptNextAlert = true;
    private Logger LOG = LogManager.getLogger(Search_TC.class);

    @Test
    public void checkSearchFunctionality() {
        LOG.info("Start Test: checkSearchFunctionality");
        String searchText="WebDriver";
        HomePage homePage = new HomePage();
        boolean searchResult = homePage.insertStringInSearchField(searchText).areListItemsHaveText(searchText);
        Assert.assertTrue(!searchResult);
    }
}
