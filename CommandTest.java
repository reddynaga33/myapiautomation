package com.abb.dias.api.automation.nodemodules;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandTest {

	public static void main(String[] args) {
		
			
		
		String arg = "select * from ICMPCM.D_EQI  order by 1 asc OFFSET 0 ROWS Fetch first 100 rows ONLY";
		
		try
        {  
        
       //Process process=Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"node javascript_node.js \""+ arg + "\"");
       Process process=Runtime.getRuntime().exec("node dbnodejs_tet.js \""+ arg + "\"");
       System.out.println("Waiting for process...");
       process.waitFor();
       System.out.println("process completed...");
       

        } 
        catch (Exception e) 
        { 
            System.out.println("Something Wrong "); 
            e.printStackTrace(); 
        } 
	}
}

