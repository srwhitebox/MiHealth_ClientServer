package com.mitac.mihealth.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.mitac.mihealth.client.api.A001;
import com.mitac.mihealth.client.api.A002;
import com.mitac.mihealth.client.api.A003;
import com.mitac.mihealth.client.api.A004;

public class dataApiMain {
	public static void main(String[] args) {
		Map<String, String> argsMap = checkArgs(args);		
		//JsonObject jEyeProperty = McJsonUtils.toJson("{\"leftEye\": \"1.25\", \"rightEye\": \"0.8\"}").getAsJsonObject();
		//JsonObject jFatProperty = McJsonUtils.toJson("{\"bmi\": \"24.10\", \"height\": \"158.5\", \"weight\": \"60.54\", \"comment\": \"過重\", \"fatLevel\": \"OBESITY\"}").getAsJsonObject();

		if(argsMap.get("error").equals("1")) {
			printUsage();
		}else{			
			String modelName = argsMap.get("modelName");			
			JSONObject jProperty = new JSONObject(argsMap.get("data"));			
			
//			switch(modelName){
//				case "ST":							
//					A001 a001 = new A001();
//					a001.post(jProperty);
//					break;
//				case "SIGHT":
//					A002 a002 = new A002();
//					a002.post(jProperty);
//					break;
//				case "WH":
//					A003 a003 = new A003();
//					a003.post(jProperty);
//					break;
//				case "ACC":
//					A004 a004 = new A004();
//					a004.post(jProperty);
//					break;
//				default :
//					System.out.println("table " + modelName + " is not exist!!");
//					break;
//			}
		}		
	}
	
	public static void printUsage() {
		// run as => run configurations => (x)=arguments => 
		// St : -m St -d "{\"PID\": \"test2234\", \"Guy\": \"TTT\", \"SexID\": \"2\", \"Years\": \"90\", \"ClassID\": \"2\", \"Zip\": \"10\"}"
		// Sight : -m Sight -d "{\"PID\": \"test2234\", \"GradeID\": \"10\", \"Sem\": \"2\"}"
		// Wh : -m Wh -d "{\"PID\": \"test2234\", \"GradeID\": \"10\", \"Sem\": \"2\"}"
		// Acc : -m Acc -d "{\"AccID\": \"2\", \"PID\": \"test2234\"}"		
		System.out.println("Usage : dataApiMain -m modelName -d data");		
	}
	
	public static Map<String, String> checkArgs(String[] args) {
		Map<String, String> argsMap = new HashMap<String, String>();
		argsMap.put("error", "0");
		
		if(args.length != 4) {			
			argsMap.put("error", "1");
		}
		else {
			for(int i = 0; i < args.length; i+=2) {
				if(args[i].equals("-m")) {
					String modelName = args[i+1].toUpperCase();	
					argsMap.put("modelName", modelName);
				}
				else if(args[i].equals("-d")) {
					argsMap.put("data", args[i+1]);
				}
				else {
					argsMap.put("error", "1");
				}
			}
		}
		
		return argsMap;
	}
}
