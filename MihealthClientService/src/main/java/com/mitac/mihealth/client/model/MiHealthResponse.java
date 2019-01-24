package com.mitac.mihealth.client.model;

import com.google.gson.JsonArray;
import com.xmpl.lib.utils.McJsonUtils;

public class MiHealthResponse {
	public static final String KEY_TYPE = "type"; 
	
	public static final int CODE_SUCCEED = 200;
	public static final int CODE_NOT_FOUND = 400;

	public static final String DATA_TYPE_MEASUREMENT = "measurement";
	public static final String DATA_TYPE_CARE = "care";
	
	private int responseCode;
	private int count;
	private JsonArray data;
	
	public MiHealthResponse(){
		
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public JsonArray getData() {
		return data;
	}

	public void setData(JsonArray data) {
		this.data = data;
	}
	
	public static MiHealthResponse getInstance(String jResponse){
		return McJsonUtils.toObject(jResponse, MiHealthResponse.class);
	}
	
	public static MiHealthResponse getInstance(byte[] jResponse){
		return McJsonUtils.toObject(McJsonUtils.toJson(jResponse), MiHealthResponse.class);
	}
}
