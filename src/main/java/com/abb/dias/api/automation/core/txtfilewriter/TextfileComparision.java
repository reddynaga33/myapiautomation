package com.abb.dias.api.automation.core.txtfilewriter;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.text.diff.StringsComparator;

import com.abb.dias.api.automation.core.diffhmtlhighlighter.FileCommandsVisitor;
import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;







public class TextfileComparision {
	
	static ExcelReader reader=new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
    static String folderLocation=reader.getValuefromConfigExcel("Resultfolder");
    static  String ResFolderLocation=reader.getValuefromConfigExcel("Responsefolder");
    static String apiresultLocation=reader.getValuefromConfigExcel("apiresultfolder");
    static  String dbresultLocation=reader.getValuefromConfigExcel("dbresultfolder");
	
	static ExcelReader exlConfigReader=new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
     static String resultFolder=  exlConfigReader.getValuefromConfigExcel("Resultfolder");
	
	public static void compareFiles(String fileOne, String fileTwo)  {
		// Read both files with line iterator.
		LineIterator file1 = null;
		try {
			file1 = FileUtils.lineIterator(new File(apiresultLocation+"\\"+fileOne+".txt"));
			//file1 = FileUtils.lineIterator(new File(apiresultLocation+"/"+fileOne+".txt"), "utf-8");

			
		} catch (IOException e) {
		
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			 TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ " ,An exception occured while reading line by line in file1: "  + e.getMessage());

			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while reading line by line in file1: "  + e.getMessage());
					
					}
		LineIterator file2 = null;
		try {
			file2 = FileUtils.lineIterator(new File(dbresultLocation+"\\"+fileTwo+".txt"),"utf-8");
			//file2 = FileUtils.lineIterator(new File(dbresultLocation+"/"+fileTwo+".txt"), "utf-8");

			
		} catch (IOException e) {
			
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			 TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ " ,An exception occured while reading line by line in file2: "  + e.getMessage());

			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while reading line by line in file2: "  + e.getMessage());
		}
 
		// Initialize visitor.
		FileCommandsVisitor fileCommandsVisitor = new FileCommandsVisitor();
 
		// Read file line by line so that comparison can be done line by line.
		while (file1.hasNext() || file2.hasNext()) {
			/*
			 * In case both files have different number of lines, fill in with empty
			 * strings. Also append newline char at end so next line comparison moves to
			 * next line.
			 */
			String left = (file1.hasNext() ? file1.nextLine() : "") + "\n";
			String right = (file2.hasNext() ? file2.nextLine() : "") + "\n";
 
			// Prepare diff comparator with lines from both files.
			StringsComparator comparator = new StringsComparator(left, right);
 
			if (comparator.getScript().getLCSLength() > (Integer.max(left.length(), right.length()) * 0.4)) {
				/*
				 * If both lines have atleast 40% commonality then only compare with each other
				 * so that they are aligned with each other in final diff HTML.
				 */
				comparator.getScript().visit(fileCommandsVisitor);
			} else {
				/*
				 * If both lines do not have 40% commanlity then compare each with empty line so
				 * that they are not aligned to each other in final diff instead they show up on
				 * separate lines.
				 */
				StringsComparator leftComparator = new StringsComparator(left, "\n");
				leftComparator.getScript().visit(fileCommandsVisitor);
				StringsComparator rightComparator = new StringsComparator("\n", right);
				rightComparator.getScript().visit(fileCommandsVisitor);
			}
		}
 
		try {
			fileCommandsVisitor.generateHTML(resultFolder+"\\"+fileTwo+".html");
			//fileCommandsVisitor.generateHTML(resultFolder+"/"+fileTwo+".html");

		} catch (IOException e) {
			
			 String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			 TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
						+ " ,An exception occured while compare the both the text files: "  + e.getMessage());
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ " ,An exception occured while compare the both the text files: "  + e.getMessage());
					
	
				}
	}	
	
		
	
}
