package com.abb.dias.api.automation.core.report;

import java.io.File;

import org.testng.ITestResult;
import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentsReport {

	static  ExcelReader reader=new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
	static  String htmlreportLocation=reader.getValuefromConfigExcel("htmlreport");
	static ExtentReports report;
	public static  ExtentTest test;
	

	public static ExtentReports getReportObj() {

		return report; 
	}


	public static ExtentTest  getExtentTestObj(){
		return test;
	}
	
	/*
	    * This method is used to configure the report before test execution starts
	    */

	public static   ExtentReports configureReport() {
		try {
			report=new ExtentReports(new File(htmlreportLocation)+"\\ExtentReportResult.html");
			report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			
			
			//report=new ExtentReports(new File(htmlreportLocation)+"/ExtentReportResult.html");
		}
		catch(Exception e){

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while configuring report:" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while configuring report:" + e.getMessage());
		}
		return report;

	}
	/*
	    * This method is used to log the Fail message in Report
	    */
	public static void testFail(String methodName) {
		try {
			test.log(LogStatus.FAIL, methodName);
		}
		catch(Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging fail status in report: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging fail status in report: " + e.getMessage());	
		}
	}
	
	/*
	    * This method is used to log the Fail message in Report
	    */
	public static void testSkip(String methodName) {
		try {
			test.log(LogStatus.SKIP, methodName);
		}
		catch(Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging skip status in report: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging skip status in report: " + e.getMessage());	
		}
	}
	
	
	/*
	    * This method is used to log test name  message in Report
	    */
	public static void startTest(String testName) {
		try {
			test= report.startTest(testName);
		}
		catch(Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging start status in report:" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging start status in report: " + e.getMessage());
		}

	}
	/*
	    * This method is used to log the Info message in Report
	    */

	public static void testInfo(String testName) {
		try {
			test.log(LogStatus.INFO, testName);
		}
		catch(Exception e) {
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Info status in report: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Info status in report: " + e.getMessage());		
		}
	}
	
	/*
	    * This method is used to log the Error message in Report
	    */
	public static void testError(String testName) {
		try {
			test.log(LogStatus.ERROR, testName);
		}
		catch(Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging fail Error in report: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Error status in report: " + e.getMessage());	
		}

	}
	
	   /*
	    * This method is used to log the Warning message in Report
	    */

	public static void testWarnMessage(String testName) {
		try {
			test.log(LogStatus.WARNING, testName);
		}
		catch(Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Warning status in report: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Warning status in report: " + e.getMessage());	
		}
	}
   /*
    * This method is used to log the pass message in Report
    */
	public static void testPasedMessage(String testName) {
		try {
			test.log(LogStatus.PASS, testName);
		}
		catch(Exception e){
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Pass status in report: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured when logging Pass status in report: " + e.getMessage());	

		}
	}
	
   /*
    * This Method is used to flush once Test Execution is Finished
    */
	public static void endTest() {
		try {
			//	report.endTest(test);
			report.flush();
		}
		catch(Exception e) {
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while flushing  report:" + e.getMessage());
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while flushing  report:" + e.getMessage());
		}
	}



  /*
   * Thi method is used to get the method resuslt once its executed
   */
	public static void getResult(ITestResult result, String methodName) {

		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(LogStatus.FAIL, "Test case Failed check the details in Test Runner logs Tab or Lo4j logs");

				//test.log(LogStatus.WARNING, "API Log Link : <a href='logslocation'>API LINK</a>");
				int counter=1;
				for (String logs : Reporter.getOutput()) {
					report.setTestRunnerOutput(counter+". "+logs);
					System.out.print("");
					counter++;
				}

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				//test.log(LogStatus.PASS, methodName + " PASSED");
				int counter=1;
				for (String logs : Reporter.getOutput()) {
					report.setTestRunnerOutput(counter+". "+logs);
					System.out.print("");
					counter++;

				}

			} else if(result.getStatus()==ITestResult.SKIP){
				test.log(LogStatus.SKIP, " Test Skipped");
				int counter=1;
				for (String logs : Reporter.getOutput()) {
					report.setTestRunnerOutput(counter+". "+logs);
					System.out.print("");
					counter++;

				}


			}
		} catch (Exception e) {
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage("Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while getting result:" + e.getMessage());
			Reporter.log("Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while getting result:" + e.getMessage());


		}
	}



}
