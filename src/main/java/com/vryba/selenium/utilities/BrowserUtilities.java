package com.vryba.selenium.utilities;

import org.openqa.selenium.JavascriptExecutor;

public class BrowserUtilities {

    public static final String CLEAR_LOCAL_STORAGE = "localStorage.clear()";

    public void clearCookies(){

        BrowserFactory.getWebDriver().manage().deleteAllCookies();
    }
    public void executeJSCode(String jsCode){

        ((JavascriptExecutor) BrowserFactory.getWebDriver()).executeScript(jsCode);
    }
    public void scrollToElement(Element element){

        int yLocation = element.getWebElement().getLocation().getY();
        executeJSCode(String.format("window.scrollTo(%d, 0)", yLocation));
    }

}
