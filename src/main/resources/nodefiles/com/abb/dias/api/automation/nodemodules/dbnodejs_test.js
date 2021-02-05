var sql = require('mssql');
const fs = require('fs');
 
var dbConfig  = {
	server: "sqldb-cdm-dias-r2-0-0-qe-svr.database.windows.net",
	database: "sqldb-cdm-dias-r2-0-0-qe-db",
	user: "diasqerwuser",
	password:"!mqediasRw@206",
	port:1433
};

var functionDemo1 = function getData()

{
	var today = new Date();
	var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
	
	
	var conn = new sql.ConnectionPool(dbConfig);

	const req = new sql.Request(conn);
	
	//console.log("Test: ",process.argv[3])
	
	conn.connect(function (err)
	{
		if (err){
			console.log(err);
			return;
			
		}
		req.query("select EQI_ID,TET_COD,STE_COD,SCE_SYS_COD,LGG_COD,SCE_EQI_COD,STD_EQI_COD,SCE_EQI_SHT_DSC,STD_EQI_SHT_DSC,SCE_EQI_LNG_DSC,STD_EQI_LNG_DSC,SCE_EQI_GRP_COD,STD_EQI_GRP_COD,SCE_EQI_GRP_DSC,STD_EQI_GRP_DSC,SCE_EQI_CLS,STD_EQI_CLS,SCE_EQI_CAT,STD_EQI_CAT,SCE_EQI_CAT_DSC,STD_EQI_CAT_DSC,SCE_EQI_TYP,STD_EQI_TYP,SCE_EQI_TYP_DSC,STD_EQI_TYP_DSC,SCE_EQI_SUB_TYP,STD_EQI_SUB_TYP,SZE_DIM,EQI_WGT,WGT_UOM_COD,FORMAT(ACQ_DTE,'yyyy-MM-ddTHH:mm:ss.fffZ') AS ACQ_DTE,RPC_CST,ACQ_CST,CST_UOM_COD,WTY_STT_DTE,WTY_END_DTE,MER_COD,SRL_NBR,MOD_NBR,PAT_NBR,MNT_TAG_NBR,MEA_POI,SCE_EQI_CLF_TYP_1,STD_EQI_CLF_TYP_1,SCE_EQI_CLF_TYP_2,STD_EQI_CLF_TYP_2,SCE_EQI_CLF_TYP_3,STD_EQI_CLF_TYP_3,SCE_EQI_CLF_TYP_4,STD_EQI_CLF_TYP_4,SCE_EQI_CLF_TYP_5,STD_EQI_CLF_TYP_5,FORMAT(EFC_FRM,'yyyy-MM-ddTHH:mm:ss.fffZ') AS EFC_FRM,FORMAT(EFC_TO,'yyyy-MM-ddTHH:mm:ss.fffZ') AS EFC_TO,SCE_EQI_CRL_COD,STD_EQI_CRL_COD,SCE_EQI_CRL_DSC,STD_EQI_CRL_DSC,CTG_PLE_COD,CTG_PLE_DSC,EQI_LEV_COD,LST_FMCA_PFM_DTE,RPN_NBR_CRR,RPN_NBR_LST,RPN_CRR_DTE,RPN_LST_DTE,SCE_EQI_STU_COD,STD_EQI_STU_COD,EQI_STU_DTE,EQI_ACG_CLS,RCM_FMEA_RQM_FLG,INV_NBR,PCH_ORD_NBR,PCH_ORD_LIN_NBR,PCH_ORD_DTE,VND_COD,CST_CET_COD,ALT_LOC_COD,ROM_COD,OBJ_NBR,SRT_ORD,MER_CTY,MER_YEA_MTH,SCE_FCT_LOC_COD,MAI_WRK_CET_COD,MNT_PLA_COD,MNT_PLT_COD,PLA_PLT_COD,PLA_GRP_COD,FAI_COD,EQI_PID_DRW_NBR,TEC_NBR,REL_CLS,SCE_MAL_COD,MNT_FLG,WTY_TYP,WTY_COV_COD,SCE_WRK_CET_COD,SCE_EQI_OPI_CLS,STD_EQI_OPI_CLS,EQI_RTE_COD,SFT_CRL_EQI_FLG,ITG_ID,CFG_SHT_TXT_ATR_1,CFG_SHT_TXT_ATR_2,CFG_LNG_TXT_ATR_1,CFG_LNG_TXT_ATR_2,CFG_DTE_ATR_1,CFG_DTE_ATR_2,SCE_REF_ID,LAT_VAL,LON_VAL,ABC_IDC from ICMPCM.D_EQI order by 1 asc OFFSET 0 ROWS Fetch first 10 rows ONLY",function (err, recordSets) 
		{
			//console.log(recordSets)
			
			
			if (err)
			{
				//console.log("Query: Error: ",err)
			}
			else
			{
				let data = "Learning how to write in a file."
				//console.log(typeof(recordSet));
				 //console.log(typeof(recordSets.recordsets[0]));
				 var count = Object.keys(recordSets.recordsets[0]).length;
					//console.log("len:",count)
					
				//console.log("JsonObject: ",recordSets.recordsets[0])
				
				
				var myJSON = JSON.stringify(recordSets.recordsets[0]);
				
				
				//console.log(myJSON);
				fs.writeFile('Output.txt', myJSON, (err) => { 
      
				// In case of a error throw err. 
				if (err) throw err; 
					}) 
				 
				
				for (const [key, value] of Object.entries(recordSets.recordsets[0])) {
					//console.log("Test: ",`${key}: ${value}`);
					}
				var count = 0;
				var a;
				var arrList=[];
				for(var key in recordSets.recordsets[0]) {
					//console.log(recordSets.recordsets[0][key])
					for (const [key1, value1] of Object.entries(recordSets.recordsets[0][key])) {
//					  console.log(`${key1}= ${value1}`);
						a = `${key1}=${value1}`
						//console.log("key & Value",a)
						arrList.push(a);
						//file_write(a);
					}
					//console.log(arrList)
					//file_write(arrList)
					
				}
				
					//console.log("len:",arrList.sort());
					var srt=arrList.sort();
					//console.log("Array length: ",srt.length)
					//delete old file
					
					delete_file();
					//var srt_2 = JSON.stringify(arrList.sort()); 
					const test_arr = Object.entries(srt);
					///file_write(srt_2);
					//console.log("Array check : ",test_arr);
					
					
					for (var k_v in test_arr)
					{
						//console.log(k_v+"_"+arrList[k_v]+"\r\n")
						
						file_write(arrList[k_v]);
						
					}
				
				
				
				
			}
			conn.close();
			
		});
		
	});
		
}

function file_write(getData)
		{
			//console.log("in function file write: ",getData)
			fs.appendFileSync('sorted_final_response.txt', getData +"\r\n","utf8" ,function (err) {
			  if (err) return console.log(err);
			  
			});
		}

function delete_file()
{
	const path = './sorted_final_response.txt'
	fs.unlink(path, (err) => {
	if (err) {
    //console.error(err)
    return
  }
});
}


functionDemo1();