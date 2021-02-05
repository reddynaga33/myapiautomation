var sql = require('mssql');
const fs = require('fs');
const path = require('path');
 
//data base details
var dbConfig  = {
	server: "sqldb-cdm-dias-r2-0-0-qe-svr.database.windows.net",
	database: "sqldb-cdm-dias-r2-0-0-qe-db",
	user: "diasqerwuser",
	password:"!mqediasRw@206",
	port:1433
};

//date and time for log
const d = new Date();
const year = d.getFullYear()
const month = d.getMonth()
const Date1 =  d.getDate()
const hrs = d.getHours()
const minutes = d.getMinutes()
const seconds = d.getSeconds()
const miliseconds = d.getMilliseconds()

const Time = year+"-"+month+"-"+Date1+" "+hrs+":"+minutes+":"+seconds+":"+miliseconds;

//main query execution
var functionDemo1 = function getData()

{
	var conn = new sql.ConnectionPool(dbConfig);
	const req = new sql.Request(conn);
	conn.connect(function (err)
	{
		if (err){
			errrLog(Time+" :"+"DataBase:"+" "+err);
			return;
			
		}
		req.query(process.argv[2],function (err, recordSets) 
		{			
			if (err)
			{
				errrLog(Time+" :"+"Query:"+" "+err);
			}
			else
			{
				
				const myJSON = JSON.stringify(recordSets.recordsets[0]);
			
				// for testing only
				//fs.writeFile('Output.txt',process.argv[2], (err) => { 
      
				//if (err) throw err; 
					//}) 
				 		
				if (recordSets['rowsAffected'][0] == 0)
				{	
					file_write(myJSON);    
				}
				
				else{
		
				
				var a;
				var arrList=[];
				for(var key in recordSets.recordsets[0]) {
					
					for (const [key1, value1] of Object.entries(recordSets.recordsets[0][key])) {
						a = `${key1}=${value1}`
						arrList.push(a);	
					}	
				}
				
					var srt=arrList.sort();	
					//delete_file();
					
					const test_arr = Object.entries(srt);
					
					for (var k_v in test_arr)
					{	
						file_write(arrList[k_v]);	
					}
				}
			}
			conn.close();
			
		});
		
	});
		
}

//file writer function
function file_write(getData)
		{

			var ln = process.argv[3];
			var DB_filelocation_1 = process.argv[4];
  
			var DB_filelocation_2 = DB_filelocation_1.split(":")[1];
			var DB_filelocation_final = DB_filelocation_2.split("//").join("/")
			 
			//fs.appendFileSync(DB_filelocation_final+ln, getData +"\r\n" ,function (err) {

			fs.appendFileSync(DB_filelocation_final+ln, getData +"\r\n","utf8" ,function (err) {
	
			
			  if (err)
			  {		
					errrLog(Time+" :"+"file:"+" "+err);	  
					return;
			  }
			  
			});
		}

//delete file for testing only
function delete_file()
{
	const path = './sorted_final_response.txt'
	fs.unlink(path, (err) => {
	if (err) {
     errrLog(Time+" :"+"delete:"+" "+err);
    return
  }
});
}

//javascript error log file
function errrLog(errorlog)
{
	fs.appendFileSync('JavaScriptErrorLog.txt', errorlog  +"\r\n","utf8" ,function (err) {
              if (err) return;
              
            });	
}

functionDemo1();