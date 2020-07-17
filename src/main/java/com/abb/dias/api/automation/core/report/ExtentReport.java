package com.abb.dias.api.automation.core.report;

public class ExtentReport {
	/*static ExcelReader reader=new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
    static String htmlreportLocation=reader.getValuefromConfigExcel("htmlreport");
	private static ExtentReports extentReport;
	private static ExtentTest reporter;
	private static ExtentHtmlReporter htmlReporter;

	public static ExtentReports getExtentObj() {
		return extentReport;
	}

	public static ExtentTest geTestObj() {
		return reporter;
	}
  
	public static ExtentHtmlReporter htmlReporterObj() {
		return htmlReporter;
	}
	
	public static void configureReport() {
		
		try {
			htmlReporter = new ExtentHtmlReporter(new File(htmlreportLocation)+"\\report.html");
			
			extentReport = new ExtentReports();
			extentReport.attachReporter(htmlReporter);
			htmlReporter.config().setChartVisibilityOnOpen(false);
			htmlReporter.config().setDocumentTitle("DIAS API AUTOMATION");
			htmlReporter.config().setReportName("DIAS API AUTOMATION status");
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		} catch (Exception e) {
			
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			ExtentReport.geTestObj().log(Status.ERROR, " Method Name is : " + nameofCurrMethod
						+ "An exception occured while configuring report:" + e.getMessage());
			TestLogger.errorMessage( "An exception occured while configuring report:" + e.getMessage());
			
		}
	}

	public static void startTest(String methodName) {
		reporter = extentReport.createTest(methodName);
		 
		
	}

	public static void NumberOfPassedTetCases(String methodName) {
		reporter.log(Status.PASS, methodName);
		
	}
	
	public static void NumberOfFailedTetCases(String methodName) {
		reporter.log(Status.FAIL, methodName);
		
	}
	
	public static void NumberOfTetCasesRun(String methodName) {
		reporter.log(Status.INFO, methodName);
		
	}
	
	public static void PrintInfo(String methodName) {
		reporter.log(Status.INFO, methodName);
		
	}
	
	
	public static void getResult(ITestResult result, String methodName) {

		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				reporter.log(Status.FAIL, "Test case Failed due to below issues");
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				reporter.log(Status.PASS, methodName + " PASSED");
			} else {
				reporter.log(Status.SKIP, " Test Skipped");
			}
		} catch (Exception e) {
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			ExtentReport.geTestObj().log(Status.ERROR, " Method Name is : " + nameofCurrMethod
						+ "An exception occured while getting result:" + e.getMessage());
			TestLogger.errorMessage( "An exception occured while geting result:" + e.getMessage());
			
			
		}
	}

	public static void endTest() {
		try {
			extentReport.flush();
		} catch (Exception e) {
					
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			ExtentReport.geTestObj().log(Status.ERROR, " Method Name is : " + nameofCurrMethod
						+ "An exception occured while flush the report:" + e.getMessage());
			TestLogger.errorMessage( "An exception occured while flush report:" + e.getMessage());
				
			
		}
	}

	*/
}
