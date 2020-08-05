/*
 * package com.abb.dias.api.automation.test.kshtest;
 * 
 * import java.lang.reflect.Method;
 * 
 * import org.testng.ITestResult; import org.testng.annotations.AfterMethod;
 * import org.testng.annotations.AfterTest; import
 * org.testng.annotations.BeforeTest; import org.testng.annotations.Test;
 * 
 * import com.abb.dias.api.automation.core.report.ExtentsReport; import
 * com.abb.dias.api.automation.page.kshpages.KshDemo;
 * 
 * public class KshDemoTest extends KshDemo {
 * 
 * 
 * 
 * @BeforeTest public void beforetest() {
 * 
 * //ExtentReport.configureReport(); ExtentsReport.configureReport();
 * 
 * }
 * 
 * @Test public void runtest() throws InterruptedException {
 * 
 * 
 * //executeKshApisServiceS(ExtentsReport.getExtentTestObj(),ExtentsReport.
 * getReportObj()); executeKshApisServiceS();
 * 
 * }
 * 
 * 
 * @AfterTest public void aftertest() { //ExtentReport.endTest();
 * 
 * ExtentsReport.endTest();
 * 
 * 
 * }
 * 
 * 
 * @AfterMethod public void afterMethod(ITestResult result,Method testName) {
 * //ExtentReport.getResult(result,testName.getName());
 * ExtentsReport.getResult(result,testName.getName());
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */