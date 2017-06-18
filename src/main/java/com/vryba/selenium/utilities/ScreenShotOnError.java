package com.vryba.selenium.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.testng.Assert.fail;

public class ScreenShotOnError {

    private String baseUrl = "http://www.google.com/";
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod
    public void methodSetup() {
        BrowserFactory.startBrowser(BrowserFactory.BrowserType.ChromeDriver, baseUrl);
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
    //Tests google calculator
    public void googleCalculator() throws IOException, InterruptedException {

        //Write 2+2 in google textbox
        WebElement googleTextBox = BrowserFactory.driver.findElement(By.id("lst-ib"));
        googleTextBox.sendKeys("2+2");

        //Hit enter
        googleTextBox.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        //Get result from calculator
        //WebElement calculatorTextBox = BrowserFactory.driver.findElement(By.id("cwtltblr"));
        WebElement calculatorTextBox = BrowserFactory.driver.findElement(By.xpath("//*[@id='cwtltblr']//span"));
                String result = calculatorTextBox.getText();

        Assert.assertEquals(result, "5");
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot) BrowserFactory.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\myProjects\\selenium\\src\\main\\java\\com\\vryba\\selenium\\errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) +  ".jpg"));
        }
    }

}
