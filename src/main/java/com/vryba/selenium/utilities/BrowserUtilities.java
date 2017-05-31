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
}
