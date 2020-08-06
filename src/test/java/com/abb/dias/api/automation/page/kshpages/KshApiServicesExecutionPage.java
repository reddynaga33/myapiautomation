package com.abb.dias.api.automation.page.kshpages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.database.DatabaseUtility;
import com.abb.dias.api.automation.core.diffhmtlhighlighter.FileComparisonHighlighter;
import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.exlinputdata.InputData;
import com.abb.dias.api.automation.core.log.TestLogger;
import com.abb.dias.api.automation.core.report.ExtentsReport;
import com.abb.dias.api.automation.core.restapiutility.RestApiUtility;
import com.abb.dias.api.automation.core.txtfilewriter.NotepadWriter;
import com.abb.dias.api.automation.core.txtfilewriter.TextfileComparision;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class KshApiServicesExecutionPage  extends RestApiUtility{
	
	DatabaseUtility db = new DatabaseUtility();
	InputData id = new InputData();
	InputData id_1 = new InputData();

	ExcelReader exlConfigReader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
	String inputfileName = exlConfigReader.getValuefromConfigExcel("Input Excel Name");
	ExcelReader inputFileReader = new ExcelReader(inputfileName);

	/*
	 * This method is used to execute All the API Services
	 */
	@SuppressWarnings("static-access")
	public void executeKshApisServices() throws InterruptedException {

		String cap_output = null;
		String jsonString = null;
		Response response = null;
		JSONObject json = null;

		TestLogger.testMessage("kSH Api services Execution has started");
		Reporter.log("kSH Api services Execution has started");
		try {
			NotepadWriter.deleteResultfolder();

			for (int i = 0; i < id.meta_api_name.size(); i++) {

				if (!id.meta_endpoint.get(i).isEmpty()) {

					for (int j = 0; j < id.api_name1.size(); j++) {

						if (id.meta_api_name.get(i).equals(id.api_name1.get(j))
								& (id.execute.get(j).equalsIgnoreCase("yes"))) {
							int counter_repeatation = 1;

							for (int repeation = 1; repeation <= Integer
									.parseInt(id.no_of_repeatations.get(j)); repeation++) {

								TestLogger.testMessage("The S.No of test case about to execute :"
										+ id.s_no_column.get(j) + " &  Testcase id is: " + id.tc_id.get(j)
										+ " &  Number of repetation " + counter_repeatation);
								Reporter.log("The S.No of test case about to execute :" + id.s_no_column.get(j)
										+ " &  Testcase id is: " + id.tc_id.get(j) + " &  Number of repetation "
										+ counter_repeatation);

								jsonString = StringParameterization(id.meta_request_body.get(i),
										id.field1_column.get(j), id.field2_column.get(j), id.field3_column.get(j),
										id.field4_column.get(j), id.field5_column.get(j), id.value1_column.get(j),
										id.value2_column.get(j), id.value3_column.get(j), id.value4_column.get(j),
										id.value5_column.get(j));

								TestLogger.testMessage("Parameterized Json Request Body is :: " + jsonString);
								Reporter.log("Parameterized Json Request Body is :: " + jsonString);

								String endPointUrl = metaInputEndpotintConcatnation(id.meta_endpoint.get(i),
										id.endpoint_value.get(j));
								String parmeterizedUrl = StringParameterization(endPointUrl, id.field1_column.get(j),
										id.field2_column.get(j), id.field3_column.get(j), id.field4_column.get(j),
										id.field5_column.get(j), id.value1_column.get(j), id.value2_column.get(j),
										id.value3_column.get(j), id.value4_column.get(j), id.value5_column.get(j));

								response = executeHttpRequest(parmeterizedUrl, jsonString, id.command.get(j));

								ExtentsReport.startTest("The S.No of test case executed is :" + id.s_no_column.get(j)
										+ " &  Testcase id is: " + id.tc_id.get(j) + " &  Number of repetation "
										+ counter_repeatation);

								TestLogger.testMessage("The S.No of test case executed is :" + id.s_no_column.get(j)
										+ " &  Testcase id is: " + id.tc_id.get(j) + " &  Number of repetation "
										+ counter_repeatation);
								Reporter.log("The S.No of test case executed is :" + id.s_no_column.get(j)
										+ " &  Testcase id is: " + id.tc_id.get(j) + " &  Number of repetation "
										+ counter_repeatation);

								if (response.getStatusCode() == Integer.parseInt(id.expected_response_code.get(j))) {

									ExtentsReport.testInfo(
											"The Api respone time is : " + response.getTime() + " Milli seconds");

									if (id.capature_output.get(i).equalsIgnoreCase("yes")) {

										capatureResponseandPassToNextApi(id.response_datatype.get(i),response, 0, j,
												id.field1_column.get(j + 1), id.field2_column.get(j + 1),
												id.field3_column.get(j + 1), id.field4_column.get(j + 1),
												id.field5_column.get(j + 1), id.value1_column.get(j + 1),
												id.value2_column.get(j + 1), id.value3_column.get(j + 1),
												id.value4_column.get(j + 1), id.value5_column.get(j + 1),
												id.capature1_column.get(j), id.capature2_column.get(j),
												id.capature3_column.get(j), id.capature4_column.get(j),
												id.capature5_column.get(j));
									}
									if ((id.data_validation.get(j).equalsIgnoreCase("no"))) {

										ExtentsReport.testInfo("The S.No of test case executed is :"
												+ id.s_no_column.get(j) + " &  Testcase id is: " + id.tc_id.get(j)
												+ " &  Number of repetation " + counter_repeatation);
										ExtentsReport.testPasedMessage(id.api_name1.get(j) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(j));

										TestLogger.testMessage(id.api_name1.get(j) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(j));
										Reporter.log(id.api_name1.get(j) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(j));

										NotepadWriter
												.collectRespone(
														id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
																+ "_repeation_No" + counter_repeatation,
														response.asString());

										TestLogger.testMessage("Collected API Response into text files");
										Reporter.log("Collected API Response into text files ");

									}

									if ((id.data_validation.get(j).equalsIgnoreCase("yes"))
											& (!(id.meta_sql_query.get(i).isEmpty()))) {

										NotepadWriter
												.collectRespone(
														id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
																+ "_repeation_No" + counter_repeatation,
														response.asString());

										TestLogger.testMessage("Collected API Response into text files");
										Reporter.log("Collected API Response into text files ");

										List<Object> apiresponse = convertApiResponseTokeyValuePairs(response);

										TestLogger.testMessage(
												"Converted API Response into key value pairs: " + id.api_name1.get(j));


									//	NotepadWriter.writerr(apiresponse, id.api_name1.get(j) + "_s.no"
									//			+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation, "api");

										String sqlString = StringParameterization(id.meta_sql_query.get(i),
												id.field1_column.get(j), id.field2_column.get(j),
												id.field3_column.get(j), id.field4_column.get(j),
												id.field5_column.get(j), id.value1_column.get(j),
												id.value2_column.get(j), id.value3_column.get(j),
												id.value4_column.get(j), id.value5_column.get(j));

										TestLogger.testMessage("sql query is :" + sqlString);

										List<Object> dbres = db.dbResult(sqlString);
										
										NotepadWriter.writerr(apiresponse,dbres,id.api_name1.get(j) + "_s.no"+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation,id.api_name1.get(j) + "_s.no"+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation) ;


									//	NotepadWriter.writerr(dbres, id.api_name1.get(j) + "_s.no"
										//		+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation, "db");

										List<Object> apires = convertApiResponseTokeyValuePairs(response);

										FileComparisonHighlighter.compareFiles(
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation,
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation);

										TextfileComparision.compareFiles(
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation,
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation);

										Boolean comparetextfiles = NotepadWriter.compareTextfiles(
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation,
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation);

										if (comparetextfiles) {

											ExtentsReport.testInfo("The S.No of test case executed is :"
													+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of Repetaion:: " + counter_repeatation);
											ExtentsReport.testPasedMessage(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are matched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);

											TestLogger.testMessage("The S.No of test case executed is :"
													+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of Repetaion:: " + counter_repeatation);

											Reporter.log("The S.No of test case executed is :" + id.s_no_column.get(j)
													+ " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of Repetaion:: " + counter_repeatation);

											Reporter.log(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are matched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);

										} else {

											ExtentsReport.testInfo("The S.No of test case executed is :"
													+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of Repetaion: " + counter_repeatation);
											ExtentsReport.testFail(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are mismatched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);
											TestLogger.testMessage("The S.No of test case executed is :"
													+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of Repetaion: " + counter_repeatation);
											TestLogger.errorMessage(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are mismatched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);
											Reporter.log(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are mismatched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);

										}

										System.out.println("***************" + apires.size());
										if (dbres.size() == apires.size()) {

											int counter = 0;
											for (int k = 0; k < apires.size(); k++) {
												if (dbres.get(k).equals(apires.get(k))) {

												} else {
													counter = counter + 1;
												}
											}

										}

										else {

											ExtentsReport.testError(
													"The api response key value pairs count is not machting with the db result key value pairs");
											TestLogger.testMessage(
													"The api response key value pairs count is not machting with the db result key value pairs");
											Reporter.log(
													"The api response key value pairs count is not machting with the db result key value pairs");
										}

									}

								} else {

									if ((id.data_validation.get(j).equalsIgnoreCase("no"))) {

										ExtentsReport.testInfo("The S.No of test case executed is :"
												+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
												+ " & Number of repetation: " + counter_repeatation);
										ExtentsReport.testFail(id.api_name1.get(j) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(j));
										TestLogger.testMessage("The S.No of test case executed is :"
												+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
												+ " & Number of repetation: " + counter_repeatation);
										TestLogger.testMessage(id.api_name1.get(j) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(j));
										Reporter.log(id.api_name1.get(j) + " Respone code is  :"
												+ response.getStatusCode() + " VS Expected Response code is :"
												+ id.expected_response_code.get(j));

										NotepadWriter
												.collectRespone(
														id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
																+ "_repeation_No" + counter_repeatation,
														response.asString());

									}

									if ((id.data_validation.get(j).equalsIgnoreCase("yes"))
											& (!(id.meta_sql_query.get(i).isEmpty()))) {

										NotepadWriter
												.collectRespone(
														id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
																+ "_repeation_No" + counter_repeatation,
														response.asString());
										List<Object> apiresponse = convertApiResponseTokeyValuePairs(response);

										//NotepadWriter.writerr(apiresponse, id.api_name1.get(j) + "_s.no"
										//		+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation, "api");

										String sqlString = StringParameterization(id.meta_sql_query.get(i),
												id.field1_column.get(j), id.field2_column.get(j),
												id.field3_column.get(j), id.field4_column.get(j),
												id.field5_column.get(j), id.value1_column.get(j),
												id.value2_column.get(j), id.value3_column.get(j),
												id.value4_column.get(j), id.value5_column.get(j));

										TestLogger.testMessage(sqlString);

										List<Object> dbres = db.dbResult(sqlString);

									//	NotepadWriter.writerr(dbres, id.api_name1.get(j) + "_s.no"
										//		+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation, "db");
										NotepadWriter.writerr(apiresponse,dbres,id.api_name1.get(j) + "_s.no"+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation,id.api_name1.get(j) + "_s.no"+ id.s_no_column.get(j) + "_repeation_No" + counter_repeatation) ;


										List<Object> apires = convertApiResponseTokeyValuePairs(response);

										FileComparisonHighlighter.compareFiles(
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation,
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation);
										TextfileComparision.compareFiles(
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation,
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation);

										Boolean comparetextfiles = NotepadWriter.compareTextfiles(
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation,
												id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j) + "_repeation_No"
														+ counter_repeatation);
										if (comparetextfiles) {
											ExtentsReport.testFail(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ "  &  Content of the API Response and DB Response both files are matched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);

										} else {
											NotepadWriter.collectRespone(
													id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
															+ "_repeation_No" + counter_repeatation,
													response.asString());

											ExtentsReport.testInfo("The S.No of test case executed is :"
													+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of repetation: " + counter_repeatation);
											ExtentsReport.testFail(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are mis matched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);
											TestLogger.testMessage("The S.No of test case executed is :"
													+ id.s_no_column.get(j) + " & Testcase id is: " + id.tc_id.get(j)
													+ " & Number of repetation: " + counter_repeatation);

											TestLogger.errorMessage(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are mis matched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);
											Reporter.log(id.api_name1.get(j) + " Respone code is  :"
													+ response.getStatusCode() + " VS Expected Response code is :"
													+ id.expected_response_code.get(j)
													+ " &  Content of the API Response and DB Response both files are mis matched : "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation + " VS "
													+ id.api_name1.get(j) + "_s.no" + id.s_no_column.get(j)
													+ "_repeation_No" + counter_repeatation);

										}

										if (dbres.size() == apires.size()) {

											int counter = 0;
											for (int k = 0; k < apires.size(); k++) {
												if (dbres.get(k).equals(apires.get(k))) {

												} else {
													counter = counter + 1;
												}
											}

										}

										else {
											
											ExtentsReport.testInfo(
													"The api response key value pairs count is not machting with the db result key value pairs");
											TestLogger.errorMessage(
													"The api response key value pairs count is not machting with the db result key value pairs");
											Reporter.log(
													"The api response key value pairs count is not machting with the db result key value pairs");

										}

									}

								}
								counter_repeatation = counter_repeatation + 1;

							}

						}
					}
				}

			}
		} catch (NumberFormatException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ", An exception occured while run the kshapi service: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod + "An exception occured while run the kshapi service: "
					+ e.getMessage());

		}
		System.out.println("hhhhh");

	}

	/*
	 * This method is used for request body parameterization * @Param obj:this is
	 * request body of the api
	 * 
	 * @Param f1: field1 value in input data file
	 * 
	 * @Param f2: field2 value in input data file
	 * 
	 * @Param f3: field3 value in input data file
	 * 
	 * @Param f4: field4 value in input data file
	 * 
	 * @Param f5: field5 value in input data file
	 * 
	 * @Param v1: value1 value in input data file
	 * 
	 * @Param v2: value2 value in input data file
	 * 
	 * @Param v3: value3 value in input data file
	 * 
	 * @Param v4: value4 value in input data file
	 * 
	 * @Param v5: value5 value in input data file
	 * 
	 */

	public void extractfeildvalues(String f1, String f2, String f3, String f4, String v1, String v2, String v3,
			String v4, JSONObject obj) {

		try {
			if (!f1.isEmpty()) {
				if (obj.containsKey(f1)) {
					obj.put(f1, v1);
				}
			}
			if (!f2.isEmpty()) {
				if (obj.containsKey(f2)) {
					obj.put(f2, v2);

				}
			}
			if (!f3.isEmpty()) {
				if (obj.containsKey(f3)) {
					obj.put(f3, v3);
				}
			}
			if (!f4.isEmpty()) {
				if (obj.containsKey(f4)) {
					obj.put(f4, v4);
				}
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while extracting field values: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while extracting field values: " + e.getMessage());

		}
	}
	/*
	 * This method is sued to convert into the API response into key value pairs
	 * 
	 * @ Param apiResponse: this is the api Response
	 */

	public List<Object> convertApiResponseTokeyValuePairs(Response apiResponse) {

		List<Object> sortedList = null;
		try {
			List<?> itemsCount = apiResponse.path("$");
			String jsonitem[] = new String[itemsCount.size()];
			for (int a = 0; a < itemsCount.size(); a++) {
				jsonitem[a] = itemsCount.get(a).toString();
			}
			String itemvalue = null;
			String[] itemvalue2 = null;
			List<String> item = new ArrayList<String>();
			for (int b = 0; b < jsonitem.length; b++) {
				itemvalue = jsonitem[b].substring(1, jsonitem[b].length() - 1);
				itemvalue2 = itemvalue.split(",");
				for (int c = 0; c < itemvalue2.length; c++) {
					item.add(itemvalue2[c]);
				}
			}
			sortedList = item.stream().sorted().collect(Collectors.toList());
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while converting the api json response into key and values pairs: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while converting the api json response into key and values pairs: "
					+ e.getMessage());

		}
		return sortedList;

	}

	/*
	 * This method is used to replace the string value with some other value
	 * 
	 * @Param anyString: provide the string name for parameterzation
	 * 
	 * @Param f1: field1 value in input data file
	 * 
	 * @Param f2: field2 value in input data file
	 * 
	 * @Param f3: field3 value in input data file
	 * 
	 * @Param f4: field4 value in input data file
	 * 
	 * @Param f5: field5 value in input data file
	 * 
	 * @Param v1: value1 value in input data file
	 * 
	 * @Param v2: value2 value in input data file
	 * 
	 * @Param v3: value3 value in input data file
	 * 
	 * @Param v4: value4 value in input data file
	 * 
	 * @Param v5: value5 value in input data file
	 */
	public String StringParameterization(String anystring, String f1, String f2, String f3, String f4, String f5,
			String v1, String v2, String v3, String v4, String v5) {

		String modifiedString = anystring;

		try {
			if (modifiedString.contains(f1)) {
				modifiedString = modifiedString.replace(f1, v1);
			}
			if (modifiedString.contains(f2)) {
				modifiedString = modifiedString.replace(f2, v2);

			}
			if (modifiedString.contains(f3)) {
				modifiedString = modifiedString.replace(f3, v3);

			}
			if (modifiedString.contains(f4)) {
				modifiedString = modifiedString.replace(f4, v4);

			}
			if (modifiedString.contains(f5)) {
				modifiedString = modifiedString.replace(f5, v5);

			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while doing the string parameterization: " + e.getMessage());
			TestLogger.errorMessage("An exception occured while doing the string parameterization: " + e.getMessage());
		}
		return modifiedString;

	}

	/*
	 * This method is used to concat the metadata file endpoint with inputdata file
	 * Endpoint value
	 * 
	 * @Param meataEndpoint :End point point API Servicein meata data file
	 * 
	 * @Param inputEndpoint : End point value in input data file
	 * 
	 */
	public String metaInputEndpotintConcatnation(String metaEndpoint, String inputEndpoint) {

		String tempinputEndpoint = metaEndpoint;
		try {
			if (!inputEndpoint.isEmpty()) {

				tempinputEndpoint = tempinputEndpoint.concat(inputEndpoint);
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while meta data endpoint concatnation with inputdata file Endpoint value: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ ",An exception occured while meta data endpoint concatnation with inputdata file Endpoint value: "
					+ e.getMessage());
		}
		return tempinputEndpoint;
	}

	public String capaturerResponseOutput(Response response, int listindex, String capature1_key) {

		// public String capaturerResponseOutput(Response response,int listindex, String
		// cap1,String cap2,String cap3, String cap4,String cap5) {

		List<Map<String, String>> items = response.jsonPath().getList("$");
		String var1 = items.get(0).get(capature1_key);

		System.out.println(" the out put form list  is " + items.get(0).get(capature1_key));
		return var1;

	}

	/*
	 * This method capature the response out put based on keys and pass it to next
	 * api
	 * 
	 * @Param output_datatype this is data type of the response comings
	 * 
	 * @Param Response this is response of the api
	 * 
	 * @Param this is index of the keys that going to capature the response value
	 * 
	 * @Param valueindex this is index of the that store value into list
	 * 
	 * @Param field_1 this is field 1 column in the excel input data
	 * 
	 * @Param field_2 this is field 2 column in the excel input data
	 * 
	 * @Param field_3 this is field 3 column in the excel input data
	 * 
	 * @Param field_4 this is field_4 column in the excel input data
	 * 
	 * @Param field_5 this is field_5 column in the excel input data
	 * 
	 * @Param value_1 this is value 1 column in the excel input data
	 * 
	 * @Param value_2 this is value 2 column in the excel input data
	 * 
	 * @Param value_3 this is value 3 column in the excel input data
	 * 
	 * @Param value_4 this is value4 column in the excel input data
	 * 
	 * @Param value_5 this is value_5 column in the excel input data
	 * 
	 * @Param key1 this is capature 1 column in the excel input data
	 * 
	 * @Param key2 this is capature 2 column in the excel input data
	 * 
	 * @Param key3 this is capature 3 column in the excel input data
	 * 
	 * @Param key4 this is capature 4 column in the excel input data
	 * 
	 * @Param key5 this is the capature 5 column in the excel input data
	 * 
	 */

	public void capatureResponseandPassToNextApi(String output_datatype, Response response, int listindex,
			int valueindex, String field_1, String field_2, String field_3, String field_4, String field_5,
			String value_1, String value_2, String value_3, String value_4, String value_5, String key1, String key2,
			String key3, String key4, String key5) {

		if (output_datatype.equalsIgnoreCase("List")) {

			if (!key1.isEmpty()) {
				List<Map<String, String>> items = response.jsonPath().getList("$");
				String capature1_value = items.get(listindex).get(key1);

				if (!field_1.isEmpty()) {

					if (key1.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_2.isEmpty()) {

					if (key1.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_3.isEmpty()) {

					if (key1.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_4.isEmpty()) {

					if (key1.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_5.isEmpty()) {

					if (key1.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capature1_value);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("Object")) {

			if (!key1.isEmpty()) {

				String capatured_object = response.jsonPath().getJsonObject(key1);
				if (!field_1.isEmpty()) {

					if (key1.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_2.isEmpty()) {

					if (key1.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_3.isEmpty()) {

					if (key1.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (key1.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_5.isEmpty()) {

					if (key1.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}
			}
		}

		if (output_datatype.equalsIgnoreCase("String")) {
			if (!field_1.isEmpty()) {
				// if(key1.equals(field_1)) {

				String stringouput = response.asString(); //
				id.value1_column.add(valueindex + 1, stringouput);

			}
		}

		if (output_datatype.equalsIgnoreCase("List")) {

			if (!key2.isEmpty()) {

				List<Map<String, String>> items = response.jsonPath().getList("$");
				String capature1_value = items.get(listindex).get(key2);
				if (!field_1.isEmpty()) {

					if (key2.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_2.isEmpty()) {

					if (key2.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capature1_value);

					}
				}
				if (!field_3.isEmpty()) {

					if (key2.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capature1_value);

					}
				}
				if (!field_4.isEmpty()) {

					if (key2.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_5.isEmpty()) {

					if (key2.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capature1_value);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("Object")) {

			if (!key2.isEmpty()) {

				String capatured_object = response.jsonPath().getJsonObject(key1);

				if (!field_1.isEmpty()) {

					if (key2.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_2.isEmpty()) {

					if (key2.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_3.isEmpty()) {

					if (key2.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (key2.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_5.isEmpty()) {

					if (key2.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("List")) {

			if (!key3.isEmpty()) {

				List<Map<String, String>> items = response.jsonPath().getList("$");
				String capature1_value = items.get(listindex).get(key3);
				if (!field_1.isEmpty()) {

					if (key3.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_2.isEmpty()) {

					if (key3.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capature1_value);

					}
				}
				if (!field_3.isEmpty()) {

					if (key3.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capature1_value);

					}
				}
				if (!field_4.isEmpty()) {

					if (key3.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_5.isEmpty()) {

					if (key3.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capature1_value);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("Object")) {

			if (!key3.isEmpty()) {

				String capatured_object = response.jsonPath().getJsonObject(key1);

				if (!field_1.isEmpty()) {

					if (key3.equals(value_1)) {

					}
				}

				if (!field_2.isEmpty()) {

					if (key3.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_3.isEmpty()) {

					if (key3.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (key4.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_5.isEmpty()) {

					if (key5.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("List")) {

			if (!key4.isEmpty()) {

				List<Map<String, String>> items = response.jsonPath().getList("$");
				String capature1_value = items.get(listindex).get(key3);
				if (!field_1.isEmpty()) {

					if (key4.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_2.isEmpty()) {

					if (key4.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capature1_value);

					}
				}
				if (!field_3.isEmpty()) {

					if (key4.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capature1_value);

					}
				}
				if (!field_4.isEmpty()) {

					if (key4.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_5.isEmpty()) {

					if (key4.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capature1_value);

					}
				}
			}
		}

		if (output_datatype.equalsIgnoreCase("Object")) {

			if (!key4.isEmpty()) {

				String capatured_object = response.jsonPath().getJsonObject(key1);

				if (!field_1.isEmpty()) {

					if (key4.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_2.isEmpty()) {

					if (key4.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_3.isEmpty()) {

					if (key4.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (key4.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_5.isEmpty()) {

					if (key4.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("List")) {

			if (!key5.isEmpty()) {
				List<Map<String, String>> items = response.jsonPath().getList("$");
				String capature1_value = items.get(listindex).get(key1);

				if (!field_1.isEmpty()) {

					if (key5.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_2.isEmpty()) {

					if (key5.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_3.isEmpty()) {

					if (key5.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_4.isEmpty()) {

					if (key5.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capature1_value);

					}
				}

				if (!field_5.isEmpty()) {

					if (key5.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capature1_value);

					}
				}

			}
		}

		if (output_datatype.equalsIgnoreCase("Object")) {

			if (!key5.isEmpty()) {

				String capatured_object = response.jsonPath().getJsonObject(key1);
				if (!field_1.isEmpty()) {

					if (key5.equals(value_1)) {

						id.value1_column.add(valueindex + 1, capatured_object);

					}
				}

				if (!field_2.isEmpty()) {

					if (key5.equals(value_2)) {

						id.value2_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_3.isEmpty()) {

					if (key5.equals(value_3)) {

						id.value3_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_4.isEmpty()) {

					if (key5.equals(value_4)) {

						id.value4_column.add(valueindex + 1, capatured_object);

					}
				}
				if (!field_5.isEmpty()) {

					if (key5.equals(value_5)) {

						id.value5_column.add(valueindex + 1, capatured_object);

					}
				}

			}
		}

	}

	/*
	 * This method is used to capture value for key from api response
	 * 
	 * @Param response this is response of the api
	 * 
	 * @Praram listindex this is index of the response if the response data type is
	 * list
	 * 
	 * @Param cap1 this is capature 1 column(key) in the excel input data
	 * 
	 * @Param cap2 thsi is capature 2 column(key) in the excel input data
	 * 
	 * @Param cap3 this is capature 3 columnn(Key) in the excel input data
	 * 
	 * @Pram cap4 this is capture 4 column (key) in the excel input data
	 * 
	 * @Param capt5 this is capture 5 column(key) in the excel input data
	 * 
	 * 
	 */

	public String ppassResponseOuputToNextApi(List<String> capatured_items, String field_1, String value_1,
			String field_2, String value_2, String field_3, String value_3, String field_4, List<String> value4_column,
			String field_5, String value_5, int number) {

		String value = null;

		if (!capatured_items.get(0).toString().isEmpty()) {
			if (!field_1.isEmpty()) {
				if (capatured_items.get(0).toString().equals(value_1)) {

					InputData.value1_column.add(number + 1, capatured_items.get(0));

				}

			}

		}

		if (!capatured_items.get(1).toString().isEmpty()) {
			if (!field_2.isEmpty()) {
				if (capatured_items.get(1).toString().equals(value_1)) {

				}

			}

		}

		if (!capatured_items.get(2).toString().isEmpty()) {
			if (!field_2.isEmpty()) {
				if (capatured_items.get(2).toString().equals(value_1)) {

				}

			}
		}

		if (!capatured_items.get(3).toString().isEmpty()) {
			if (!field_3.isEmpty()) {
				if (capatured_items.get(3).toString().equals(value_1)) {

				}

			}
		}
		if (!capatured_items.get(4).toString().isEmpty()) {
			if (!field_4.isEmpty()) {
				if (capatured_items.get(4).toString().equals(value_1)) {

				}

			}

		}
		return value;

	}

	/*
	 * This method is used to capture value for key from api response
	 * 
	 * @Param response this is response of the api
	 * 
	 * @Praram listindex this is index of the response if the response data type is
	 * list
	 * 
	 * @Param cap1 this is capature 1 column(key) in the excel input data
	 * 
	 * @Param cap2 thsi is capature 2 column(key) in the excel input data
	 * 
	 * @Param cap3 this is capature 3 columnn(Key) in the excel input data
	 * 
	 * @Pram cap4 this is capture 4 column (key) in the excel input data
	 * 
	 * @Param capt5 this is capture 5 column(key) in the excel input data
	 * 
	 * 
	 */
	public String capaturerResponseOutput(Response response, int listindex, String cap1, String cap2, String cap3,
			String cap4, String cap5) {

		List<Map<String, String>> items = response.jsonPath().getList("$");
		String var1 = items.get(0).get(cap1);

		return var1;

	}
}
	
	
	


