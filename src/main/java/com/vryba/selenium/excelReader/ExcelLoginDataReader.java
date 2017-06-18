package com.vryba.selenium.excelReader;

import com.vryba.selenium.utilities.Constant;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelLoginDataReader {

    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //method to set filePath, open excelFile, pass path and sheetname as args to this method
    public static void setExcelFile(String Path, String SheetName) throws Exception{

        try {
            //Open excel file
            FileInputStream excelFile = new FileInputStream(Path);
            //access required test data sheet
            ExcelWBook = new XSSFWorkbook(excelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e){
            throw e;
        }
    }
    //method to read test data from file Cell, in we're passing parameters as Rox num nad Col num
    public static String getCellData(int RowNum, int ColNum){
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        }catch (Exception e){
            return "";
        }
    }
    //method to write in the Excel cell, Row num and Col num are the parameters
    public static void setCellData(String Result, int RowNum, int ColNum)throws Exception {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            //Constant variable Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw e;
        }
    }
}

