package com.mitac.mihealth.client.api;

import java.util.List;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.mitac.mihealth.client.model.Sight;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.dao.SightDAO;

public class A002 {
	private ApplicationContext msSqlcontext;
	private Gson gson;
	public A002(ApplicationContext msSqlcontext,Gson gson){
		this.msSqlcontext = msSqlcontext;
		this.gson = gson;
	}
	/**傳送教育部視力量測資料*/
	public void post(JsonObject json){	
		post(new JSONObject(gson.toJson(json)));
	}
	private void post(JSONObject json){		
		SightDAO sightDAO = msSqlcontext.getBean(SightDAO.class);
		String pid = json.getString("PID");
		int gradeid = json.getInt("GradeID");
		int sem = json.getInt("Sem");
		List<Sight> sightlist = sightDAO.selectOne(pid, gradeid, sem);
		Sight sight = gson.fromJson(json.toString(), Sight.class);
		if (sightlist.size() == 0) {
			// insert
			sightDAO.insert(sight);
			System.out.println("========== Sight insert");
		} else {
			// update
			sightDAO.update(sight);
			System.out.println("========== Sight insert");
		}
	}
}
