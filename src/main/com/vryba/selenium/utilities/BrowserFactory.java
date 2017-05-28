package main.com.vryba.selenium.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {

    private static BrowserFactory browserFactory = null;
    private static WebDriver driver;
    public static enum BrowserType{FirefoxDriver, InternetExplorerDriver, ChromeDriver};

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
}

