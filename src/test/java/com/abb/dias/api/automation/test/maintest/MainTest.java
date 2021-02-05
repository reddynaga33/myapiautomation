package com.abb.dias.api.automation.test.maintest;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class MainTest {

	public static void main(String[] args) {

		
		// Create object of TestNG Class
		TestNG runner=new TestNG();

		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();

		// Add xml file which you have to execute
		//suitefiles.add("C:\\Users\\INNAALA\\Desktop\\framewwork\\DIAS-TA-API\\kshtestng.xml");
		suitefiles.add(System.getProperty("user.dir")+"\\kshtestng.xml");


		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();
		
		
	}

}