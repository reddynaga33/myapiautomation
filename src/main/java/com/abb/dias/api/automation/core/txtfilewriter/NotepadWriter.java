package com.abb.dias.api.automation.core.txtfilewriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
	static String htmlreportlocation = reader.getValuefromConfigExcel("htmlreport");

	/*
	 * This method is sued to delete the existing reporting folders
	 * 
	 */
	public static void deleteResultfolder() {

		File file1 = null;
		File file2 = null;
		File file3 = null;
		File file4 = null;
		File file5 = null;
		try {
			file1 = new File(folderLocation);
			file2 = new File(ResFolderLocation);
			file3 = new File(apiresultLocation);
			file4 = new File(dbresultLocation);
			file5 = new File(htmlreportlocation);

			if (file1.exists()) {

				FileUtils.cleanDirectory(file1);
				FileUtils.deleteDirectory(file1);

			}
			if (file2.exists()) {

				FileUtils.cleanDirectory(file2);
				FileUtils.deleteDirectory(file2);

			}

			if (file3.exists()) {

				FileUtils.cleanDirectory(file3);
				FileUtils.deleteDirectory(file3);

			}

			if (file4.exists()) {

				FileUtils.cleanDirectory(file4);
				FileUtils.deleteDirectory(file4);

			}
			/*
			 * if(file5.exists()) {
			 * 
			 * FileUtils.cleanDirectory(file5); FileUtils.deleteDirectory(file5);
			 * 
			 * }
			 */
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();

			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception has occured while deleting Result Folder: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception has occured while deleting Result Folder: " + e.getMessage());
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
				// fw = new FileWriter(file + "/api_" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			}
			if (whichResponse.contains("db")) {
				fw = new FileWriter(file + "\\db_" + texfileName + ".txt");
				// fw = new FileWriter(file + "/db_" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occued while wirting the list of items into text file: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occued while wirting the list of items into text file: " + e.getMessage());

		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "An exception occured while closing text file:" + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "An exception occured while closing the text file:" + e.getMessage());

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
	 * 
	 * public static void writerr(List<Object> writeobject, String texfileName,
	 * String whichResponse) {
	 * 
	 * FileWriter fw = null; File apiresultfoldr = null; File dbresulfoldr = null;
	 * try {
	 * 
	 * apiresultfoldr = new File(apiresultLocation); dbresulfoldr = new
	 * File(dbresultLocation); apiresultfoldr.mkdir(); dbresulfoldr.mkdir();
	 * 
	 * if (whichResponse.contains("api")) {
	 * 
	 * fw = new FileWriter(apiresultfoldr + "\\" + texfileName + ".txt"); //fw = new
	 * FileWriter(apiresultfoldr + "/" + texfileName + ".txt");
	 * 
	 * for (int i = 0; i < writeobject.size(); i++) { fw.write(writeobject.get(i) +
	 * "\r\n"); } } if (whichResponse.contains("db")) { fw = new
	 * FileWriter(dbresulfoldr + "\\" + texfileName + ".txt"); //fw = new
	 * FileWriter(dbresulfoldr + "/" + texfileName + ".txt");
	 * 
	 * for (int i = 0; i < writeobject.size(); i++) { fw.write(writeobject.get(i) +
	 * "\r\n"); } } } catch (Exception e) {
	 * 
	 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
	 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
	 * " An exception occued while wirting the list of items into text file:" +
	 * e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
	 * "An exception occued while wirting the list of items into text file: " +
	 * e.getMessage());
	 * 
	 * } finally { try { fw.close(); } catch (IOException e) {
	 * 
	 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
	 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
	 * " An exception occued while wirting the list of items into text file:" +
	 * e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
	 * "An exception occued while wirting the list of items into text file: " +
	 * e.getMessage());
	 * 
	 * } } }
	 */
	
	
	/*
	 * This method is sued to write the list object of items into text file
	 * 
	 * @Param writeobject This list object to provide
	 * 
	 * @Param textfileName: This is the name of the text file to write
	 * 
	 * @Param whichResponse: This is wether to write api or db response
	 */
	public static void writerr(List<String> writeobject, String texfileName, String whichResponse) {
		//FileWriter fw1 = new FileWriter("filename.txt", Charset.forName("utf-8"));
		FileWriter fw = null;
		File apiresultfoldr = null;
		File dbresulfoldr = null;
		try {
        //  System.out.println("size of the list object is::"+writeobject.get(0));
			apiresultfoldr = new File(apiresultLocation);
			dbresulfoldr = new File(dbresultLocation);
			apiresultfoldr.mkdir();
			dbresulfoldr.mkdir();
			  System.out.println("ddddddddddddddddddddd"+writeobject.toString()); 
			  
		

			if (whichResponse.contains("api")) {

				 fw = new FileWriter(apiresultfoldr + "\\" + texfileName + ".txt");
				//fw = new FileWriter(apiresultfoldr + "/" + texfileName + ".txt");
				 if(writeobject.toString().equals("[]")) {
					 fw.write(writeobject.toString()+ "\r\n");
						
					}
				 else {
				for (int i = 0; i < writeobject.size(); i++) {
					System.out.println("notepad result is::::"+writeobject.get(i));
					fw.write(writeobject.get(i) + "\r\n");
				}
			}
			}
			if (whichResponse.contains("db")) {
				fw = new FileWriter(dbresulfoldr + "\\" + texfileName + ".txt");
				//fw = new FileWriter(dbresulfoldr + "/" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " An exception occued while wirting the list of items into text file:" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occued while wirting the list of items into text file: " + e.getMessage());

		} finally {
			try {
				fw.close();
			} catch (IOException e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ " An exception occued while wirting the list of items into text file:" + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "An exception occued while wirting the list of items into text file: " + e.getMessage());

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
	public static void writerrr(List<Object> writeobject, String texfileName, String whichResponse) {

		FileWriter fw = null;
		File apiresultfoldr = null;
		File dbresulfoldr = null;
		try {

			apiresultfoldr = new File(apiresultLocation);
			dbresulfoldr = new File(dbresultLocation);
			apiresultfoldr.mkdir();
			dbresulfoldr.mkdir();

			if (whichResponse.contains("api")) {

				 fw = new FileWriter(apiresultfoldr + "\\" + texfileName + ".txt");
				//fw = new FileWriter(apiresultfoldr + "/" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			}
			if (whichResponse.contains("db")) {
				fw = new FileWriter(dbresulfoldr + "\\" + texfileName + ".txt");
				//fw = new FileWriter(dbresulfoldr + "/" + texfileName + ".txt");

				for (int i = 0; i < writeobject.size(); i++) {
					fw.write(writeobject.get(i) + "\r\n");
				}
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " An exception occued while wirting the list of items into text file:" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occued while wirting the list of items into text file: " + e.getMessage());

		} finally {
			try {
				fw.close();
			} catch (IOException e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ " An exception occued while wirting the list of items into text file:" + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "An exception occued while wirting the list of items into text file: " + e.getMessage());

			}
		}
	}
	
	
	
	/*
	 * This method is used for check the string pattern match
	 * 
	 * @Param :pattern pattern of the string to match
	 * 
	 */

	public static boolean checkExtraStringPatternMatch(String pattern) {

		if (pattern.equals("0")) {

			return true;
		}

		if (pattern.equals("T00:00:00.000Z")) {

			return true;
		}
		return false;

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
			// fw = new FileWriter(file + "/" + texfileName + ".txt");

			fw.write(apiResponse);
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while writing the api respone into text file: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occured while writing the api respone into text file: " + e.getMessage());
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "An exception occrued while closing  writing the api Response into text file: "
						+ e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "An exception occrued while closing  writing the api Response into text file: "
						+ e.getMessage());
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
			// fw = new FileWriter(file + "/" + texfileName + ".txt");

			for (int i = 0; i < writeobject.size(); i++) {
				fw.write(writeobject.get(i) + "\r\n");
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occrued while writing the api result into text file: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occrued while writing the api result into text file: " + e.getMessage());
		} finally {
			try {
				fw.close();
			} catch (IOException e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "An exception occrued while writing the api result into text file: " + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "An exception occrued while writing the api result into text file: " + e.getMessage());
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
			// fw = new FileWriter(file + "/" + texfileName + ".txt");

			for (int i = 0; i < writeobject.size(); i++) {
				fw.write(writeobject.get(i) + "\r\n");
			}
		} catch (Exception e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "An exception occrued while writing the db result into text file: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "An exception occrued while writing the db result into text file: " + e.getMessage());

		} finally {
			try {
				fw.close();
			} catch (IOException e) {

				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "An exception occured while closing the text file :" + e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "An exception occured while closing the text file  " + e.getMessage());

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
	/*
	 * public static boolean compareTextfiles(String texfileNameOne, String
	 * texfileNameTwo) {
	 * 
	 * File fileOne = new File(apiresultLocation); File fileTwo = new
	 * File(dbresultLocation); boolean compareResult = false; try { File f1 = new
	 * File(fileOne + "\\" + texfileNameOne + ".txt"); File f2 = new File(fileTwo +
	 * "\\" + texfileNameTwo + ".txt"); // File f1 = new File(fileOne + "/" +
	 * texfileNameOne + ".txt"); // File f2 = new File(fileTwo + "/" +
	 * texfileNameTwo + ".txt"); compareResult = FileUtils.contentEquals(f2, f1);
	 * System.out.println("Are the files same? " + compareResult);
	 * 
	 * } catch (FileNotFoundException e) {
	 * 
	 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
	 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
	 * "File not founc exception  occured while comapre the both (API and DB ) the text files: "
	 * + e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
	 * "File not founc exception  occured while comapre the both(API and DB )  the text files: "
	 * + e.getMessage());
	 * 
	 * } catch (IOException e) {
	 * 
	 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
	 * TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod +
	 * "File not founc exception  occured while comapre the both (API and DB ) the text files: "
	 * + e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
	 * "File not founc exception  occured while comapre the both(API and DB )  the text files: "
	 * + e.getMessage());
	 * 
	 * }
	 * 
	 * return compareResult; }
	 */
	
	
	/*
	 * This method is used to compare the both text files content
	 * 
	 * @Param textfileNameOne : This is the text file path of the first text file
	 * 
	 * @Param textfileNameTwo: This is the text file path of the second text file
	 */

	public static boolean compareTextfiles(String texfileNameOne, String texfileNameTwo)  {

		//File fileOne = new File(apiresultLocation);
		//File fileTwo = new File(dbresultLocation);
		boolean compareResult = true;
		 BufferedReader reader1 = null;
		 BufferedReader reader2 = null ;
		try {
			//File f1 = new File(fileOne + "\\" + texfileNameOne + ".txt");
			//File f2 = new File(fileTwo + "\\" + texfileNameTwo + ".txt");
			// File f1 = new File(fileOne + "/" + texfileNameOne + ".txt");
			// File f2 = new File(fileTwo + "/" + texfileNameTwo + ".txt");
			
		

			  FileInputStream fis = new FileInputStream(dbresultLocation+"\\"+texfileNameOne+".txt");
			  InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			  reader1 = new BufferedReader(new FileReader(apiresultLocation+"\\"+texfileNameOne+".txt" ));
			  reader2 = new BufferedReader( isr);
			 
			
			
		//BufferedReader reader1 = new BufferedReader(new FileReader("C:\\Users\\INNAALA\\Desktop\\framewwork\\DIAS-TA-API\\APIAutomationReport\\apiresult\\D_Currency_s.no16_repetition_No1.txt"));
		
		//BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\INNAALA\\Desktop\\framewwork\\DIAS-TA-API\\APIAutomationReport\\dbresult\\D_Currency_s.no16_repetition_No1.txt"));
		
		String line1 = reader1.readLine();
		
		String line2 = reader2.readLine();
		
		boolean areEqual = true;
		
		int lineNum = 1;
		boolean checkequal=true;
		
		while (line1 != null || line2 != null)
		{
			int count=0;
			if(line1 == null || line2 == null)
			{
				areEqual = false;
				
				break;
			}
			else if(! line1.equalsIgnoreCase(line2))
			{
				areEqual = false;
				
				break;
			}
			
			line1 = reader1.readLine();
			
			line2 = reader2.readLine();
			
			lineNum++;
		}
		
		if(areEqual)
		{
			System.out.println("Two files have same content.");
		}
		else
		{
			 compareResult = false;
			//checkequal=false;
			System.out.println("Two files have different content. They differ at line "+lineNum);
			
			System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
		}
		System.out.println(checkequal);
		//reader1.close();
		
		//reader2.close();
			
			
			
			
			
			//compareResult = FileUtils.contentEquals(f2, f1);
			//System.out.println("Are the files same? " + compareResult);

		} catch (FileNotFoundException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "File not founc exception  occured while comapre the both (API and DB ) the text files: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "File not founc exception  occured while comapre the both(API and DB )  the text files: "
					+ e.getMessage());

		} catch (IOException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "File not founc exception  occured while comapre the both (API and DB ) the text files: "
					+ e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "File not founc exception  occured while comapre the both(API and DB )  the text files: "
					+ e.getMessage());

		}
		finally {
			try {
			reader1.close();
			
			reader2.close();
			}catch(IOException e){
				
				String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
				TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ "File not founc exception  occured while comapre the both (API and DB ) the text files: "
						+ e.getMessage());
				Reporter.log(" Method Name is : " + nameofCurrMethod
						+ "File not founc exception  occured while comapre the both(API and DB )  the text files: "
						+ e.getMessage());

				
			}
		}

		return compareResult;
	}


/*
	 * This method is used to check text file exists
	 * 
	 * @Param textfileNameOne : This is the text file path of the first text file
	 * 
	 *
	 */

public static boolean  checkFileExists(String dbTextFileLocation){

     boolean dbTextFilePresent=false;
     File dbTextFile;
     
    dbTextFile=new File(dbresultLocation+"\\"+dbTextFileLocation+".txt");
    
    if(dbTextFile.exists()){
    
     dbTextFilePresent=true;
    }
    
return dbTextFilePresent;
}

}
