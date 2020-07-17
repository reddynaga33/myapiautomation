package com.abb.dias.api.automation.core.envmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.log.TestLogger;

public class EnvironmentManager {


	/*
	 * Properties Declaration
	 */
	private static Properties properties;

	/*
	 * db configuration sheet name in excelfile
	 */

	/*
	 * This method is used to get the input excel data
	 */
	public static String getInputExcelName() {

		return properties.getProperty("INPUTEXCELDATA");
	}

	/*
	 *This method is sued to get the HMTL TEMPLATE LOCATION 
	 */
	public static String getHtmlTemplate() {
		return properties.getProperty("HTMLTEMPLATELOCATION");

	}
	
	/*
	 *This method is sued to get the HMTL TEMPLATE LOCATION 
	 */
	public static String logFolder() {
		//return properties.getProperty(System.getProperty("user.dir")+"\\test-output\\index.html");
		return properties.getProperty(System.getProperty("user.dir")+"/test-output/index.html");


	}
	

	/*
	 * This method is used to get the Token information sheet
	 */
	public static String getTokenInfoSheetName() {

		return properties.getProperty("EXCELHEETTOKENINFOSHEET");
	}
    /*
     * This method is sued to get the base url of the api service
     */
	public static String getBaseURL() {

		return properties.getProperty("BASEURL");
	}

	/*
	 * get the configuration sheet name of excel
	 * 
	 */
	public static String getExcelConfigSheetName() {

		return properties.getProperty("EXCELCONFIGURATIONSHEETNAME");
	}
    /*
     * Thi method is ued to get the db configuration sheet
     */
	public static String getDbConfigSheetName() {

		return properties.getProperty("DBCONFIGURATIONSHEET");
	}
    /*
     */
	public static String getSheetName() {

		return properties.getProperty("EXCELSHEETNAME");
	}

	/*
	 * This method return the json data path
	 */
	public static String getExcelFileLcoation() {

		return properties.getProperty("EXCELSHEETLOCATION");
	
	}

	 
			

	
	
	public static String getDataFromJsonPath() {

		return properties.getProperty("MASTERDATAJSONFILE");
	}

	/*
	 * Static block to initializing the properties method
	 */
	static {
		properties = new Properties();

		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
			
	System.out.println(System.getProperty("user.dir"));
			//FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");

			properties.load(ip);

		} catch (IOException e) {
			
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "Execption was thrown at EnvironmentManager class- static block" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "Execption was thrown at EnvironmentManager class- static block" + e.getMessage());

		}

	}

}
