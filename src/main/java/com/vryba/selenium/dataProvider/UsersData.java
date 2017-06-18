package com.vryba.selenium.dataProvider;

import com.vryba.selenium.excelReader.ExcelDataReader;
import com.vryba.selenium.utilities.Constant;

public class UsersData {
    private static final int TESTCASE_NAME_COLUMN = 0;
    private static final int USER_EMAIL_COLUMN = 1;
    private static final int PASSWORD_COLUMN = 2;
    private String userEmail;
    private String password;
    private ExcelDataReader userDataReader;
    private static final String sheetName = "Sheet1";
    private Integer rowNum;

    public UsersData(String testCase){
        try {
            userDataReader = new ExcelDataReader(Constant.Path_TestData + Constant.File_TestData, sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        for(int row=0; row<userDataReader.getLastRow(); row++){
            if (userDataReader.getCellData(row, TESTCASE_NAME_COLUMN).contentEquals(testCase)){
                rowNum = row;
                break;
            }
        }
        if (rowNum == null){
            throw new RuntimeException("Test case name not found in target excel file: " + Constant.Path_TestData + Constant.File_TestData);
        }
        userEmail = userDataReader.getCellData(rowNum, USER_EMAIL_COLUMN);
        password = userDataReader.getCellData(rowNum, PASSWORD_COLUMN);
    }
    //method to get user name from excel
    public String getEmail(){
        return userEmail;
    }
    //method to get password from excel
    public String getPassword(){
        return password;
    }
}
