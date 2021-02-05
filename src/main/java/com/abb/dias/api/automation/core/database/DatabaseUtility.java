package com.abb.dias.api.automation.core.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	// String sqlserverstring = sqlserverjdbc + sqlPort + ":" + hostName +
	// ";database=" + dbName;

	public void dbConnection() {

		String sqlserverstring = sqlserverjdbc + hostName + ":" + sqlPort + ";database=" + dbName;
		try {
			connection = DriverManager.getConnection(sqlserverstring, dbUser, dbPassword);
			statement = connection.createStatement();
			
			
		} catch (SQLException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(" Method Name is : " + nameofCurrMethod
					+ "   An exception occured to connect DataBase" + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod + "   An exception occured to connect DataBase"
					+ e.getMessage());
		}

	}

	/*
	 * This method is used get DB Table Records
	 * 
	 * @Param : sqlQuery The sql query will come from metadata sheet from input
	 * excel
	 * 
	 * public List<Object> getDBTableResult(String SqlQuery) throws
	 * InterruptedException { List<Object> sortedList = null; int columns = 0; int
	 * counter = 0; List<Map<String, Object>> metaDataRows = new
	 * LinkedList<Map<String, Object>>(); try { connection =
	 * DriverManager.getConnection(sqlserverstring, dbUser, dbPassword);
	 * Thread.sleep(50); statement = connection.createStatement(); queryResult =
	 * statement.executeQuery(SqlQuery.trim()); metaData =
	 * queryResult.getMetaData(); System.out.println(queryResult); columns =
	 * metaData.getColumnCount(); metaDataRows = new LinkedList<Map<String,
	 * Object>>(); Thread.sleep(50); Map<String, Object> row; while
	 * (queryResult.next()) { row = new HashMap<String, Object>(); for (int i = 1; i
	 * <= columns; ++i) { row.put(metaData.getColumnName(i),
	 * queryResult.getObject(i)); System.out.println(metaData.getColumnName(i) + " "
	 * + queryResult.getObject(i)); } metaDataRows.add(row); } String[] one = new
	 * String[metaDataRows.size()]; for (int k = 0; k < metaDataRows.size(); k++) {
	 * Map<String, Object> m = metaDataRows.get(k); one[k] = m.toString(); }
	 * String[] dbitem = null; List<String> listColumnRow = new ArrayList<String>();
	 * for (int m = 0; m < one.length; m++) { String dbitems = one[m].substring(1,
	 * one[m].length() - 1); dbitem = dbitems.split(","); for (int d = 0; d <
	 * dbitem.length; d++) { listColumnRow.add(dbitem[d]); } } sortedList =
	 * listColumnRow.stream().sorted().collect(Collectors.toList());
	 * 
	 * } catch (SQLException e) {
	 * 
	 * String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
	 * TestLogger.errorMessage(
	 * "An exception occured while connect to database and extracting db result: " +
	 * e.getMessage()); Reporter.log(" Method Name is : " + nameofCurrMethod +
	 * " An exception occured while connect to database and extracting db result:" +
	 * e.getMessage()); } finally {
	 * 
	 * try { statement.close(); System.out.println("db connection closed"); } catch
	 * (SQLException e) {
	 * 
	 * e.printStackTrace(); } } return sortedList; }
	 */
	/*
	 * This method is used to verify the DB Table status empty or not
	 * 
	 * @Param sqlQuery: sql query is coming from meta data excel input sheet
	 */
	
	
	/*
	 * This method is used get DB Table Records
	 * 
	 * @Param : sqlQuery The sql query will come from metadata sheet from input
	 * excel
	 */
	public void  getSQLQueryResult(String SqlQuery,String textfilewritelocation)  {
		
		try {
			
			ExcelReader reader = new ExcelReader(EnvironmentManager.getExcelConfigSheetName().trim());
			String dbresultLocation = reader.getValuefromConfigExcel("dbresultfolder")+"//";
			System.out.println("the db file db location is:"+dbresultLocation);

			
			File dbresulfoldr = new File(dbresultLocation);
			dbresulfoldr.mkdir();
			//String location=	dbresulfoldr + "\\" + textfilewritelocation+ ".txt";
			String textfilelocation=	textfilewritelocation+ ".txt";

			System.out.println("the db  actual text file location:"+textfilelocation);
		//	String query = "select EQI_ID,TET_COD,STE_COD,SCE_SYS_COD,LGG_COD,SCE_EQI_COD,STD_EQI_COD,SCE_EQI_SHT_DSC,STD_EQI_SHT_DSC,SCE_EQI_LNG_DSC,STD_EQI_LNG_DSC,SCE_EQI_GRP_COD,STD_EQI_GRP_COD,SCE_EQI_GRP_DSC,STD_EQI_GRP_DSC,SCE_EQI_CLS,STD_EQI_CLS,SCE_EQI_CAT,STD_EQI_CAT,SCE_EQI_CAT_DSC,STD_EQI_CAT_DSC,SCE_EQI_TYP,STD_EQI_TYP,SCE_EQI_TYP_DSC,STD_EQI_TYP_DSC,SCE_EQI_SUB_TYP,STD_EQI_SUB_TYP,SZE_DIM,EQI_WGT,WGT_UOM_COD,FORMAT(ACQ_DTE,'yyyy-MM-ddTHH:mm:ss.fffZ') AS ACQ_DTE,RPC_CST,ACQ_CST,CST_UOM_COD,WTY_STT_DTE,WTY_END_DTE,MER_COD,SRL_NBR,MOD_NBR,PAT_NBR,MNT_TAG_NBR,MEA_POI,SCE_EQI_CLF_TYP_1,STD_EQI_CLF_TYP_1,SCE_EQI_CLF_TYP_2,STD_EQI_CLF_TYP_2,SCE_EQI_CLF_TYP_3,STD_EQI_CLF_TYP_3,SCE_EQI_CLF_TYP_4,STD_EQI_CLF_TYP_4,SCE_EQI_CLF_TYP_5,STD_EQI_CLF_TYP_5,FORMAT(EFC_FRM,'yyyy-MM-ddTHH:mm:ss.fffZ') AS EFC_FRM,FORMAT(EFC_TO,'yyyy-MM-ddTHH:mm:ss.fffZ') AS EFC_TO,SCE_EQI_CRL_COD,STD_EQI_CRL_COD,SCE_EQI_CRL_DSC,STD_EQI_CRL_DSC,CTG_PLE_COD,CTG_PLE_DSC,EQI_LEV_COD,LST_FMCA_PFM_DTE,RPN_NBR_CRR,RPN_NBR_LST,RPN_CRR_DTE,RPN_LST_DTE,SCE_EQI_STU_COD,STD_EQI_STU_COD,EQI_STU_DTE,EQI_ACG_CLS,RCM_FMEA_RQM_FLG,INV_NBR,PCH_ORD_NBR,PCH_ORD_LIN_NBR,PCH_ORD_DTE,VND_COD,CST_CET_COD,ALT_LOC_COD,ROM_COD,OBJ_NBR,SRT_ORD,MER_CTY,MER_YEA_MTH,SCE_FCT_LOC_COD,MAI_WRK_CET_COD,MNT_PLA_COD,MNT_PLT_COD,PLA_PLT_COD,PLA_GRP_COD,FAI_COD,EQI_PID_DRW_NBR,TEC_NBR,REL_CLS,SCE_MAL_COD,MNT_FLG,WTY_TYP,WTY_COV_COD,SCE_WRK_CET_COD,SCE_EQI_OPI_CLS,STD_EQI_OPI_CLS,EQI_RTE_COD,SFT_CRL_EQI_FLG,ITG_ID,CFG_SHT_TXT_ATR_1,CFG_SHT_TXT_ATR_2,CFG_LNG_TXT_ATR_1,CFG_LNG_TXT_ATR_2,CFG_DTE_ATR_1,CFG_DTE_ATR_2,SCE_REF_ID,LAT_VAL,LON_VAL,ABC_IDC from ICMPCM.D_EQI order by 1 asc OFFSET 0 ROWS Fetch first 10 rows ONLY";
			//String tableName = "D_Equipment";
		//	String Filelocation = "C://Users//INKULAL//Desktop//API_Automation_NagarJuna//V1//API Automation//Version1.5//DIAS-TA-API//APIAutomationReport//dbresult/";
			//String srno = "s.no22";
			//String repetion = "repetion_No1";
			
		      // Process process=Runtime.getRuntime().exec("node api_js_test.js \""+ SqlQuery +"\" \"" +tableName+"\" \""+Filelocation+"\" \""+srno+"\" \""+repetion+"\" ");
		   //    Process process=Runtime.getRuntime().exec("node api_js_test.js \""+ SqlQuery +"\" ");

		       
		       Process process=Runtime.getRuntime().exec("node dbnodejs_test.js \""+ SqlQuery +"\" \"" +textfilelocation+"\" \""+ dbresultLocation+"\" ");

		       System.out.println("Waiting for process...");
		       process.waitFor();
		       System.out.println("process completed...");
		       
		} catch (IOException e) {

			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(
					"An exception occured while connect to database and extracting db result: " + e.getMessage());
			Reporter.log(" Method Name is : " + nameofCurrMethod
					+ " An exception occured while connect to database and extracting db result:" + e.getMessage());
		
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public boolean getDBTableEmptyStatus(String SqlQuery) throws InterruptedException {
		int counter = 0;
		boolean tableEmpty = true;
		try {
			connection = DriverManager.getConnection(sqlserverstring, dbUser, dbPassword);
			Thread.sleep(50);
			statement = connection.createStatement();
			queryResult = statement.executeQuery(SqlQuery.trim());
			metaData = queryResult.getMetaData();
			if (queryResult.next() == true) {
				counter++;
			}
			if (counter == 0) {
				tableEmpty = true;
			} else {
				tableEmpty = false;
			}

		} catch (SQLException e) {
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			TestLogger.errorMessage(
					"An exception occured while connect to database and extracting db result: " + e.getMessage());
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
		return tableEmpty;

	}

	

}
