package com.abb.dias.api.automation.core.copyfolders;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;

public class Copydirectory {
	
	
	
	static  ExcelReader reader=new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
    static  String testExecutionReport=reader.getValuefromConfigExcel("outputfolderrename");
	
    
    /*
     * This method is used to  copy the report and create existing folder with time stamp
     */
		public static void copyFolder( ) {

			String timeStamp = new SimpleDateFormat("-MM-dd-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime( ));
			File srcDir = new File(testExecutionReport);

			String destination = testExecutionReport+timeStamp;
			File destDir = new File(destination);

			try {
			    FileUtils.copyDirectory(srcDir, destDir);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
			
			

}
