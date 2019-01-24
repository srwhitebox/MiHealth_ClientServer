package com.xmpl.lib.internet.websocket;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Strings;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class McWebSocketClient extends WebSocketListener{
	private static final int CODE_NORMAL_CLOSE = 1000;

	protected OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

	protected OkHttpClient httpClient;
	protected WebSocket webSocket;
	protected Request.Builder requestBuilder;
	
	public McWebSocketClient(String url){
		requestBuilder = new Request.Builder();
		setHostUrl(url);
	}
	
	public OkHttpClient.Builder connectTimeout(int timeout){
		return this.httpClientBuilder.connectTimeout(timeout,  TimeUnit.MILLISECONDS);
	}

	public OkHttpClient.Builder readTimeout(int timeout){
		return this.httpClientBuilder.readTimeout(timeout,  TimeUnit.MILLISECONDS);
	}
	
	public String getHostUrl(){
		return requestBuilder.build().url().toString();
	}
	
	public Request.Builder setHostUrl(String url){
		return requestBuilder.url(url);
	}
	
	public Request.Builder header(String name, String value){
		return this.requestBuilder.header(name, value);
	}
	
	public Request.Builder removeHeader(String name){
		return this.requestBuilder.removeHeader(name);
	}
	
	public WebSocket open(){
		this.httpClient = httpClientBuilder.build();
		
		final Request request =this.requestBuilder.build();
		
		this.webSocket = httpClient.newWebSocket(request, this);
		
		return this.webSocket;
	}
	
	public void send(String text){
		if (Strings.isNullOrEmpty(text))
			return;
		
		this.webSocket.send(text);
	}
	
	public void send(byte[] bytes){
		if (bytes == null || bytes.length == 0)
			return;
		if (this.webSocket!=null)
			this.webSocket.send(ByteString.of(bytes));
	}
	
	public void close(){
//		this.webSocket.cancel(); //will trigger onFailure(
		this.webSocket.close(1000, null);
//		httpClient.dispatcher().executorService().shutdown();
	}
	
	@Override
	public void onOpen(WebSocket webSocket, Response response) {
	}
	
	public void onOpen_2(WebSocket webSocket, Response response) {
	}

	@Override
	public void onMessage(WebSocket webSocket, String jResponse) {
	}

	@Override
	public void onMessage(WebSocket webSocket, ByteString jResponse) {
	}

	@Override
	public void onClosing(WebSocket webSocket, int code, String reason) {
		webSocket.close(CODE_NORMAL_CLOSE, null);
	}

	@Override
	public void onFailure(WebSocket webSocket, Throwable t, Response response) {
	}

	public void onClosing_2(WebSocket webSocket, int code, String reason) {
		webSocket.close(CODE_NORMAL_CLOSE, null);
	}

	public void onFailure_2(WebSocket webSocket, Throwable t, Response response) {
	}
}
