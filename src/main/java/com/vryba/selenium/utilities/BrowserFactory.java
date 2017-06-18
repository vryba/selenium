package com.vryba.selenium.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
    private static final String DRIVER_NOT_INITIALIZED = "! ERROR\tWebDriver not initialized";
    private static BrowserFactory browserFactory = null;
    public static WebDriver driver;
    public enum BrowserType{FirefoxDriver, InternetExplorerDriver, ChromeDriver}

    private BrowserFactory(BrowserType browserType){
        if(browserFactory == null){
            switch (browserType) {
                case FirefoxDriver:
                    driver = new FirefoxDriver();
                    break;
                case InternetExplorerDriver:
                    driver = new InternetExplorerDriver();
                    break;
                case ChromeDriver:
                    driver = new ChromeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Typo, check specified browser name: "+browserType);
            }
            browserFactory = this;
        }
    }
    public static WebDriver startBrowser (BrowserType browserType, String url){
        new BrowserFactory(browserType);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    public static WebDriver getWebDriver() {
        if (browserFactory != null) {
            return driver;
        } else {
            throw new RuntimeException(DRIVER_NOT_INITIALIZED);
        }
    }
    public static void closeSession(){
        browserFactory=null;
        driver.quit();
        driver=null;
    }
}

