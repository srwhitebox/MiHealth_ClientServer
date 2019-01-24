package com.mitac.mihealth.client.controller;

import java.util.Date;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.controller.IMiHealthListener;
import com.mitac.mihealth.client.controller.IMiHealthListener_2;
import com.mitac.mihealth.client.model.MiHealthQuery;
import com.mitac.mihealth.client.model.MiHealthResponse;
import com.mitac.mihealth.client.model.Student;
import com.mitac.mihealth.client.utils.MiHealthProperty;
import com.xmpl.lib.internet.websocket.McWebSocketClient;
import com.xmpl.lib.utils.McJsonUtils;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

public class MiHealthClient extends McWebSocketClient{
	private IMiHealthListener miHealthListener;
	private IMiHealthListener_2 miHealthListener_2;
	
	public MiHealthClient(String url){
		super(url);
	}
	
	public MiHealthClient(String url, String schoolId, String authKey){
		this(url);
		this.setCampusId(schoolId);
		this.setAuthKey(authKey);
	}

	public void setCampusId(String schoolId){
		this.header("campusId", schoolId);
	}
	
	public void setAuthKey(String authKey){
		this.header("authKey", authKey);
	}
	
	public void setClientId(String clientId){
		this.header("clientId", clientId);
	}
	
	public void query(MiHealthQuery query){
		this.send(query.toString());
	}
	
	public void setListener(IMiHealthListener listenr){
		this.miHealthListener = listenr;
	}
	
	public void setListener_2(IMiHealthListener_2 listenr){
		this.miHealthListener_2 = listenr;
	}
	
	private void onResponse(JsonObject jData){
		final String dataType = jData.get(MiHealthResponse.KEY_TYPE).getAsString();
		System.out.println("CLIENT===============HERE+++++++" + dataType.trim());
		//System.out.println("------------datatype---------"+dataType);
		
		//final JsonObject jProperties_care = jData.get(MiHealthProperty.KEY_CARE_DATA).getAsJsonObject();
		//final JsonObject jProperties_treatment = jData.get(MiHealthProperty.KEY_TREATMENT_DATA).getAsJsonObject();
		//System.out.println("------------jProperties_care---------"+jProperties_care);
		//System.out.println("------------jProperties_treatment---------"+jProperties_treatment);
		
		switch(dataType){
		case MiHealthResponse.DATA_TYPE_MEASUREMENT:
			Student student = McJsonUtils.toObject(jData.get(MiHealthProperty.KEY_STUDENT), Student.class);
			Date date = new Date(jData.get(MiHealthProperty.KEY_DATE).getAsLong());
			JsonObject jProperties = jData.get(MiHealthProperty.KEY_DATA).getAsJsonObject();
			if (this.miHealthListener != null)
				this.miHealthListener.onMeasurementData(student, date, jProperties);
			if (this.miHealthListener_2 != null)
				this.miHealthListener_2.onMeasurementData_2(student, date, jProperties);
			break;
		case MiHealthResponse.DATA_TYPE_CARE:
//			if (this.miHealthListener != null)
//				this.miHealthListener.onCareData(student, date, jProperties);
			//if (this.miHealthListener != null)
			//	this.miHealthListener.onMeasurementData(student, date, jProperties);
			//if (this.miHealthListener_2 != null)
			//	this.miHealthListener_2.onMeasurementData_2(student, date, jProperties);
			break;
			//0904_新增接收treatment資料
		case MiHealthResponse.DATA_TYPE_TREATMENT:
			//System.out.println("HELLO~~~~~~~~~~~~~~~~~~~~~~~~~");
			Student student2 = McJsonUtils.toObject(jData.get(MiHealthProperty.KEY_STUDENT), Student.class);
			//System.out.println("HELLO~~~~~~~~~~~~~~~~~~~~~~~~~1");
			Date date2 = new Date(jData.get(MiHealthProperty.KEY_DATE).getAsLong());
			//System.out.println("HELLO~~~~~~~~~~~~~~~~~~~~~~~~~2");
			JsonObject jProperties_care = jData.get(MiHealthProperty.KEY_CARE_DATA).getAsJsonObject();
			JsonObject jProperties_treatment = jData.get(MiHealthProperty.KEY_TREATMENT_DATA).getAsJsonObject();
			//System.out.println("HELLO~~~~~~~~~~~~~~~~~~~~~~~~~3");
			jProperties_care.add("treatment", jProperties_treatment.get("treatment"));
			//JsonObject jProperties2 = new JsonObject();
			//System.out.println(jProperties_care.toString());
			//System.out.println(jProperties_treatment.toString());
//			for (String key : JSONObject.getNames(jProperties_care)){
//				jProperties2.add(key, jProperties_care.get(key));
//			}
//			for (String key : JSONObject.getNames(jProperties_treatment)){
//				jProperties2.add(key, jProperties_care.get(key));
//			}
			
			//System.out.println("HELLO~~~~~~~~~~~~~~~~~~~~~~~~~4");
			if (this.miHealthListener != null)
				this.miHealthListener.onMeasurementData(student2, date2, jProperties_care);
			if (this.miHealthListener_2 != null)
				this.miHealthListener_2.onMeasurementData_2(student2, date2, jProperties_care);
			//System.out.println("HELLO~~~~~~~~~~~~~~~~~~~~~~~~~5");
			break;
		}
	}
	
	private void onResponse(MiHealthResponse response){
		if (response == null)
			return;
		
		switch (response.getResponseCode()) {
		case MiHealthResponse.CODE_SUCCEED:
			for(JsonElement jElement : response.getData()){
				if (jElement.isJsonObject()){
					onResponse(jElement.getAsJsonObject());
				}
			}
			break;
		case MiHealthResponse.CODE_NOT_FOUND:
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public void onMessage(WebSocket webSocket, String jResponse) {
		onResponse(MiHealthResponse.getInstance(jResponse));
	}

	@Override
	public void onMessage(WebSocket webSocket, ByteString jResponse) {
		onResponse(MiHealthResponse.getInstance(jResponse.toByteArray()));
	}
	
	@Override
	public void onOpen(WebSocket webSocket, Response response) {
		if (this.miHealthListener != null)
			this.miHealthListener.onOpen();
	}

	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		super.onClosing( webSocket,  code,  reason);
		if (this.miHealthListener != null)
			this.miHealthListener.onClosing(code,reason);
	}

	@Override
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
		if (this.miHealthListener != null)
			this.miHealthListener.onFailure(response);
	}
	
	@Override
	public void onOpen_2(WebSocket webSocket, Response response) {
		if (this.miHealthListener_2 != null)
			this.miHealthListener_2.onOpen_2();
	}
	
	@Override
	public void onClosing_2(WebSocket webSocket, int code, String reason) {
		super.onClosing_2( webSocket,  code,  reason);
		if (this.miHealthListener_2 != null)
			this.miHealthListener_2.onClosing_2(code,reason);
	}

	@Override
	public void onFailure_2(WebSocket webSocket, Throwable t, Response response) {
		if (this.miHealthListener_2 != null)
			this.miHealthListener_2.onFailure_2(response);
	}
}



