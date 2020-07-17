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
	public static List<String> execute = new ArrayList<String>();
	public static List<String> data_validation = new ArrayList<String>();
	public static List<String> command = new ArrayList<String>();
	public static List<String> expected_response_code = new ArrayList<String>();

	public static List<String> endpoint_label = new ArrayList<String>();
	public static List<String> capature_output = new ArrayList<String>();
	public static List<String> api_name1 = new ArrayList<String>();
	public static List<String> endpoint_value = new ArrayList<String>();
	public static List<String> field1_column = new ArrayList<String>();
	public static List<String> value1_column = new ArrayList<String>();
	public static List<String> field2_column = new ArrayList<String>();
	public static List<String> value2_column = new ArrayList<String>();

	public static List<String> field3_column  = new ArrayList<String>();
	public static List<String> value3_column = new ArrayList<String>();
	public static List<String> field4_column = new ArrayList<String>();
	public static List<String> value4_column = new ArrayList<String>();
	public static List<String> value5_column=new ArrayList<String>();
	public static List<String> field5_column = new ArrayList<String>();
	public static List<String> capature1_column = new ArrayList<String>();
	public static List<String> var1_column = new ArrayList<String>();
	public static List<String> capature2_column = new ArrayList<String>();

	public static List<String>  var2_column  = new ArrayList<String>();
	public static List<String> capature3_column = new ArrayList<String>();
	public static List<String> var3_column = new ArrayList<String>();


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

	/*This method is used to read the input data from excel sheet
	 * 
	 */

	public static void getDataFromInputDataFile() {
		try {


			for (int inputfilerow = 1; inputfilerow < inputFileReader.getSheet().getRows(); inputfilerow++) {


				s_no_column.add(inputFileReader.getCellText(inputFileReader.getColumnNumber("S.No"),
						inputfilerow));
				tc_id.add(inputFileReader.getCellText(inputFileReader.getColumnNumber("TC id"),
						inputfilerow));
				execute.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Execute"), inputfilerow));

				data_validation.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Data Validation"), inputfilerow));

				expected_response_code.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Expected Response Code"), inputfilerow));

				capature_output.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Capture Output"), inputfilerow));
				no_of_repeatations.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("No of repetitions"), inputfilerow));
				command.add(inputFileReader.getCellText(inputFileReader.getColumnNumber("Command"),
						inputfilerow));
				api_name1.add(inputFileReader.getCellText(inputFileReader.getColumnNumber("API Name"),
						inputfilerow));
				endpoint_label.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("EndPoint Label"), inputfilerow));
				endpoint_value.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Endpoint Value"), inputfilerow));
				field1_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Field 1"), inputfilerow));
				value1_column.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Value 1"), inputfilerow));
				field2_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Field 2"), inputfilerow));
				value2_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Value 2"), inputfilerow));
				field3_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Field 3"), inputfilerow));
				value3_column.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Value 3"), inputfilerow));
				field4_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Field 4"), inputfilerow));
				value4_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Value 4"), inputfilerow));
				value5_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Value 5"), inputfilerow));
				field5_column.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Field 5"), inputfilerow));
				capature1_column.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Capture 1"), inputfilerow));
				var1_column.add(inputFileReader.getCellText(inputFileReader.getColumnNumber("Var 1"),
						inputfilerow));
				capature2_column.add(inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Capture 2"), inputfilerow));
				var2_column.add( inputFileReader.getCellText(inputFileReader.getColumnNumber("Var 2"),
						inputfilerow));
				capature3_column.add( inputFileReader
						.getCellText(inputFileReader.getColumnNumber("Capture 3"), inputfilerow));
				var3_column.add(inputFileReader.getCellText(inputFileReader.getColumnNumber("Var 3"),
						inputfilerow));

				
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