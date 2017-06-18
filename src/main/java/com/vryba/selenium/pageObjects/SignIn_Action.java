/*
* This class to accept the test data from excel file
*/
package com.vryba.selenium.pageObjects;
import com.vryba.selenium.excelReader.ExcelLoginDataReader;
import org.openqa.selenium.WebDriver;
//import com.vryba.selenium.te

public class SignIn_Action {
    public static void Execute(WebDriver driver)throws Exception{
        //this is to get the values from Excel, passing paramenters (Row num and Col num) to getCellData method
        String sUserName = ExcelLoginDataReader.getCellData(1, 1);
        String sPassword = ExcelLoginDataReader.getCellData(1, 2);
/*      Home_Page.lnk_MyAccount(driver).click();
        LogIn_Page.txtbx_UserName(driver).sendKeys(sUserName);
        LogIn_Page.txtbx_Password(driver).sendKeys(sPassword);
        LogIn_Page.btn_LogIn(driver).click();*/

    }
}
