package com.abb.dias.api.automation.core.exlinputdata;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;

public class InputData {
	public InputData() {

	}

	
	
	public List<String> meta_api_nam;
	public static List<String> meta_api_name = new ArrayList<String>();
	public static List<String> tc_id = new ArrayList<String>();
	public static List<String> s_no_column = new ArrayList<String>();
	public static List<String> meta_endpoint = new ArrayList<String>();
	public static List<String> meta_request_body = new ArrayList<String>();
	public static List<String> meta_sql_query = new ArrayList<String>();
	public static List<String> response_datatype = new ArrayList<String>();
	public static List<String> index_column = new ArrayList<String>();
	public static List<String> servicename_column= new ArrayList<String>();

	public static List<String> execute = new ArrayList<String>();
	public static List<String> data_validation = new ArrayList<String>();
	public static List<String > response_validation  =new ArrayList<String>();
	public static List<String> command = new ArrayList<String>();
	public static List<String> expected_response_code = new ArrayList<String>();

	public static List<String> endpoint_label = new ArrayList<String>();
	public static List<String> capature_output = new ArrayList<String>();
	public static List<String> api_name1 = new ArrayList<String>();
	public static List<String> endpoint_value = new ArrayList<String>();
	
	public static List<String> field1_column = new ArrayList<String>();
	public static List<String> field2_column = new ArrayList<String>();
	public static List<String> field3_column  = new ArrayList<String>();
	public static List<String> field4_column = new ArrayList<String>();
	public static List<String> field5_column = new ArrayList<String>();
	public static List<String> field6_column = new ArrayList<String>();
	public static List<String> field7_column = new ArrayList<String>();
	public static List<String> field8_column  = new ArrayList<String>();
	public static List<String> field9_column = new ArrayList<String>();
	public static List<String> field10_column = new ArrayList<String>();


	public static List<String> value1_column = new ArrayList<String>();
	public static List<String> value2_column = new ArrayList<String>();
	public static List<String> value3_column = new ArrayList<String>();
	public static List<String> value4_column = new ArrayList<String>();
	public static List<String> value5_column=new ArrayList<String>();
	public static List<String> value6_column = new ArrayList<String>();
	public static List<String> value7_column = new ArrayList<String>();
	public static List<String> value8_column = new ArrayList<String>();
	public static List<String> value9_column = new ArrayList<String>();
	public static List<String> value10_column=new ArrayList<String>();
	
	
	
	
	public static List<String> capature1_column = new ArrayList<String>();
	public static List<String> capature2_column = new ArrayList<String>();
	public static List<String> capature3_column = new ArrayList<String>();
	public static List<String> capature4_column = new ArrayList<String>();
	public static List<String> capature5_column = new ArrayList<String>();
    
	public static List<String> capature6_column = new ArrayList<String>();
	public static List<String> capature7_column = new ArrayList<String>();
	public static List<String> capature8_column = new ArrayList<String>();
	public static List<String> capature9_column = new ArrayList<String>();
	public static List<String> capature10_column = new ArrayList<String>();

	public static List<String> key1_column = new ArrayList<String>();
	public static List<String> key2_column = new ArrayList<String>();
	public static List<String> key3_column = new ArrayList<String>();
	public static List<String> key4_column = new ArrayList<String>();
	public static List<String> key5_column = new ArrayList<String>();

	public static List<String> exp_value1_column = new ArrayList<String>();
	public static List<String> exp_value2_column = new ArrayList<String>();
	public static List<String> exp_value3_column = new ArrayList<String>();
	public static List<String> exp_value4_column = new ArrayList<String>();
	public static List<String> exp_value5_column = new ArrayList<String>();

	


	public static List<String> no_of_repeatations = new ArrayList<String>();
	public static ExcelReader exlConfigReader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
	public static String metaDataFileName = exlConfigReader.getValuefromConfigExcel("API MetaData Filename");
	public static String inputfileName = exlConfigReader.getValuefromConfigExcel("Input Excel Name");
	public static ExcelReader metaFileReader = new ExcelReader(metaDataFileName);
	public static ExcelReader inputFileReader = new ExcelReader(inputfileName);

	static {

		getDatafromMetafile();
		getDataFromInputDataFile();

	}

	/*This method is used to read the input data sheet from excel
	 * 
	 */

