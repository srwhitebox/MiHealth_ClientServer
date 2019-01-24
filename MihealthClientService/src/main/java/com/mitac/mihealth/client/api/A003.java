package com.mitac.mihealth.client.api;

import java.util.List;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.mitac.mihealth.client.model.Wh;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.dao.WhDAO;

public class A003 {
	private ApplicationContext msSqlcontext;
	private Gson gson;
	public A003(ApplicationContext msSqlcontext,Gson gson){
		this.msSqlcontext = msSqlcontext;
		this.gson = gson;
	}
	/**傳送教育部身高體重量測資料*/
	public void post(JsonObject json){	
		post(new JSONObject(gson.toJson(json)));
	}
	private void post(JSONObject json){		
		WhDAO whDAO = msSqlcontext.getBean(WhDAO.class);
		String pid = json.getString("PID");
		int gradeid = json.getInt("GradeID");
		int sem = json.getInt("Sem");
		List<Wh> whlist = whDAO.selectOne(pid, gradeid, sem);
		Wh wh = gson.fromJson(json.toString(), Wh.class);
		
		if (whlist.size() == 0) {
			// insert
			whDAO.insert(wh);
			System.out.println("========== Wh insert");
		} else {
			// update
			whDAO.update(wh);
			System.out.println("========== Wh update");
		}
	}
}
