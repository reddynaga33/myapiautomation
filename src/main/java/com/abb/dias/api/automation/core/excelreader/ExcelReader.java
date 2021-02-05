package com.abb.dias.api.automation.core.excelreader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.log.TestLogger;

import jxl.Sheet;
import jxl.Workbook;


public class ExcelReader {
	Workbook workbook = null;
	public FileInputStream file;
	Sheet sheet = null;
	int numberOfRows;
	int numberOfColumns;
	String[] cellString = null;
	String stringname = null;
	//static String fileLocation = EnvironmentManager.getExcelFileLcoation().trim();
	public static FileInputStream file2;
	static Workbook workbook2;
	static Sheet sh;
	int inputfilecolumn = 0;
	int inputfilerow = 0;
	public ExcelReader(String sheetName) {
		try {
		
			
			//file = new FileInputStream(fileLocation);
			
			file = new FileInputStream(System.getProperty("user.dir")+"//src//test//MergedKshAndPhInput.xls");
		} catch (FileNotFoundException e) {
           Reporter.log("An exception occured find the input excel file location path " + e.getMessage());
		   TestLogger.errorMessage("An exception occured while reading find the input excel location path " + e.getMessage());			
		}
		try {
			workbook = Workbook.getWorkbook(file);
			sheet = workbook.getSheet(sheetName.trim());
		} catch (Exception e) {
			TestLogger.errorMessage("An exception occured while getting the workbook name :" + sheetName + e.getMessage());

		}

	}

	/*
	 * This method is used to get the object of the sheet class
	 * 
	 * @return sheet This return the object of the sheet class
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/*
	 * This method is used to get the object of the workbook object
	 * 
	 * @return Workbook return the object of workbook
	 */
	public Workbook workBookObject() {

		return workbook;

	}

	/*
	 * This method is used to get the text from cell in excel sheet
	 * 
	 * @param column provide column number of cell in excel sheet
	 * 
	 * @param row provide the row number of the cell in excel sheet
	 */
	public String getCellText(int column, int row) {

		String text = null;
		try {
			text = sheet.getCell(column,row).getContents().trim();
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while getting cell text is :" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while getting cell text is :" + e.getMessage());	
		}
		return text;
	}

	/*
	 * This method is used to get the value by providing the key name from
	 * configuration excel sheet
	 * 
	 * @param key The key name of the value
	 */
	public String getValuefromConfigExcel(String key) {

		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		String value = null;

		try {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < rows; j++) {
					if (sheet.getCell(i, j).getContents().trim().equalsIgnoreCase(key.trim())) {
						value = sheet.getCell(i + 1, j).getContents().trim();
						inputfilecolumn = columns;
						inputfilerow = rows;
					}
				}
			}
		}

		catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage( " Method Name is : " + nameofCurrMethod
					+ "An exception occured while get the key value from input excel configuration sheet  :" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while get the key value from input excel configuration sheet  :" + e.getMessage());

		}
		return value;
	}

	/*
	 * This method is used to get the value of key from configuration sheet
	 * 
	 * @Param columnName This is column name of the keys values
	 * 
	 * @Param provideKey This is key of the value
	 */
	public String getValuefromConfigExcel(String columnName, String provideKey) {

		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		String value = null;
		int columnumber = getColumnNumber(columnName.trim());
		try {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if (sheet.getCell(j, i).getContents().trim().equalsIgnoreCase(provideKey.trim())) {

						value = sheet.getCell(columnumber, i).getContents();
					}

				}
			}
		}

		catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage("An exception occured while get the key value from input excel configuration sheet  :" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while get the key value from input excel configuration sheet  :" + e.getMessage());

		}
		return value;
	}

	/*
	 * This Method is useful to get the column number by providing cell text
	 * 
	 * @Param text This is text in cell
	 */
	public int getColumnNumber(String text) {

		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		int inputfilecolumn = 0;
		try {
			for (int i = 0; i < columns; i++) {
				for (int j = 0; j < rows; j++) {
					if (sheet.getCell(i, j).getContents().trim().equalsIgnoreCase(text.trim())) {

						inputfilecolumn = i;

					}
				}
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while taking cell text from excel input data sheet  :" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while taking cell text from excel input data sheet  :" + e.getMessage());

		}
		return inputfilecolumn;
	}

	/*
	 * This method is used to verify the text present in cell for excel sheet
	 * 
	 * @Param searchSting This is text that search while excel sheet
	 */
	public boolean verifyStringPresent(String searchString) {

		int numberOfRows = sheet.getRows();

		int numberOfColumns = sheet.getColumns();
		boolean present = false;
		try {
			for (int rows = 0; rows < numberOfRows; rows++) {

				for (int columns = 0; columns < numberOfColumns; columns++) {
					if (searchString != null) {
						if (sheet.getCell(columns, rows).getContents().trim().equals(searchString.trim())) {

							present = true;

						}
					}
				}
			}
		} catch (Exception e) {



			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while searching string present in cell of the excel sheet  :" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while searching string present in cell of the excel sheet  :" + e.getMessage());	
		}
		return present;

	}
}