	public static void getDataFromInputDataFile() {
		try {


			for (int inputfilerow = 1; inputfilerow < inputFileReader.getSheet().getRows(); inputfilerow++) {

				
				
				
				s_no_column.add(inputFileReader.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getSerialNumberColumn()),
						inputfilerow));
					tc_id.add(inputFileReader.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getTestCaseIdColumn()),
							inputfilerow));
					execute.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExecuteColumn()), inputfilerow));
					servicename_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getServiceNameColumn()), inputfilerow));
					data_validation.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getDataValidationColumn()), inputfilerow));
					response_validation.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getResponseValidation()), inputfilerow));
					
					expected_response_code.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExpectedResponseCodeColumn()), inputfilerow));
					
					capature_output.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCaptureOutputColumn()), inputfilerow));
					no_of_repeatations.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getNoOfRepetitionsColumn()), inputfilerow));
					command.add(inputFileReader.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCommandColumn()),
							inputfilerow));
					api_name1.add(inputFileReader.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getApiNameColumn()),
							inputfilerow));
					endpoint_label.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getEndPointLabelColumn()), inputfilerow));
					endpoint_value.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getEndPointValueColumn()), inputfilerow));
					field1_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField1Column()), inputfilerow));
					field2_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField2Column()), inputfilerow));
					field3_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField3Column()), inputfilerow));
					field4_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField4Column()), inputfilerow));
					field5_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField5Column()), inputfilerow));
					field6_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField6Column()), inputfilerow));
					field7_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField7Column()), inputfilerow));
					field8_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField8Column()), inputfilerow));
					field9_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField9Column()), inputfilerow));
					field10_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getField10Column()), inputfilerow));
					value1_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue1Column()), inputfilerow));
					value2_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue2Column()), inputfilerow));
					value3_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue3Column()), inputfilerow));
					value4_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue4Column()), inputfilerow));
					value5_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue5Column()), inputfilerow));
					value6_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue6Column()), inputfilerow));
					value7_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue7Column()), inputfilerow));
					value8_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue8Column()), inputfilerow));
					value9_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue9Column()), inputfilerow));
					value10_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getValue10Column()), inputfilerow));
					capature1_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture1Column()), inputfilerow));
					capature2_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture2Column()), inputfilerow));
					capature3_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture3Column()), inputfilerow));
					capature4_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture4Column()), inputfilerow));
					capature5_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture5Column()), inputfilerow));
				
					capature6_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture6Column()), inputfilerow));
					capature7_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture7Column()), inputfilerow));
					capature8_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture8Column()), inputfilerow));
					capature9_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture9Column()), inputfilerow));
					capature10_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getCapture10Column()), inputfilerow));
									
					key1_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getKey1Column()), inputfilerow));
					key2_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getKey2Column()), inputfilerow));
					key3_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getKey3Column()), inputfilerow));
					key4_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getKey4Column()), inputfilerow));
					key5_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getKey5Column()), inputfilerow));
				
					exp_value1_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExpValue1Column()), inputfilerow));
					exp_value2_column.add(inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExpValue2Column()), inputfilerow));
					exp_value3_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExpValue3Column()), inputfilerow));
					exp_value4_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExpValue4Column()), inputfilerow));
					exp_value5_column.add( inputFileReader
							.getCellText(inputFileReader.getColumnNumber(EnvironmentManager.getExpValue5Column()), inputfilerow));
				
					
					
					
				
			}

		}

		catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "  An exception occured to while reading inputdata excel sheet" + e.getMessage());
			Reporter.log("Method Name is : " + nameofCurrMethod
					+ "  An exception occured to while reading inputdata excel sheet" + e.getMessage());
		}
	}

	/*This method is sued to read the data from meta data file
	 * 
	 */

	public static void getDatafromMetafile() {
		try {

			for (int metarow = 1; metarow < metaFileReader.getSheet().getRows(); metarow++) {

				meta_api_name.add(
						metaFileReader.getCellText(metaFileReader.getColumnNumber("API Name"), metarow));
				meta_endpoint.add(
						metaFileReader.getCellText(metaFileReader.getColumnNumber("Endpoint"), metarow));
				meta_request_body.add(metaFileReader
						.getCellText(metaFileReader.getColumnNumber("Request Body"), metarow));
				meta_sql_query.add(metaFileReader
						.getCellText(metaFileReader.getColumnNumber("Data Validation Query"), metarow));
				response_datatype.add(metaFileReader
						.getCellText(metaFileReader.getColumnNumber("Response Data Type"), metarow));
				index_column.add(metaFileReader
						.getCellText(metaFileReader.getColumnNumber("Index"), metarow));
				
				
				
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " An exception occured while reading Meta Data from excel sheet" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " An exception occured while reading Meta Data from excel sheet" + e.getMessage());

		}

	}

}