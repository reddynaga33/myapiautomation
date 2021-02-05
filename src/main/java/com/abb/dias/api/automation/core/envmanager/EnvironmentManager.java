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
	 *This is method to get the sql server host name
	 */
	public static String getSqlHostName() {
		
		return properties.getProperty("HOSTNAME");
	
	}
	
	/*
	 *This is method to get the sql server host name
	 */
	public static String getSqlDataBaseName() {
		
		return properties.getProperty("DATABASENAME");
	
	}
	
	
	/*
	 *This is method to get the sql server username
	 */
	public static String getSqlUserName() {
		
		return properties.getProperty("USER");
	
	}
	
	/*
	 *This is method to get the sql server password
	 */
	public static String getSqlPasswordName() {
		
		return properties.getProperty("PASSWORD");
	
	}
	
	/*
	 *This is method to get the sql server jdbc
	 */
	public static String getSqlServerJdbc() {
		
		return properties.getProperty("SQLSERVERJDBC");
	
	}
	
	
	/*
	 *This is method to get the sql server port
	 */
	public static String getSqlServerPort() {
		
		return properties.getProperty("SQLSERVERPORT");
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 *This is method to get the column name of s.no from config properties file
	 */
	public static String getSerialNumberColumn() {
		
		return properties.getProperty("S.NO");
	
	}
	/*
	 *This is method to get the column name of Test case id  from config properties file
	 */

	public static String getTestCaseIdColumn() {

		return properties.getProperty("TCID");

	}

	/*
	 *This is method to get the column name of Execute from config properties file
	 */
	public static String getExecuteColumn() {

		return properties.getProperty("EXECUTE");

	}

	/*
	 *This is method to get the column name of Data Validation from config properties file
	 */
	public static String getDataValidationColumn() {

		return properties.getProperty("DATAVALIDATION");

	}

	/*
	 *This is method to get the column name of Expected Response Code from config properties file
	 */
	public static String getExpectedResponseCodeColumn() {

		return properties.getProperty("EXPECTEDRESPONSECODE");

	}

	/*
	 *This is method to get the column name of capture output from config properties file
	 */

	public static String getCaptureOutputColumn() {

		return properties.getProperty("CAPTUREOUTPUT");

	}

	/*
	 *This is method to get the column name of no of Repetitions from config properties file
	 */
	public static String getNoOfRepetitionsColumn() {

		return properties.getProperty("NOOFREPETITIONS");

	}

	/*
	 *This is method to get the column name of command from config properties file
	 */
	public static String getCommandColumn() {

		return properties.getProperty("COMMAND");

	}

	/*
	 *This is method to get the column name of api name from config properties file
	 */
	public static String getApiNameColumn() {

		return properties.getProperty("APINAME");

	}

	/*
	 *This is method to get the column name of Endpoint Label from config properties file
	 */
	public static String getEndPointLabelColumn() {

		return properties.getProperty("ENDPOINTLABEL");

	}

	/*
	 *This is method to get the column name of Endpoint Value from config properties file
	 */
	public static String getEndPointValueColumn() {

		return properties.getProperty("ENDPOINTVALUE");

	}

	/*
	 *This is method to get the column name of Service Name from config properties file
	 */
	public static String getServiceNameColumn() {

		return properties.getProperty("SERVICENAME");

	}

	/*
	 *This is method to get the column name of Field 1 from config properties file
	 */
	public static String getField1Column() {

		return properties.getProperty("FIELD1");

	}

	/*
	 *This is method to get the column name of Field2 from config properties file
	 */
	public static String getField2Column() {

		return properties.getProperty("FIELD2");

	}
	/*
	 *This is method to get the column name of Field3 from config properties file
	 */
	public static String getField3Column() {

		return properties.getProperty("FIELD3");

	}

	/*
	 *This is method to get the column name of Field4 from config properties file
	 */
	public static String getField4Column() {

		return properties.getProperty("FIELD4");

	}

	/*
	 *This is method to get the column name of Field5 from config properties file
	 */
	public static String getField5Column() {

		return properties.getProperty("FIELD5");

	}

	/*
	 *This is method to get the column name of Field6 from config properties file
	 */
	public static String getField6Column() {

		return properties.getProperty("FIELD6");

	}

	/*
	 *This is method to get the column name of Field7 from config properties file
	 */

	public static String getField7Column() {

		return properties.getProperty("FIELD7");

	}


	/*
	 *This is method to get the column name of Field8 from config properties file
	 */
	public static String getField8Column() {

		return properties.getProperty("FIELD8");

	}



	/*
	 *This is method to get the column name of Field9 from config properties file
	 */
	public static String getField9Column() {

		return properties.getProperty("FIELD9");

	}


	/*
	 *This is method to get the column name of Field10 from config properties file
	 */
	public static String getField10Column() {

		return properties.getProperty("FIELD10");

	}


	/*
	 *This is method to get the column name of Value1 from config properties file
	 */
	public static String getValue1Column() {

		return properties.getProperty("VALUE1");

	}

	/*
	 *This is method to get the column name of Value2 from config properties file
	 */
	public static String getValue2Column() {

		return properties.getProperty("VALUE2");

	}


	/*
	 *This is method to get the column name of Value3 from config properties file
	 */
	public static String getValue3Column() {

		return properties.getProperty("VALUE3");

	}



	/*
	 *This is method to get the column name of Value4 from config properties file
	 */
	public static String getValue4Column() {

		return properties.getProperty("VALUE4");

	}


	/*
	 *This is method to get the column name of Value5 from config properties file
	 */
	public static String getValue5Column() {

		return properties.getProperty("VALUE5");

	}



	/*
	 *This is method to get the column name of Value6 from config properties file
	 */
	public static String getValue6Column() {

		return properties.getProperty("VALUE6");

	}

	/*
	 *This is method to get the column name of Value7 from config properties file
	 */
	public static String getValue7Column() {

		return properties.getProperty("VALUE7");

	}



	/*
	 *This is method to get the column name of Value8 from config properties file
	 */
	public static String getValue8Column() {

		return properties.getProperty("VALUE8");

	}



	/*
	 *This is method to get the column name of Value9 from config properties file
	 */
	public static String getValue9Column() {

		return properties.getProperty("VALUE9");

	}



	/*
	 *This is method to get the column name of Value10 from config properties file
	 */
	public static String getValue10Column() {

		return properties.getProperty("VALUE10");

	}

	/*
	 *This is method to get the column name of Capature1 from config properties file
	 */
	public static String getCapture1Column() {

		return properties.getProperty("CAPTURE1");

	}

	/*
	 *This is method to get the column name of Capature2 from config properties file
	 */
	public static String getCapture2Column() {

		return properties.getProperty("CAPTURE2");

	}


	/*
	 *This is method to get the column name of Capature3 from config properties file
	 */
	public static String getCapture3Column() {

		return properties.getProperty("CAPTURE3");

	}


	/*
	 *This is method to get the column name of Capature4 from config properties file
	 */
	public static String getCapture4Column() {

		return properties.getProperty("CAPTURE4");

	}


	/*
	 *This is method to get the column name of Capature5 from config properties file
	 */
	public static String getCapture5Column() {

		return properties.getProperty("CAPTURE5");

	}


	/*
	 *This is method to get the column name of Capature6 from config properties file
	 */
	public static String getCapture6Column() {

		return properties.getProperty("CAPTURE6");

	}

	/*
	 *This is method to get the column name of Capature7 from config properties file
	 */
	public static String getCapture7Column() {

		return properties.getProperty("CAPTURE7");

	}

	/*
	 *This is method to get the column name of Capature8 from config properties file
	 */
	public static String getCapture8Column() {

		return properties.getProperty("CAPTURE8");

	}


	/*
	 *This is method to get the column name of Capature9 from config properties file
	 */
	public static String getCapture9Column() {

		return properties.getProperty("CAPTURE9");

	}



	/*
	 *This is method to get the column name of Capature10 from config properties file
	 */
	public static String getCapture10Column() {

		return properties.getProperty("CAPTURE10");

	}
	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getKey1Column() {

		return properties.getProperty("KEY1");

	}

	/*
	 *This is method to get the column name of KEY 2 from config properties file
	 */
	public static String getKey2Column() {

		return properties.getProperty("KEY2");

	}

	/*
	 *This is method to get the column name of KEY 3 from config properties file
	 */
	public static String getKey3Column() {

		return properties.getProperty("KEY3");

	}

	

	/*
	 *This is method to get the column name of KEY 4 from config properties file
	 */
	public static String getKey4Column() {

		return properties.getProperty("KEY4");

	}


	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getKey5Column() {

		return properties.getProperty("KEY5");

	}

	
	

	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getExpValue1Column() {

		return properties.getProperty("EXPECTEDVALUE1");

	}

	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getExpValue2Column() {

		return properties.getProperty("EXPECTEDVALUE2");

	}

	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getExpValue3Column() {

		return properties.getProperty("EXPECTEDVALUE3");

	}
	
	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getExpValue4Column() {

		return properties.getProperty("EXPECTEDVALUE4");

	}
	
	
	/*
	 *This is method to get the column name of KEY 1 from config properties file
	 */
	public static String getExpValue5Column() {

		return properties.getProperty("EXPECTEDVALUE5");

	}

	


	
	/*
	 *This is method to get the column name of Api Name from config properties file
	 */
	public static String getMetaDataApiNameColumn() {

		return properties.getProperty("METAAPINAME");

	}
	/*
	 *This is method to get the column name of Endpoint from config properties file
	 */
	public static String getMetaDataAEndPointColumn() {

		return properties.getProperty("ENDPOINT");

	}


	/*
	 *This is method to get the column name of Request Body from config properties file
	 */
	public static String getMetaDataARequestBodyColumn() {

		return properties.getProperty("REQUESTBODY");

	}

	/*
	 *This is method to get the column name of Data Validation Query from config properties file
	 */
	public static String getMetaDataADataValidationQueryColumn() {

		return properties.getProperty("DATAVALIDATIONQUERY");

	}

	/*
	 *This is method to get the column name of Response Data Type from config properties file
	 */
	public static String getMetaDataAResponseDatTypeColumn() {

		return properties.getProperty("RESPONSEDATATYPE");

	}


	/*
	 *This is method to get the column name of index from config properties file
	 */
	public static String getMetaDataAIndexColumn() {

		return properties.getProperty("INDEX");

	}


	/*
	 *This is method to get the KSH Service Name keyword config properties file
	 */
	public static String getKshServiceName() {

		return properties.getProperty("KSHSERVICENAME");

	}
	/*
	 *This is method to get ph service name keyword from config properties file
	 */
	public static String getPhServiceName() {

		return properties.getProperty("PHSERVICENAME");

	}

	/*
	 *This is method to get ph service name keyword from config properties file
	 */
	public static String getOLMServiceName() {

		return properties.getProperty("OLMRVICENAME");

	}


	/*
	 *This is method to get the KSH Service Name keyword config properties file
	 */
	public static String getKshURL() {

		return properties.getProperty("KSHURL");

	}
	/*
	 *This is method to get ph service name keyword from config properties file
	 */
	public static String getPhURL() {

		return properties.getProperty("PHURL");

	}



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


	public static String getPhExcelFileLcoation() {

		return properties.getProperty("EXCELSHEETLOCATION");

	}		



	public static String getDataFromJsonPath() {

		return properties.getProperty("MASTERDATAJSONFILE");
	}
	
	/*
	 *This method is used to get the user provided token status
	 */
	public static String userProvidedToken() {
		return properties.getProperty("USERPROVIDEDTOKEN");
		

	}
	
	
	/*
	 *This method is used to get the status true
	 */
	public static String foundTrue() {
		return properties.getProperty("TRUE");
		

	}
	
	
	/*
	 *This method is used to get the status true
	 */
	public static String foundFalse() {
		return properties.getProperty("FALSE");
		

	}
	
	/*
	 * This method is used to get the response data first character
	 */
	public static String getresponseFirstcharacter() {
		
		return properties.getProperty("RESPONSEEXPECTEDDATATYPE");
	}
	
	
	/*
	 * This method is used to get the value of responsevalidation from config file
	 */
	public static String getResponseValidation() {
		
		return properties.getProperty("RESPONSEVALIDATION");
	}

	/*
	 * Static block to initializing the properties method
	 */
	static {
		properties = new Properties();

		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");

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
