package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePage;
import com.vryba.selenium.pageObjects.NavigationBar;
import com.vryba.selenium.pageObjects.QuestionsPage;
import com.vryba.selenium.utilities.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationBar_TC extends TestBase{
    private Logger LOG = LogManager.getLogger(NavigationBar_TC.class);
    @Test
    public void checkActiveNavBarListItem() {
        LOG.info("Start Test: checkActiveNavBarListItem");
        NavigationBar navbar = new NavigationBar();
        Assert.assertEquals(navbar.questionsNavBarListItem.getAttribute("class"), "-item");
        navbar.questionsNavBarListItem.click();
        Assert.assertEquals(navbar.questionsNavBarListItem.getAttribute("class"), "-item _current");
        LOG.info("Questions tab is active");
    }
}
