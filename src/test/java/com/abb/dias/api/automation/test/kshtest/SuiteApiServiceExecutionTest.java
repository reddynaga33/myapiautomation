package com.abb.dias.api.automation.test.kshtest;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.abb.dias.api.automation.core.copyfolders.Copydirectory;
import com.abb.dias.api.automation.core.report.ExtentsReport;
import com.abb.dias.api.automation.core.txtfilewriter.NotepadWriter;
import com.abb.dias.api.automation.page.kshpages.KshApiServicesExecutionPage;
import com.abb.dias.api.automation.page.kshpages.SuiteApiServiceExecutionPage;

public class SuiteApiServiceExecutionTest extends  SuiteApiServiceExecutionPage {
	
	
	
	@BeforeSuite
	public void beforetest() {
		NotepadWriter.deleteResultfolder();
		//ExtentReport.configureReport();
		ExtentsReport.configureReport();

	}
	
	@Parameters({"testexecute"})
	@Test
	public void runtest(String testexecute) throws InterruptedException {
		
		
		//executeKshApisServiceS(ExtentsReport.getExtentTestObj(),ExtentsReport.getReportObj());
		executeKshApisServices( testexecute);
		
	}
	
	
	@AfterSuite
	public void aftertest() {
		//ExtentReport.endTest();

		ExtentsReport.endTest();
		Copydirectory.copyFolder();
		
		
	}
	
	
  	@AfterMethod
public void afterMethod(ITestResult result,Method testName) {   		
  		//ExtentReport.getResult(result,testName.getName());
  		ExtentsReport.getResult(result,testName.getName());

  	}
	

	

	
	

}
