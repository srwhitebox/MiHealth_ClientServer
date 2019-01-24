package com.xmpl.lib.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class McJsonUtils {
	
	public static Gson getGson(){
		return new Gson();
	}
	
	public static JsonElement toJson(String jValue){
		if (Strings.isNullOrEmpty(jValue))
			return null;
		
		return new JsonParser().parse(jValue);
    }

	public static JsonElement toJson(byte[] jValue){
		if (jValue == null)
			return null;
		ByteArrayInputStream is = new ByteArrayInputStream(jValue);
	
		return new JsonParser().parse(new InputStreamReader(is));
    }
	
	public static JsonElement toJsonElement(String jsonSource){
		try{
			return Strings.isNullOrEmpty(jsonSource) ? null : new JsonParser().parse(jsonSource);
		}catch(JsonSyntaxException e){
			return null;
		}
	}
	
	
	public static JsonElement toJsonElement(InputStream is){
		return toJsonElement(new InputStreamReader(is, McCharset.UTF_8));
	}
	
	public static JsonElement toJsonElement(Reader jsonReader){
		try{
			return jsonReader == null ? null : new JsonParser().parse(jsonReader);
		}catch(JsonSyntaxException e){
			return null;
		}
	}
	


	public static <T> T  toObject(InputStream is, Class<T> dataClass){
		return toObject(toJsonElement(is), dataClass);
	}
	
	public static <T> T  toObject(String jElement, Class<T> dataClass){
		return toObject(toJsonElement(jElement), dataClass);
	}
	
	public static <T> T toObject(JsonElement jElement, Class<T> dataClass){
		return jElement == null ? null : getGson().fromJson(jElement, dataClass);
	}

}
