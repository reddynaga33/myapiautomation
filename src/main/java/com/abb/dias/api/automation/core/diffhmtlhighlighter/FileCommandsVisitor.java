package com.abb.dias.api.automation.core.diffhmtlhighlighter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.diff.CommandVisitor;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;


public class FileCommandsVisitor implements CommandVisitor<Character>  {
	 
	
	 static ExcelReader exlConfigReader=new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
     static String resultFolder=  exlConfigReader.getValuefromConfigExcel("Resultfolder");
	
		// Spans with red & green highlights to put highlighted characters in HTML
		private static final String DELETION = "<span style=\"background-color: #FB504B\">${text}</span>";
		private static final String INSERTION = "<span style=\"background-color: #45EA85\">${text}</span>";
	 
		private String left = "";
		private String right = "";
	 
		public void visitKeepCommand(Character c) {
			// For new line use <br/> so that in HTML also it shows on next line.
			String toAppend = "\n".equals("" + c) ? "<br/>" : "" + c;
			// KeepCommand means c present in both left & right. So add this to both without
			// any
			// highlight.
			left = left + toAppend;
			right = right + toAppend;
		}
	 
		public void visitInsertCommand(Character c) {
			// For new line use <br/> so that in HTML also it shows on next line.
			String toAppend = "\n".equals("" + c) ? "<br/>" : "" + c;
			// InsertCommand means character is present in right file but not in left. Show
			// with green highlight on right.
			right = right + INSERTION.replace("${text}", "" + toAppend);
		}
	 
		public void visitDeleteCommand(Character c) {
			// For new line use <br/> so that in HTML also it shows on next line.
			String toAppend = "\n".equals("" + c) ? "<br/>" : "" + c;
			// DeleteCommand means character is present in left file but not in right. Show
			// with red highlight on left.
			left = left + DELETION.replace("${text}", "" + toAppend);
		}
	 
		public void generateHTML(String diffLocation) throws IOException {
	 
			// Get template & replace placeholders with left & right variables with actual
			// comparison
			String template = FileUtils.readFileToString(new File(System.getProperty("user.dir")+EnvironmentManager.getHtmlTemplate()), "utf-8");

			String out1 = template.replace("${left}", left);
			String output = out1.replace("${right}", right);
			// Write file to disk.
			FileUtils.write(new File(diffLocation), output, "utf-8");
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

