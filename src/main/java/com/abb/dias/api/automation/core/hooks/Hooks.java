package com.abb.dias.api.automation.core.hooks;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.abb.dias.api.automation.core.report.ExtentsReport;

//public class Hooks extends ExtentReport {
public class Hooks extends ExtentsReport {
	
	

	
	public void beforeSuite() {
		
		System.out.println("before suite executed");
	}
	
	@BeforeTest
	public void beforeTest() {
		
		ExtentsReport.configureReport();
		
	}
	@AfterTest
	public void afterTet() {
		
		ExtentsReport.endTest();
	}
	public void afterSuite() {
		
		System.out.println("after suite executed");
	}
	
	
	
	
	

	
	
	
}
