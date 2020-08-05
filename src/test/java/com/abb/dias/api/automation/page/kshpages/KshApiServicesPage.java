/*
 * package com.abb.dias.api.automation.page.kshpages;
 * 
 * import java.util.ArrayList;
 * 
 * import java.util.List; import java.util.stream.Collectors;
 * 
 * import org.testng.Reporter;
 * 
 * import com.abb.dias.api.automation.core.database.DatabaseUtility; import
 * com.abb.dias.api.automation.core.diffhmtlhighlighter.
 * FileComparisonHighlighter; import
 * com.abb.dias.api.automation.core.exlinputdata.InputData; import
 * com.abb.dias.api.automation.core.log.TestLogger; import
 * com.abb.dias.api.automation.core.report.ExtentsReport; import
 * com.abb.dias.api.automation.core.restapiutility.RestApiUtility; import
 * com.abb.dias.api.automation.core.txtfilewriter.NotepadWriter; import
 * com.abb.dias.api.automation.core.txtfilewriter.TextfileComparision; import
 * com.abb.dias.api.automtion.core.jsonreader.JsonReader;
 * 
 * import io.restassured.response.Response; import net.minidev.json.JSONObject;
 * 
 * public class KshApiServicesPage extends RestApiUtility {
 * 
 * DatabaseUtility db = new DatabaseUtility(); InputData id = new InputData();
 * 
 * 
 * This method is used to execute All the API Services
 * 
 * @SuppressWarnings("static-access") public void executeKshApisServices()
 * throws InterruptedException {
 * 
 * Response response = null; JSONObject json = null; int number_of_testcases=0;
 * int number_of_passed_testcases=0;
 * 
 * int number_of_ResponseCode_passed_testcases=0;
 * 
 * int number_of_ResponseCode_failed_testcases=0;
 * 
 * int number_of_contentEquals_passed_testcases=0;
 * 
 * int number_of_contentNotEquals_passed_testcases=0;
 * 
 * TestLogger.testMessage("kSH Api services Execution has started");
 * Reporter.log("kSH Api services Execution has started"); try {
 * NotepadWriter.deleteResultfolder();
 * 
 * for (int i = 0; i < id.meta_api_name.size(); i++) {
 * 
 * if (!id.meta_endpoint.get(i).isEmpty()) { System.out.print("3"); for(int
 * j=0;j<id.api_name1.size();j++) {
 * 
 * 
 * if(id.meta_api_name.get(i).equals(id.api_name1.get(j))&(id.execute.get(j).
 * equalsIgnoreCase("yes"))) { int counter_repeatation=1;
 * 
 * for(int
 * repeation=1;repeation<=Integer.parseInt(id.no_of_repeatations.get(j));
 * repeation++) {
 * 
 * TestLogger.testMessage("The S.No of test case about to execute :"+id.
 * s_no_column.get(j)+" &  Testcase id is: "+id.tc_id.get(j)
 * +" &  Number of repetation "+counter_repeatation);
 * Reporter.log("The S.No of test case about to execute :"+id.s_no_column.get(j)
 * +" &  Testcase id is: "+id.tc_id.get(j)+" &  Number of repetation "
 * +counter_repeatation);
 * 
 * String jsonString=
 * StringParameterization(id.meta_request_body.get(i),id.field1_column.get(j),id
 * .field2_column.get(j), id.field3_column.get(j), id.field4_column.get(j),
 * id.field5_column.get(j),id.value1_column.get(j),id.value2_column.get(j),id.
 * value3_column.get(j), id.value4_column.get(j),id.value5_column.get(j));
 * 
 * TestLogger.testMessage("Parameterized Json Request Body is :: "+jsonString);
 * Reporter.log("Parameterized Json Request Body is :: "+jsonString);
 * 
 * 
 * json = JsonReader.convertRequestBodyToJson(jsonString);
 * 
 * String endPointUrl=
 * metaInputEndpotintConcatnation(id.meta_endpoint.get(i),id.endpoint_value.get(
 * j));
 * 
 * response = executeHttpRequest(endPointUrl, json, id.command.get(j));
 * 
 * ExtentsReport.startTest("The S.No of test case executed is :"+id.s_no_column.
 * get(j)+" &  Testcase id is: "+id.tc_id.get(j)+" &  Number of repetation "
 * +counter_repeatation);
 * 
 * TestLogger.testMessage("The S.No of test case executed is :"+id.s_no_column.
 * get(j)+" &  Testcase id is: "+id.tc_id.get(j)+" &  Number of repetation "
 * +counter_repeatation);
 * Reporter.log("The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" &  Testcase id is: "+id.tc_id.get(j)+" &  Number of repetation "
 * +counter_repeatation);
 * 
 * 
 * if (response.getStatusCode()
 * ==Integer.parseInt(id.expected_response_code.get(j))) {
 * 
 * if ((id.data_validation.get(j).equalsIgnoreCase("no"))) {
 * 
 * number_of_ResponseCode_passed_testcases++;
 * 
 * ExtentsReport.testInfo("The S.No of test case executed is :"+id.s_no_column.
 * get(j)+" &  Testcase id is: "+id.tc_id.get(j)+" &  Number of repetation "
 * +counter_repeatation); ExtentsReport. testPasedMessage( id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j));
 * 
 * TestLogger.testMessage(id.api_name1.get(j) + " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)); Reporter.log(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j));
 * 
 * 
 * 
 * NotepadWriter.collectRespone(id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j
 * )+"_repeation_No"+counter_repeatation, response.asString());
 * 
 * TestLogger.testMessage("Collected API Response into text files");
 * Reporter.log("Collected API Response into text files ");
 * 
 * number_of_passed_testcases++;
 * 
 * }
 * 
 * 
 * if ((id.data_validation.get(j).equalsIgnoreCase("yes")) &
 * (!(id.meta_sql_query.get(i).isEmpty()))) {
 * 
 * 
 * NotepadWriter.collectRespone(id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j
 * )+"_repeation_No"+counter_repeatation, response.asString());
 * 
 * TestLogger.testMessage("Collected API Response into text files");
 * Reporter.log("Collected API Response into text files ");
 * 
 * List<Object> apiresponse = convertApiResponseTokeyValuePairs(response);
 * 
 * TestLogger.testMessage("Converted API Response into key value pairs: "+id.
 * api_name1.get(j));
 * 
 * 
 * NotepadWriter.writerr(apiresponse,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation, "api");
 * 
 * String sqlString=
 * StringParameterization(id.meta_sql_query.get(i),id.field1_column.get(j),id.
 * field2_column.get(j), id.field3_column.get(j), id.field4_column.get(j),
 * id.field5_column.get(j),id.value1_column.get(j),id.value2_column.get(j),id.
 * value3_column.get(j), id.value4_column.get(j),id.value5_column.get(j));
 * 
 * TestLogger.testMessage("sql query is :"+sqlString);
 * 
 * List<Object> dbres = db.dbResult(sqlString);
 * 
 * System.out.println("db reult is done"); NotepadWriter.writerr(dbres,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation, "db");
 * 
 * List<Object> apires = convertApiResponseTokeyValuePairs(response);
 * System.out.println("db reult is done2");
 * 
 * FileComparisonHighlighter.compareFiles(id.api_name1.get(j)+"_s.no"+id.
 * s_no_column.get(j)+"_repeation_No"+counter_repeatation,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation); System.out.println("db reult is done3");
 * 
 * TextfileComparision.compareFiles(id.api_name1.get(j)+"_s.no"+id.s_no_column.
 * get(j)+"_repeation_No"+counter_repeatation,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * System.out.println("db reult is done4");
 * 
 * 
 * Boolean
 * comparetextfiles=NotepadWriter.compareTextfiles(id.api_name1.get(j)+"_s.no"+
 * id.s_no_column.get(j)+"_repeation_No"+counter_repeatation,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation); System.out.println("db reult is done5");
 * 
 * if(comparetextfiles) {
 * 
 * number_of_contentEquals_passed_testcases++;
 * 
 * ExtentsReport.testInfo(
 * "The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" & Testcase id is: "+id.tc_id.get(j)+" & Number of Repetaion:: "
 * +counter_repeatation); ExtentsReport.testPasedMessage(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are matched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * TestLogger.testMessage("The S.No of test case executed is :"+id.s_no_column.
 * get(j)+" & Testcase id is: "+id.tc_id.get(j)+" & Number of Repetaion:: "
 * +counter_repeatation);
 * 
 * Reporter.log("The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" & Testcase id is: "+id.tc_id.get(j)+" & Number of Repetaion:: "
 * +counter_repeatation);
 * 
 * Reporter.log(id.api_name1.get(j) + " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are matched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * 
 * number_of_passed_testcases++;
 * 
 * } else {
 * 
 * ExtentsReport.testInfo(
 * "The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" & Testcase id is: "+id.tc_id.get(j)+" & Number of Repetaion: "
 * +counter_repeatation); ExtentsReport.testFail(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are mismatched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation); TestLogger.testMessage(
 * "The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" & Testcase id is: "+id.tc_id.get(j)+" & Number of Repetaion: "
 * +counter_repeatation); TestLogger.errorMessage(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are mismatched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation); Reporter.log(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are mismatched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * 
 * number_of_contentNotEquals_passed_testcases++; }
 * 
 * System.out.println("***************"+apires.size()); if (dbres.size() ==
 * apires.size()) {
 * 
 * int counter = 0; for (int k = 0; k < apires.size(); k++) { if
 * (dbres.get(k).equals(apires.get(k))) {
 * 
 * } else { counter = counter + 1; } }
 * 
 * 
 * }
 * 
 * else {
 * 
 * ExtentsReport.
 * testError("The api response key value pairs count is not machting with the db result key value pairs"
 * ); TestLogger.
 * testMessage("The api response key value pairs count is not machting with the db result key value pairs"
 * ); Reporter.
 * log("The api response key value pairs count is not machting with the db result key value pairs"
 * ); }
 * 
 * }
 * 
 * 
 * } else {
 * 
 * if ((id.data_validation.get(j).equalsIgnoreCase("no"))) {
 * number_of_ResponseCode_failed_testcases++;
 * 
 * ExtentsReport.testInfo(
 * "The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" & Testcase id is: "+id.tc_id.get(j)+" & Number of repetation: "
 * +counter_repeatation); ExtentsReport.testFail( id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j));
 * TestLogger.testMessage("The S.No of test case executed is :"+id.s_no_column.
 * get(j)+" & Testcase id is: "+id.tc_id.get(j)+" & Number of repetation: "
 * +counter_repeatation); TestLogger.testMessage(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)); Reporter.log( id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j));
 * 
 * 
 * 
 * NotepadWriter.collectRespone(id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j
 * )+"_repeation_No"+counter_repeatation, response.asString());
 * 
 * }
 * 
 * if ((id.data_validation.get(j).equalsIgnoreCase("yes")) &
 * (!(id.meta_sql_query.get(i).isEmpty()))) {
 * 
 * 
 * NotepadWriter.collectRespone(id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j
 * )+"_repeation_No"+counter_repeatation, response.asString()); List<Object>
 * apiresponse = convertApiResponseTokeyValuePairs(response);
 * 
 * NotepadWriter.writerr(apiresponse,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation, "api");
 * 
 * String sqlString=
 * StringParameterization(id.meta_sql_query.get(i),id.field1_column.get(j),id.
 * field2_column.get(j), id.field3_column.get(j), id.field4_column.get(j),
 * id.field5_column.get(j),id.value1_column.get(j),id.value2_column.get(j),id.
 * value3_column.get(j), id.value4_column.get(j),id.value5_column.get(j));
 * 
 * TestLogger.testMessage(sqlString);
 * 
 * List<Object> dbres = db.dbResult(sqlString);
 * 
 * NotepadWriter.writerr(dbres,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation, "db");
 * 
 * List<Object> apires = convertApiResponseTokeyValuePairs(response);
 * 
 * FileComparisonHighlighter.compareFiles(id.api_name1.get(j)+"_s.no"+id.
 * s_no_column.get(j)+"_repeation_No"+counter_repeatation,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * TextfileComparision.compareFiles(id.api_name1.get(j)+"_s.no"+id.s_no_column.
 * get(j)+"_repeation_No"+counter_repeatation,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * Boolean
 * comparetextfiles=NotepadWriter.compareTextfiles(id.api_name1.get(j)+"_s.no"+
 * id.s_no_column.get(j)+"_repeation_No"+counter_repeatation,
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation); if(comparetextfiles) {
 * ExtentsReport.testFail(id.api_name1.get(j) + " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * "  &  Content of the API Response and DB Response both files are matched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * number_of_contentEquals_passed_testcases++;
 * 
 * } else {
 * NotepadWriter.collectRespone(id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j
 * )+"_repeation_No"+counter_repeatation, response.asString());
 * 
 * 
 * ExtentsReport.testInfo(
 * "The S.No of test case executed is :"+id.s_no_column.get(j)
 * +" & Testcase id is: "+id.tc_id.get(j)+" & Number of repetation: "
 * +counter_repeatation); ExtentsReport.testFail( id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are mis matched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * TestLogger.testMessage("The S.No of test case executed is :"+id.s_no_column.
 * get(j)+" & Testcase id is: "+id.tc_id.get(j)+" & Number of repetation: "
 * +counter_repeatation);
 * 
 * TestLogger.errorMessage(id.api_name1.get(j) + " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are mis matched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation); Reporter.log(id.api_name1.get(j) +
 * " Respone code is  :" +
 * response.getStatusCode()+" VS Expected Response code is :"+id.
 * expected_response_code.get(j)+
 * " &  Content of the API Response and DB Response both files are mis matched : "
 * +id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation+" VS "+
 * id.api_name1.get(j)+"_s.no"+id.s_no_column.get(j)+"_repeation_No"+
 * counter_repeatation);
 * 
 * 
 * 
 * 
 * 
 * number_of_contentNotEquals_passed_testcases++; }
 * 
 * if (dbres.size() == apires.size()) {
 * 
 * int counter = 0; for (int k = 0; k < apires.size(); k++) { if
 * (dbres.get(k).equals(apires.get(k))) {
 * 
 * } else { counter = counter + 1; } }
 * 
 * 
 * }
 * 
 * else { ExtentsReport.
 * testInfo("The api response key value pairs count is not machting with the db result key value pairs"
 * ); TestLogger.
 * errorMessage("The api response key value pairs count is not machting with the db result key value pairs"
 * ); Reporter.
 * log("The api response key value pairs count is not machting with the db result key value pairs"
 * );
 * 
 * 
 * }
 * 
 * }
 * 
 * 
 * } counter_repeatation=counter_repeatation+1; number_of_testcases=
 * number_of_testcases+1; }
 * 
 * }}}
 * 
 * } } catch (NumberFormatException e) {
 * 
 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
 * +", An exception occured while run the kshapi service: "+ e.getMessage());
 * Reporter.log(" Method Name is : " + nameofCurrMethod +
 * "An exception occured while run the kshapi service: "+ e.getMessage());
 * 
 * 
 * }
 * 
 * }
 * 
 * 
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
 * 
 * 
 * public void extractfeildvalues(String f1, String f2, String f3, String f4,
 * String v1, String v2, String v3, String v4, JSONObject obj) {
 * 
 * try { if (!f1.isEmpty()) { if (obj.containsKey(f1)) { obj.put(f1, v1); } } if
 * (!f2.isEmpty()) { if (obj.containsKey(f2)) { obj.put(f2, v2);
 * 
 * } } if (!f3.isEmpty()) { if (obj.containsKey(f3)) { obj.put(f3, v3); } } if
 * (!f4.isEmpty()) { if (obj.containsKey(f4)) { obj.put(f4, v4); } } } catch
 * (Exception e) {
 * 
 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while extracting field values: "+ e.getMessage());
 * Reporter.log(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while extracting field values: "+ e.getMessage());
 * 
 * } }
 * 
 * This method is sued to convert into the API response into key value pairs
 * 
 * @ Param apiResponse: this is the api Response
 * 
 * 
 * public List<Object> convertApiResponseTokeyValuePairs(Response apiResponse) {
 * 
 * List<Object> sortedList = null; try { List<?> itemsCount =
 * apiResponse.path("$"); String jsonitem[] = new String[itemsCount.size()]; for
 * (int a = 0; a < itemsCount.size(); a++) { jsonitem[a] =
 * itemsCount.get(a).toString(); } String itemvalue = null; String[] itemvalue2
 * = null; List<String> item = new ArrayList<String>(); for (int b = 0; b <
 * jsonitem.length; b++) { itemvalue = jsonitem[b].substring(1,
 * jsonitem[b].length() - 1); itemvalue2 = itemvalue.split(","); for (int c = 0;
 * c < itemvalue2.length; c++) { item.add(itemvalue2[c]); } } sortedList =
 * item.stream().sorted().collect(Collectors.toList()); } catch (Exception e) {
 * 
 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while converting the api json response into key and values pairs: "
 * + e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while converting the api json response into key and values pairs: "
 * + e.getMessage());
 * 
 * 
 * } return sortedList;
 * 
 * }
 * 
 * 
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
 * 
 * public String StringParameterization(String anystring,String f1,String
 * f2,String f3,String f4,String f5,String v1,String v2,String v3,String
 * v4,String v5) {
 * 
 * String modifiedString=anystring;
 * 
 * try { if (modifiedString.contains(f1)) { modifiedString =
 * modifiedString.replace(f1, v1); } if (modifiedString.contains(f2)) {
 * modifiedString = modifiedString.replace(f2, v2);
 * 
 * } if (modifiedString.contains(f3)) { modifiedString =
 * modifiedString.replace(f3, v3);
 * 
 * } if (modifiedString.contains(f4)) { modifiedString =
 * modifiedString.replace(f4, v4);
 * 
 * } if (modifiedString.contains(f5)) { modifiedString =
 * modifiedString.replace(f5, v5);
 * 
 * } } catch (Exception e) {
 * 
 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
 * Reporter.log(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while doing the string parameterization: " +
 * e.getMessage()); TestLogger.
 * errorMessage("An exception occured while doing the string parameterization: "
 * + e.getMessage()); } return modifiedString;
 * 
 * }
 * 
 * 
 * This method is used to concat the metadata file endpoint with inputdata file
 * Endpoint value
 * 
 * @Param meataEndpoint :End point point API Servicein meata data file
 * 
 * @Param inputEndpoint : End point value in input data file
 * 
 * 
 * public String metaInputEndpotintConcatnation(String metaEndpoint, String
 * inputEndpoint) {
 * 
 * String tempinputEndpoint = metaEndpoint; try { if (!inputEndpoint.isEmpty())
 * {
 * 
 * tempinputEndpoint = tempinputEndpoint.concat(inputEndpoint); } } catch
 * (Exception e) {
 * 
 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while meta data endpoint concatnation with inputdata file Endpoint value: "
 * + e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
 * ",An exception occured while meta data endpoint concatnation with inputdata file Endpoint value: "
 * + e.getMessage()); } return tempinputEndpoint; }
 * 
 * }
 */