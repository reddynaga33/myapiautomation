package com.abb.dias.api.automation.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Reporter;

import com.abb.dias.api.automation.core.envmanager.EnvironmentManager;
import com.abb.dias.api.automation.core.excelreader.ExcelReader;
import com.abb.dias.api.automation.core.log.TestLogger;

public class DatabaseUtility {

	ExcelReader reader = new ExcelReader(EnvironmentManager.getDbConfigSheetName().trim());
	String sqlserverjdbc = reader.getValuefromConfigExcel("sqlserverjdbc");
	String sqlPort = reader.getValuefromConfigExcel("sqlserverport");
	String hostName = reader.getValuefromConfigExcel("hostname");
	String dbName = reader.getValuefromConfigExcel("databasename");
	String dbUser = reader.getValuefromConfigExcel("user");
	String dbPassword = reader.getValuefromConfigExcel("password");
	Connection connection = null;
	Statement statement = null;
	String selectSql = null;
	ResultSet queryResult = null;
	ResultSetMetaData metaData = null;
	String sqlserverstring = sqlserverjdbc + hostName + ":" + sqlPort + ";database=" + dbName;
	//String sqlserverstring = sqlserverjdbc + sqlPort  + ":" + hostName + ";database=" + dbName;


	public void dbConnection() {

		String sqlserverstring = sqlserverjdbc + hostName + ":" + sqlPort + ";database=" + dbName;
		try {
			connection = DriverManager.getConnection(sqlserverstring, dbUser, dbPassword);
			statement = connection.createStatement();


		} catch (SQLException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "   An exception occured to connect DataBase" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ "   An exception occured to connect DataBase" + e.getMessage());

		}

	}

	public List<Object> dbResult(String selectSql) throws InterruptedException  {
		List<Object> sortedList = null;
		int columns = 0;
		List<Map<String, Object>> metaDataRows = new LinkedList<Map<String, Object>>();
		try {
			
			connection = DriverManager.getConnection(sqlserverstring, dbUser, dbPassword);
           Thread.sleep(50);
           System.out.println("heysql connected");
			statement = connection.createStatement();
	           System.out.println("heysql connected1");

			queryResult = statement.executeQuery(selectSql.trim());
	           System.out.println("heysql connected2");

			metaData = queryResult.getMetaData();
			
			    
	           System.out.println(queryResult);

			
			columns = metaData.getColumnCount();
			
			
			  System.out.println("heysql connected4");
			metaDataRows = new LinkedList<Map<String, Object>>();
			  System.out.println("heysql connected5");
			Thread.sleep(50);
			Map<String, Object> row;
			while (queryResult.next()) {
				row = new HashMap<String, Object>();
				for (int i = 1; i <= columns; ++i) {
					row.put(metaData.getColumnName(i), queryResult.getObject(i));
					System.out.println(metaData.getColumnName(i)+" "+queryResult.getObject(i));
				}
				metaDataRows.add(row);
			}
			String[] one = new String[metaDataRows.size()];
			for (int k = 0; k < metaDataRows.size(); k++) {
				Map<String, Object> m = metaDataRows.get(k);
				one[k] = m.toString();
			}
			String[] dbitem = null;
			List<String> listColumnRow = new ArrayList<String>();
			for (int m = 0; m < one.length; m++) {
				String dbitems = one[m].substring(1, one[m].length() - 1);
				dbitem = dbitems.split(",");
				for (int d = 0; d < dbitem.length; d++) {
					listColumnRow.add(dbitem[d]);
				}
			}
			sortedList = listColumnRow.stream().sorted().collect(Collectors.toList());

		} catch (SQLException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage("An exception occured while connect to database and extracting db result: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " An exception occured while connect to database and extracting db result:" + e.getMessage());

		}
		
		
		finally {
			
			try {
				statement.close();
				System.out.println("db connection closed");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return sortedList;
	}


}
