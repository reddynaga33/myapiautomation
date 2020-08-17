package com.abb.dias.api.automtion.core.jsonreader;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.log.TestLogger;
import com.abb.dias.api.automation.core.report.ExtentsReport;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class JsonReader {
	static JSONParser jsonParser;
	static FileReader reader;
	static JSONObject detailobject;
	static Object object;
	/**
	 * This method will read the file from the path given
	 */
	public JsonReader() {

		jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
		try {
			reader = new FileReader(EnvironmentManager.getDataFromJsonPath());
			object = jsonParser.parse(reader);

		} catch (ParseException e) {




			ExtentsReport.getExtentTestObj().log(LogStatus.ERROR,
					" An exception occured to while parsing the data into jsonformat: " + e.getMessage());
			TestLogger.errorMessage(" An exception occured to while parsing the data into jsonformat: "  + e.getMessage());



		} catch (FileNotFoundException e) {


			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			ExtentsReport.getExtentTestObj().log(LogStatus.INFO,
					"An exception occured in Method Nameis : " + nameofCurrMethod);
			ExtentsReport.getExtentTestObj().log(LogStatus.ERROR,
					" An exception occured to while parsing the data into jsonformat: " + e.getMessage());
			TestLogger.errorMessage(" An exception occured to while parsing the data into jsonformat: "  + e.getMessage());



		}
	}

	/**
	 * Method will return the json object for the respective module
	 * 
	 * @param modulename
	 * @return Jsonobject
	 */
	public static JSONObject getJsonObject(String moduleName) {
		try {
			// object = jsonParser.parse(reader);
			JSONArray jsonList = (JSONArray) object;
			for (int i = 0; i < jsonList.size(); i++) {
				JSONObject moduleObject = (JSONObject) jsonList.get(i);
				if (moduleObject.containsKey(moduleName)) {
					detailobject = (JSONObject) moduleObject.get(moduleName);
				}
			}

		} catch (Exception e) {



			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			ExtentsReport.getExtentTestObj().log(LogStatus.INFO,
					"An exception occured in Method Nameis : " + nameofCurrMethod);
			ExtentsReport.getExtentTestObj().log(LogStatus.ERROR,
					" An exception occured to while getting json object from respective module: " + e.getMessage());
			TestLogger.errorMessage(	" An exception occured to while getting json object from respective module: "  + e.getMessage());





		}

		return detailobject;
	}

	/**
	 * This method will check the value is present for a particular key and will
	 * return the the value
	 * 
	 * @param moduleName
	 *            : Mention the name of the module
	 * @param key
	 *            : Mention the submodule as key
	 * @return string value
	 */
	public String getJsonData(String key) {
		String values = null;
		try {
			values = (String) detailobject.get(key);
			return values;
		} catch (Exception e) {


			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			ExtentsReport.getExtentTestObj().log(LogStatus.INFO,
					"An exception occured in Method Nameis : " + nameofCurrMethod);
			ExtentsReport.getExtentTestObj().log(LogStatus.ERROR,
					" An exception occured did not find the key value of the json object: " + e.getMessage());
			TestLogger.errorMessage( " An exception occured did not find the key value of the json object: "+ e.getMessage());




		}

		return null;
	}

	/*
	 * This method is used to convert the httpreqeust body into json fromat
	 * 
	 * @param requestbody: This is the request json body
	 */
	public static JSONObject convertRequestBodyToJson(String requestbody) {
		Object obj;
		JSONObject json = null;

		if (!requestbody.isEmpty()) {
			JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
			try {
				obj = parser.parse(requestbody.trim());
				json = (JSONObject) obj;
			} catch (ParseException e) {




				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				ExtentsReport.getExtentTestObj().log(LogStatus.INFO,
						"An exception occured in Method Nameis : " + nameofCurrMethod);
				ExtentsReport.getExtentTestObj().log(LogStatus.ERROR,
						" An exception occured to while parsing the converting requesting body to json: " + e.getMessage());
				TestLogger.errorMessage(" An exception occured to while parsing the converting requesting body to json: "+ e.getMessage());







			}
		}

		return json;
	}

}
