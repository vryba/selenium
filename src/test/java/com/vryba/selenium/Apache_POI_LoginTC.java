package com.vryba.selenium;

import com.vryba.selenium.excelReader.ExcelLoginDataReader;
import com.vryba.selenium.pageObjects.SignIn_Action;
import com.vryba.selenium.utilities.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Apache_POI_LoginTC {

    private static WebDriver driver = null;

    public static void main(String[] args) throws Exception {
        //This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
        ExcelLoginDataReader.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(Constant.URL);
        SignIn_Action.Execute(driver);
        System.out.println("Login Successfully, now it is the time to Log Off buddy.");
        //HomePO.lnk_LogOut(driver).click();
        driver.quit();
        //This is to send the PASS value to the Excel sheet in the result column.
        ExcelLoginDataReader.setCellData("Pass", 1, 3);
    }
}
