package com.mitac.mihealth.client.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class ObjToJson {
	public static String getJson(Object o) {
		JSONObject jsonObj = new JSONObject();
		for(Field field : o.getClass().getDeclaredFields()) {
			if(Collection.class.isAssignableFrom(field.getType())){
				continue;
			}
			try {
				field.setAccessible(true);
				jsonObj.put(field.getName(), (field.get(o)==null?"":field.get(o)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println(jsonObj);
		return jsonObj.toString();
	}
	
	public static String getJsons(List<?> a){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(Object o : a) {
			JSONObject jsonObj = new JSONObject();
			for(Field field : o.getClass().getDeclaredFields()) {
				if(Collection.class.isAssignableFrom(field.getType())){
					continue;
				}
				try{
					field.setAccessible(true);
					jsonObj.put(field.getName(), (field.get(o)==null?"":field.get(o)));
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			sb.append(jsonObj.toString() + ",");
		}
		if(sb.length()>1){
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		
		//System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	public static String hashMapJson(HashMap<String, String> hashMap) {
		String jsonStr = "";
		
		jsonStr += "{";
		for(Object key : hashMap.keySet()) {
			if(jsonStr.equals("{")) {
				jsonStr += "\"";
			}
			else {
				jsonStr += ",\"";
			}
			jsonStr += key + "\":";
			if(!hashMap.get(key).equals("") && hashMap.get(key).charAt(0) == '[') { //if array
				jsonStr += (hashMap.get(key)==null?"":hashMap.get(key));
			}
			else {
				jsonStr += "\"" + (hashMap.get(key)==null?"":hashMap.get(key)) + "\"";
			}
        }
		jsonStr += "}";
		
		return jsonStr;
	}
	
	public static String listMapJson(List<Map<String, Object>> listMap, String excludeColName) {
		String jsonStr = "";
		
		jsonStr += "[";
		for(Map<String, Object> row : listMap) {
			if(jsonStr.equals("[")) {
				jsonStr += "{";
			}
			else {
				jsonStr += ",{";
			}
			boolean firstComma = false;
			for(Object key : row.keySet()) {
				if(key.equals(excludeColName)) {
					continue;
				}
				jsonStr += (firstComma?",\"":"\"") + key + "\":";
				jsonStr += "\"" + (row.get(key)==null?"":row.get(key)) + "\"";
				firstComma = true;
	        }
			jsonStr += "}";
		}
		jsonStr += "]";
		
		return jsonStr;
	}
}
