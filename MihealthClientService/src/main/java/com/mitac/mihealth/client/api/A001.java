package com.mitac.mihealth.client.api;

import java.util.List;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mitac.mihealth.client.model.St;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.dao.StDAO;

public class A001 {
	private ApplicationContext msSqlcontext;
	private Gson gson;
	public A001(ApplicationContext msSqlcontext,Gson gson){
		this.msSqlcontext = msSqlcontext;
		this.gson = gson;
	}
	/**傳送教育部學生資料(目前未使用)*/
	public void post(JsonObject json){	
		post(new JSONObject(gson.toJson(json)));
	}
	private void post(JSONObject json){
		StDAO stDAO = msSqlcontext.getBean(StDAO.class);
		String pid = json.getString("PID");
		List<St> stlist = stDAO.selectOne(pid);
		St st = gson.fromJson(json.toString(), St.class);
		
		if (stlist.size() == 0) {
			// insert
			stDAO.insert(st);
			System.out.println("========== St insert");
		} else {
			// update
			stDAO.update(st);
			System.out.println("========== St update");
		}
		
//		msSqlcontext.close();
	}
}
