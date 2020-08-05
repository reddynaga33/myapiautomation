/*
 * package com.abb.dias.api.automation.test.kshtest;
 * 
 * import java.lang.reflect.Method;
 * 
 * 
 * 
 * import org.testng.ITestResult; import org.testng.annotations.AfterMethod;
 * import org.testng.annotations.AfterTest; import
 * org.testng.annotations.BeforeTest; import org.testng.annotations.Test;
 * 
 * import com.abb.dias.api.automation.core.report.ExtentsReport; import
 * com.abb.dias.api.automation.page.kshpages.KshApiServicesPage;
 * 
 * import io.restassured.RestAssured; import io.restassured.config.SSLConfig;
 * 
 * public class KshApiServicesTest extends KshApiServicesPage{
 * 
 * 
 * @BeforeTest public void beforetest() {
 * 
 * ExtentsReport.configureReport();
 * 
 * }
 * 
 * @Test public void runtest() throws InterruptedException {
 * 
 * executeKshApisServices();
 * 
 * }
 * 
 * 
 * @AfterTest public void aftertest() {
 * 
 * ExtentsReport.endTest();
 * 
 * 
 * }
 * 
 * @AfterMethod //@AfterMethod public void afterMethod(ITestResult result,Method
 * testName) { ExtentsReport.getResult(result,testName.getName());
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * }
 */