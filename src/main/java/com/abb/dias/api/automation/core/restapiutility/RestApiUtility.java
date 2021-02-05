package com.abb.dias.api.automation.core.restapiutility;

import java.util.HashMap;

import java.util.Map;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;

/*import java.util.HashMap;

import java.util.Map;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;*/


public class RestApiUtility {

	/*
	 * This method is used to execute the http request such as POST,GET,PUT,DELETE
	 * 
	 * @Param endURL This is endpoint of the api service
	 * 
	 * @Param payLoad This is request body of the http request
	 * 
	 * @Param httpMethod This is method of the http request such as POST OR GET OR
	 * PUT OR DELETE
	 */
	public Response executeHttpRequest(String endURL, String payLoad, String httpMethod, String testname) {

		//public Response executeHttpRequest(String endURL, JSONObject payLoad, String httpMethod) {

		//ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
		//String baseURL = reader.getValuefromConfigExcel("Base URL");

		ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
		String baseURL = null;
		if(testname.contains(EnvironmentManager.getKshServiceName().trim().toLowerCase())) {
			baseURL = reader.getValuefromConfigExcel(EnvironmentManager.getKshURL());
			System.out.println(baseURL);
		}
		if(testname.contains(EnvironmentManager.getPhServiceName().trim().toLowerCase())) {
			baseURL = reader.getValuefromConfigExcel(EnvironmentManager.getPhURL());
		}

		if(testname.contains("pt1ksh")) {
			baseURL = reader.getValuefromConfigExcel("pt1ksh");
			System.out.println(baseURL);
		}
		if(testname.contains("sysad")) {
			baseURL = reader.getValuefromConfigExcel("SYSAD URL");
			System.out.println("+++++++++++the sysad url is reading");
		}


		RequestSpecification httprequest = null;
		Response getresponse = null;
		Response postresponse = null;
		String token = null;
		String url = null;
		String totalurl=null;

		if (!endURL.isEmpty() || httpMethod.isEmpty()) {
			try {
				totalurl = baseURL + endURL;
				url = baseURL;
				System.out.println("The API URL is: "+totalurl);

				TestLogger.testMessage("The API URL is: "+totalurl);
				Reporter.log("The API URL is: "+totalurl);
				RestAssured.baseURI = url;
				RestAssured.useRelaxedHTTPSValidation();
				httprequest = RestAssured.given();
				if(EnvironmentManager.foundTrue().trim().contains(EnvironmentManager.userProvidedToken().trim())) 
				{
					ExcelReader tokenReader = new ExcelReader(EnvironmentManager.getTokenInfoSheetName().trim());
					token=tokenReader.getValuefromConfigExcel("user token");
				}

				else {
					token = gettoken(testname);
				}			

				httprequest.header("authorization", "Bearer " + token);
				httprequest.header("Content-Type", "application/json");
				if (httpMethod.equalsIgnoreCase("POST") & (payLoad == null)) {
					Thread.sleep(50);
					getresponse = httprequest.request(Method.POST,endURL);

				}
				if (httpMethod.equalsIgnoreCase("POST") & (payLoad != null)) {
					httprequest.body(payLoad);
					Thread.sleep(50);
					getresponse = httprequest.request(Method.POST,endURL);
				}
				if (httpMethod.equalsIgnoreCase("GET") & (payLoad == null)) {
					Thread.sleep(50);
					getresponse = httprequest.request(Method.GET,endURL);
				}

				if (httpMethod.equalsIgnoreCase("GET") & (payLoad != null)) {
					System.out.println("the body put put is::"+payLoad);
					Thread.sleep(50);
					getresponse = httprequest.request(Method.GET,endURL);
				}
				if (httpMethod.equalsIgnoreCase("PUT") & (payLoad == null)) {
					Thread.sleep(50);
					getresponse = httprequest.request(Method.PUT,endURL);

				}
				if (httpMethod.equalsIgnoreCase("PUT") & (payLoad != null)) {
					httprequest.body(payLoad);
					Thread.sleep(50);
					getresponse = httprequest.request(Method.PUT,endURL);
				}

				if (httpMethod.equalsIgnoreCase("DELETE") & (payLoad == null)) {
					Thread.sleep(50);
					getresponse = httprequest.request(Method.DELETE,endURL);
				}
				if (httpMethod.equalsIgnoreCase("DELETE") & (payLoad != null)) {
					httprequest.body(payLoad);
					Thread.sleep(50);
					getresponse = httprequest.request(Method.DELETE,endURL);
				}

				postresponse = getresponse;
				long seconds = getresponse.time();

				TestLogger.testMessage("The respone time is : " + seconds + " Milli seconds");
				TestLogger.testMessage("The response status code for post request is :" + postresponse.getStatusCode());
				Reporter.log("The respone time is : " + seconds + " Milli seconds");
				Reporter.log("The response status code for post request is :" + postresponse.getStatusCode());
				Thread.sleep(50);
			} catch (Exception e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ " ,An exception has occured while generating post request is: " + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ " ,An exception has occured while generating post request is: " + e.getMessage());
			}
		}
		return postresponse;

	}




	/*
	 * This method is used to generate the token response
	 * 
	 * @Param: url endpoint of Token Generation
	 */
	public static Response Token_Generation(String url,String testname) {
		ExcelReader read = new ExcelReader(EnvironmentManager.getTokenInfoSheetName().trim());
		
		String clientSecretParam = null ;
		String grantParam = null ;
		String resourceParam = null ;
		String clientIdParam = null ;

		//ExcelReader read = new ExcelReader(EnvironmentManager.getTokenInfoSheetName().trim());
		//String grantParam = read.getValuefromConfigExcel("grant_type");
		//String resourceParam = read.getValuefromConfigExcel("resource");
		//String clientIdParam = read.getValuefromConfigExcel("client_id");
		//String clientSecretParam = read.getValuefromConfigExcel("client_secret");
       
		RequestSpecification httpRequest = null;
		Response getResponse = null;
		Response postResponse = null;
		Map<String, String> formdata = null;

		try {

			
			if(testname.contains(EnvironmentManager.getOLMServiceName().trim().toLowerCase())) {
				System.out.println("++++++++the token parameters is readed");
				 grantParam = read.getValuefromConfigExcel("olm_grant_type");
				resourceParam = read.getValuefromConfigExcel("olm_resource");
				 clientIdParam = read.getValuefromConfigExcel("olm_client_id");
				 clientSecretParam = read.getValuefromConfigExcel("olm_client_secret");

				}
			else {
				 clientSecretParam = read.getValuefromConfigExcel("client_secret");
				 grantParam = read.getValuefromConfigExcel("grant_type");
				resourceParam = read.getValuefromConfigExcel("resource");
				 clientIdParam = read.getValuefromConfigExcel("client_id");
				}
			

          if(testname.contains(EnvironmentManager.getOLMServiceName().trim().toLowerCase())) {
        	  System.out.println("done");
			formdata = new HashMap<String, String>();
			formdata.put("grant_type", grantParam);
			formdata.put("resource",resourceParam);
			formdata.put("client_id",clientIdParam);
			formdata.put("client_secret", clientSecretParam);
          }
          else {
        	  formdata = new HashMap<String, String>();
  			formdata.put("grant_type", grantParam);
  			formdata.put("resource",resourceParam);
  			formdata.put("client_id",clientIdParam);
  			formdata.put("client_secret", clientSecretParam);
        	  
          }


			RestAssured.baseURI = url;
			httpRequest = RestAssured.given().params(formdata);
			getResponse = httpRequest.request(Method.POST);
			postResponse = getResponse;
			long seconds = getResponse.getTime();

			TestLogger.testMessage("The Token Response time is:" + seconds + " Milli seconds");
			TestLogger.testMessage("The Response status code for Token generation API is:" + postResponse.getStatusCode());
			System.out.println("The Response status code for Token generation API is:" + postResponse.getStatusCode());

			Reporter.log("The Token Response time is:" + seconds + " Milli seconds");
			Reporter.log("The Response status code for Token generation API is:" + postResponse.getStatusCode());
			Thread.sleep(50);
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating token: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating token: " + e.getMessage());

		}
		return postResponse;
	}

	/*
	 * This method is used to extract the token from token response
	 */
	public static String gettoken(String testname) {

		ExcelReader read = new ExcelReader(EnvironmentManager.getTokenInfoSheetName().trim());
		String tokenURL;
		//String tokenURL = read.getValuefromConfigExcel("Token URL");
		Response tokenresponsebody = null;
		String access_token = null;
		try {
			if(testname.contains(EnvironmentManager.getOLMServiceName().trim().toLowerCase())) {
				tokenURL=read.getValuefromConfigExcel("olm_Token URL");
				System.out.println("+++++++the olm_token url is reading from excel");
			}
			else {
				
				tokenURL=read.getValuefromConfigExcel("Token URL");
			}
			
			tokenresponsebody = Token_Generation(tokenURL,testname);
			access_token = tokenresponsebody.jsonPath().getJsonObject("access_token");
			Thread.sleep(200);
			TestLogger.testMessage("The Token generated sucessfully:" + access_token);

		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while accessing the Token: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while accessing the Token: " + e.getMessage());
		}
		return access_token;
	}

	/*
	 * This method will perform the http POST request with http request body
	 * 
	 * @Param endUrl This is the end point of the api service
	 * 
	 * @Param payLoad This is the request body of the http request
	 */
	public Response Post(String endURL, JSONObject payLoad,String testname) {
		ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
		String baseURL = reader.getValuefromConfigExcel("Base URL");
		System.out.println("The Base URL is" + baseURL);
		RequestSpecification httprequest = null;
		Response getresponse = null;
		Response postresponse = null;
		String token = null;
		String url = null;
		try {
			url = baseURL + endURL;
			RestAssured.baseURI = url;
			System.out.println("..." + url);
			httprequest = RestAssured.given();
			token = gettoken(testname);
			httprequest.header("authorization", "Bearer " + token);
			httprequest.body(payLoad.toJSONString());
			httprequest.header("Content-Type", "application/json");
			getresponse = httprequest.request(Method.POST);
			postresponse = getresponse;
			long seconds = getresponse.time();
			TestLogger.testMessage("The respone time is : " + seconds + " Milli seconds");
			TestLogger.testMessage("The response status code for post request is :" + postresponse.getStatusCode());
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating post request: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating post request:" + e.getMessage());

		}
		return postresponse;
	}



	/*
	 * This method perform the Http post request function
	 * 
	 * @Param endURL this the end point of the api service
	 */
	public Response Post(String endURL,String testname) {

		ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
		String baseURL = reader.getValuefromConfigExcel("Base URL");
		RequestSpecification httprequest = null;
		Response getresponse = null;
		Response postresponse = null;
		String token = null;
		String url = null;
		try {
			url = baseURL + endURL;
			RestAssured.baseURI = url;
			System.out.println("..." + url);
			httprequest = RestAssured.given();
			token = gettoken(testname);
			httprequest.header("authorization", "Bearer " + token);
			httprequest.header("Content-Type", "application/json");
			getresponse = httprequest.request(Method.POST);
			postresponse = getresponse;
			long seconds = getresponse.time();

			TestLogger.testMessage("The respone time is : " + seconds + " Milli seconds");
			TestLogger.testMessage("The response status code for post request is :" + postresponse.getStatusCode());

		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating post request is: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating post request is: " + e.getMessage());
		}
		return postresponse;
	}



	/*
	 * This method will perform the http GET request operation
	 * 
	 * @Param endUrl This is the end point of the api service
	 */
	public Response Get(String endURL,String testname) {
		ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
		String baseURL = reader.getValuefromConfigExcel("Base URL");
		RequestSpecification httprequest = null;
		Response getresponse = null;
		Response postresponse = null;
		String token = null;
		String url = null;
		try {
			url = baseURL + endURL;
			RestAssured.baseURI = url;
			System.out.println("..." + url);
			httprequest = RestAssured.given();
			token = gettoken(testname);
			httprequest.header("authorization", "Bearer " + token);
			httprequest.header("Content-Type", "application/json");
			getresponse = httprequest.request(Method.GET);
			postresponse = getresponse;
			long seconds = getresponse.time();
			TestLogger.testMessage("The respone time is : " + seconds + " Milli seconds");
			TestLogger.testMessage("The response status code for GET request is :" + postresponse.getStatusCode());
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception has occured while generating GET request is: "  + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+" ,An exception has occured while generating GET request is: "  + e.getMessage());
		}
		return postresponse;
	}



}
