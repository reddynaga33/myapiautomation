package com.abb.dias.api.automation.core.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

;

public class TestLogger {
	
	
	public static Logger logger = LogManager.getLogger(TestLogger.class);
	

	
	/**
	 * Based on this method name user knows the which method is executing
	 * @param methodName
	 * Method name
	 */
	public static void methodName(String methodName) {

		logger.info("Method name = " + methodName);
		
	}

	/**
	 * This method will returns the error message
	 * @param methodName
	 */
	public static void errorMessage(String methodName) {
		
		
		logger.error( methodName);
	}

	

	/**
	 * This method will returns the error message
	 * @param methodName
	 */
	public static void fileInfo(Object methodName) {

		logger.info( methodName);

	}



	/**
	 * This method will return the debug message
	 * @param methodName
	 */
	public static void debugMessage(Object methodName) {

		logger.debug(methodName);

	}

	
	/**
	 * Generally this method will be used for Before and After methods and Based
	 * on this method name user knows the which method is executing
	 * @param methodName
	 * Test case name
	 */

	public static void beforORAfterMetod(String methodName) {

		logger.info(methodName);

	}



	/**
	 * This method will help you to identify web-element
	 * @param elementName
	 *  Value of the WebElement
	 */

	public static void elementIdentifier(String elementName) {

		if (elementName != null && !elementName.isEmpty())

			logger.info("Web element = '" + elementName + "' is identified successfully");

	}

	/**
	 * This method will help you to identify the which element was clicked 
	 * @param elementName
	 * Value of the WebElement
	 */

	public static void elementClickIdentifier(String elementName) {

		if (elementName != null && !elementName.isEmpty())

			logger.info("Web element = '" + elementName + "' is identified and clicked");
	}


	/**
	 * This method will help to identify the failed web-elements
	 * @param elementName
	 *  Value of the WebElement
	 */

	public static void elementIdentifierFail(String elementName) {

		if (elementName != null && !elementName.isEmpty()){

			logger.info("Web element Exception message");

			logger.info(elementName);

		}

		logger.info("**********************************************************");
	}

	/**
	 * This method will help to identify the check-boxes 
	 * @param message
	 *  Developer message
	 */

	public static void elementCheckBoxIdentifier(String message) {

		logger.info(message);

	}


	/**
	 * This method will help to identify the failed check-boxes 
	 * @param elementName
	 * Value of the WebElement
	 */

	public static void elementCheckBoxIdentifierFail(String elementName) {

		if (elementName != null && !elementName.isEmpty())

			logger.info("Web element = '" + elementName + "' is not identified");

		logger.info("**********************************************************");

	}



	/**

	 * This method will identify the web-elements
	 * @param elementName
	 *  Value of the WebElement
	 */

	public static void elementSelect(String elementName) {

		if (elementName != null && !elementName.isEmpty())

			logger.info(elementName);
	}



	/**
	 * This method was mostly used in try catch block
	 * @param message
	 * Developer message
	 */
	public static void appInfo(String message) {
		logger.info("*************************** " + "\n" + message);

	}

	/**
	 * This method print/show the user message
	 * @param message
	 * Developer message
	 */
	public static void testMessage(String message) {

		logger.info(message);

		logger.info("**********************************************************");

	}

	
	
}
