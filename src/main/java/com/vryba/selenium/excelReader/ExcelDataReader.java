package com.vryba.selenium.excelReader;

import com.vryba.selenium.utilities.Constant;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelDataReader {

    private XSSFSheet excelWSheet;
    private XSSFWorkbook excelWBook;
    private XSSFCell cell;
    private XSSFRow row;

    //method to set filePath, open excelFile, pass path and sheetname as args to this method
    public ExcelDataReader(String path, String sheetName) throws Exception{

        try {
            //Open excel file
            FileInputStream excelFile = new FileInputStream(path);
            //access required test dataProvider sheet
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e){
            throw e;
        }
    }
    //method to get number of rows in a sheet
    public int getLastRow(){

        return excelWSheet.getLastRowNum();
    }
    //method to read test dataProvider from file cell, in we're passing parameters as Rox num nad Col num
    public String getCellData(int RowNum, int ColNum){
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = cell.getStringCellValue();
            return CellData;
        }catch (Exception e){
            return "";
        }
    }
    //method to write in the Excel cell, row num and Col num are the parameters
    public void setCellData(String Result, int RowNum, int ColNum)throws Exception {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum, row.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(Result);
            } else {
                cell.setCellValue(Result);
            }
            //Constant variable Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw e;
        }
    }
}

