package com.abb.dias.api.automation.core.txtfilewriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;

public class NotepadWriter {
	
	
	// ExtentTest test;
		static ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
		static String folderLocation = reader.getValuefromConfigExcel("Resultfolder");
		static String ResFolderLocation = reader.getValuefromConfigExcel("Responsefolder");
		static String apiresultLocation = reader.getValuefromConfigExcel("apiresultfolder");
		static String dbresultLocation = reader.getValuefromConfigExcel("dbresultfolder");



		/*
		 * This method is sued to dele the existe reporting folders
		 * 
		 */
		public static void deleteResultfolder() {


			File file1 = null;
			File file2 = null;
			File file3 = null;
			File file4 = null;
			try {
				file1 = new File(folderLocation);
				file2 = new File(ResFolderLocation);
				file3 = new File(apiresultLocation);
				file4 = new File(dbresultLocation);

				if(file1.exists()) {

					FileUtils.cleanDirectory(file1);
					FileUtils.deleteDirectory(file1);

				}
				if(file2.exists()) {

					FileUtils.cleanDirectory(file2);
					FileUtils.deleteDirectory(file2);

				}	


				if(file3.exists()) {

					FileUtils.cleanDirectory(file3);
					FileUtils.deleteDirectory(file3);

				}	



				if(file4.exists()) {

					FileUtils.cleanDirectory(file4);
					FileUtils.deleteDirectory(file4);

				}	

				/*if (file1.exists() || file2.exists() || file3.exists() || file4.exists()) {

					try {
					FileUtils.cleanDirectory(file1);
						FileUtils.cleanDirectory(file2);
						FileUtils.cleanDirectory(file3);
						FileUtils.cleanDirectory(file4);
					} catch (IOException e) {
						String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
						ExtentReport.geTestObj().log(Status.FAIL, " Method Name is : " + nameofCurrMethod
								+ "   An exception occured while deleting the report folders" + e.getMessage());
					}
					try {
						FileUtils.deleteDirectory(file1);
						FileUtils.deleteDirectory(file2);
						FileUtils.deleteDirectory(file3);
						FileUtils.deleteDirectory(file4);
					} catch (IOException e) {

						e.printStackTrace();
					}
					//file1.mkdir();
					//file2.mkdir();
				} else {
				      //file1.mkdir();
					//file2.mkdir();
					//file1.mkdir();
					//file2.mkdir();
				}*/
			} catch (Exception e) {


				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "An exception has occured while deleting Result Folder: "  + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"An exception has occured while deleting Result Folder: "  + e.getMessage());
			}
		}

		/*
		 * This method is sued to write the list object of items into text file
		 * 
		 * @Param writeobject This list object to provide
		 * 
		 * @Param textfileName: This is the name of the text file to write
		 * 
		 * @Param whichResponse: This is wether to write api or db response
		 */

		public static void writer(List<Object> writeobject, String texfileName, String whichResponse) {

			FileWriter fw = null;
			File file = null;
			try {
				file = new File(folderLocation);
				file.mkdir();
				if (whichResponse.contains("api")) {
					fw = new FileWriter(file + "\\api_" + texfileName + ".txt");
					//fw = new FileWriter(file + "/api_" + texfileName + ".txt");

					for (int i = 0; i < writeobject.size(); i++) {
						fw.write(writeobject.get(i) + "\r\n");
					}
				}
				if (whichResponse.contains("db")) {
					fw = new FileWriter(file + "\\db_" + texfileName + ".txt");
					//fw = new FileWriter(file + "/db_" + texfileName + ".txt");

					for (int i = 0; i < writeobject.size(); i++) {
						fw.write(writeobject.get(i) + "\r\n");
					}
				}
			} catch (Exception e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "An exception occued while wirting the list of items into text file: "  + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"An exception occued while wirting the list of items into text file: "  + e.getMessage());


			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
					TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
							+ "An exception occured while closing text file:"  + e.getMessage());
					Reporter.log(" Method Name is : " + nameofCurrMethod
							+"An exception occured while closing the text file:"  + e.getMessage());

				}
			}
		}

		/*
		 * This method is sued to write the list object of items into text file
		 * 
		 * @Param writeobject This list object to provide
		 * 
		 * @Param textfileName: This is the name of the text file to write
		 * 
		 * @Param whichResponse: This is wether to write api or db response
		 */
		public static void writerr(List<Object> writeobject, String texfileName, String whichResponse) {

			FileWriter fw = null;
			File apiresultfoldr = null;
			File dbresulfoldr = null;
			try {

				apiresultfoldr = new File(apiresultLocation);
				dbresulfoldr = new File(dbresultLocation);
				apiresultfoldr.mkdir();
				dbresulfoldr.mkdir();

				if (whichResponse.contains("api")) {

					//fw = new FileWriter(apiresultfoldr + "\\" + texfileName + ".txt");
					fw = new FileWriter(apiresultfoldr + "/" + texfileName + ".txt");

					for (int i = 0; i < writeobject.size(); i++) {
						fw.write(writeobject.get(i) + "\r\n");
					}
				}
				if (whichResponse.contains("db")) {
					//fw = new FileWriter(dbresulfoldr + "\\" + texfileName + ".txt");
					fw = new FileWriter(dbresulfoldr + "/" + texfileName + ".txt");

					for (int i = 0; i < writeobject.size(); i++) {
						fw.write(writeobject.get(i) + "\r\n");
					}
				}
			} catch (Exception e) {



				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+" An exception occued while wirting the list of items into text file:"  + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"An exception occued while wirting the list of items into text file: "  + e.getMessage());



			} finally {
				try {
					fw.close();
				} catch (IOException e) {


					String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
					TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
							+" An exception occued while wirting the list of items into text file:"  + e.getMessage());
					Reporter.log(" Method Name is : " + nameofCurrMethod
							+"An exception occued while wirting the list of items into text file: "  + e.getMessage());

				}
			}
		}

		/*
		 * This method is used to collect the api response
		 * 
		 * @Param textfileName: This name of the text file given
		 * 
		 * @Para apiResponse: api response
		 */
		public static void collectRespone(String texfileName, String apiResponse) {

			FileWriter fw = null;
			File file = null;
			try {
				file = new File(ResFolderLocation);
				file.mkdir();
				fw = new FileWriter(file + "\\" + texfileName + ".txt");
				//fw = new FileWriter(file + "/" + texfileName + ".txt");

				fw.write(apiResponse);
			} catch (Exception e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+"An exception occured while writing the api respone into text file: "  + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"An exception occured while writing the api respone into text file: "  + e.getMessage());
			} finally {
				try {
					fw.close();
				} catch (IOException e) {
					String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
					TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
							+"An exception occrued while closing  writing the api Response into text file: "   + e.getMessage());
					Reporter.log(" Method Name is : " + nameofCurrMethod
							+"An exception occrued while closing  writing the api Response into text file: "  + e.getMessage());
				}
			}
		}

		/*
		 * This method is sued to write the api repsone into text file
		 * 
		 * @Param writeobject This list object to provide
		 * 
		 * @Param textfileName: This is the name of the text file to write
		 * 
		 * @Param whichResponse: This is wether to write api or db response
		 */
		public static void apiresultwriter(List<Object> writeobject, String texfileName, String whichResponse) {

			FileWriter fw = null;
			File file = null;
			try {
				file = new File(apiresultLocation);
				file.mkdir();
				fw = new FileWriter(file + "\\" + texfileName + ".txt");
				//fw = new FileWriter(file + "/" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			} catch (Exception e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+"An exception occrued while writing the api result into text file: "   + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"An exception occrued while writing the api result into text file: "  + e.getMessage());
			} finally {
				try {
					fw.close();
				} catch (IOException e) {

					String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
					TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
							+"An exception occrued while writing the api result into text file: " + e.getMessage());
					Reporter.log(" Method Name is : " + nameofCurrMethod
							+"An exception occrued while writing the api result into text file: " + e.getMessage());
				}
			}
		}

		/*
		 * This method is used to compare the both text files content
		 * 
		 * @Param textfileNameOne : This is the text file path of the first text file
		 * 
		 * @Param textfileNameTwo: This is the text file path of the second text file
		 */

		public static void dbresultwriter(List<Object> writeobject, String texfileName, String whichResponse) {

			FileWriter fw = null;
			File file = null;
			try {
				file = new File(dbresultLocation);
				file.mkdir();
				fw = new FileWriter(file + "\\" + texfileName + ".txt");
				//fw = new FileWriter(file + "/" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			} catch (Exception e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+"An exception occrued while writing the db result into text file: " + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"An exception occrued while writing the db result into text file: " + e.getMessage());

			} finally {
				try {
					fw.close();
				} catch (IOException e) {

					String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
					TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
							+"An exception occured while closing the text file :" + e.getMessage());
					Reporter.log(" Method Name is : " + nameofCurrMethod
							+"An exception occured while closing the text file  " + e.getMessage());

				}
			}
		}

		/*
		 * This method is used to compare the both text files content
		 * 
		 * @Param textfileNameOne : This is the text file path of the first text file
		 * 
		 * @Param textfileNameTwo: This is the text file path of the second text file
		 */

		public static boolean compareTextfiles(String texfileNameOne, String texfileNameTwo) {

			File fileOne = new File(apiresultLocation);
			File fileTwo = new File(dbresultLocation);
			boolean compareResult = false;
			try {
				File f1 = new File(fileOne + "\\" + texfileNameOne + ".txt");
				File f2 = new File(fileTwo + "\\" + texfileNameTwo + ".txt");
				//File f1 = new File(fileOne + "/" + texfileNameOne + ".txt");
				//File f2 = new File(fileTwo + "/" + texfileNameTwo + ".txt");
				compareResult = FileUtils.contentEquals(f2, f1);
				System.out.println("Are the files same? " + compareResult);

			} catch (FileNotFoundException e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+"File not founc exception  occured while comapre the both (API and DB ) the text files: " + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"File not founc exception  occured while comapre the both(API and DB )  the text files: " + e.getMessage());

			} catch (IOException e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+"File not founc exception  occured while comapre the both (API and DB ) the text files: " + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+"File not founc exception  occured while comapre the both(API and DB )  the text files: " + e.getMessage());


			}

			return compareResult;
		}
	

}
