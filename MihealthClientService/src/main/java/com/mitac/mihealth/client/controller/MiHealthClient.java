package com.mitac.mihealth.client.controller;

import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.controller.IMiHealthListener;
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
	
	private void onResponse(JsonObject jData){
		final String dataType = jData.get(MiHealthResponse.KEY_TYPE).getAsString();
		
		final Student student =  McJsonUtils.toObject(jData.get(MiHealthProperty.KEY_STUDENT), Student.class);
		final Date date = new Date(jData.get(MiHealthProperty.KEY_DATE).getAsLong());
		final JsonObject jProperties = jData.get(MiHealthProperty.KEY_DATA).getAsJsonObject();
		
		switch(dataType){
		case MiHealthResponse.DATA_TYPE_MEASUREMENT:
			if (this.miHealthListener != null)
				this.miHealthListener.onMeasurementData(student, date, jProperties);
			break;
		case MiHealthResponse.DATA_TYPE_CARE:
			if (this.miHealthListener != null)
				this.miHealthListener.onMeasurementData(student, date, jProperties);
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
}
