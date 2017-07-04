package com.vryba.selenium;

import com.vryba.selenium.pageObjects.HomePage;
import com.vryba.selenium.utilities.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUp_TC extends TestBase{

    private Logger LOG = LogManager.getLogger(SignUp_TC.class);

    @Test
    public void activeTabsCheck(){
        LOG.info("Start Test: activeTabsCheck");
        HomePage homePage = new HomePage();
        String geActiveTabTextContent = homePage
                .signUpButtonClick()
                .getActiveTabCaptionValue();
        Assert.assertEquals(geActiveTabTextContent, "Sign up");
    }
}
