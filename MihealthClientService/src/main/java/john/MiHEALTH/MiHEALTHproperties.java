package john.MiHEALTH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**參數檔案取用*/
public class MiHEALTHproperties {
	
	private static File configDir = 
			new File("conf");//export Runnable jar file
//			new File("target/classes");//in debug
	private static File configFile = new File(configDir, "MiHEALTH.properties");
	private static Properties props;
	
	public static void load(){
		InputStream stream = null;
		try{
			stream = new FileInputStream(configFile);
		}catch(FileNotFoundException e){
			configFile = new File(configDir, "MiHEALTH.properties");
			try {
				stream = new FileInputStream(configFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		props = new Properties();
		try {
			props.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**傳送教育部視力量測資料(設定學期資料)*/
	public static Integer getSem(){
		if(props==null)load();
		return Integer.parseInt((String)props.get("MiHEALTH.sem"));
	}
	/**接收MiHealth視力量測資料(websocket的參數)*/
	public static String getSchoolId(){
		if(props==null)load();
		return (String)props.get("MiHEALTH.schoolId");
	}
	/**接收MiHealth視力量測資料(websocket的參數)*/
	public static String getAuthKey(){
		if(props==null)load();
		return (String)props.get("MiHEALTH.authKey");
	}
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)(xlsx儲存路徑)*/
	public static String getExcelPath(){
		if(props==null)load();
		return (String)props.get("MiHEALTH.excelPath");
	}
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)(今年度)*/
	public static Integer getNowYear(){
		if(props==null)load();
		return Integer.parseInt((String)props.get("EDU.nowYear"));
	}
	/**從教育部匯出學生資料(xlsx)(MiHEALTH匯入格式)(教育部資料庫連線參數)*/
	public static String getEDU_ConnectionString(){
		if(props==null)load();
		return (String)props.get("EDU.ConnectionString");
	}
	public static void main(String args[]){
		System.out.println("configFile:"+configFile.getAbsolutePath());
		System.out.println("getSem():"+getSem());
	}
}
