package com.mitac.mihealth.client.api;

import java.util.List;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.mitac.mihealth.client.model.Acc;
import com.mitac.mihealth.client.model.St;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mitac.mihealth.client.dao.AccDAO;
import com.mitac.mihealth.client.dao.StDAO;

public class A004 {
	private ApplicationContext msSqlcontext;
	private Gson gson;
	public A004(ApplicationContext msSqlcontext,Gson gson){
		this.msSqlcontext = msSqlcontext;
		this.gson = gson;
	}
	/**傳送教育部傷病資料*/
	public void post(JsonObject json){	
		post(new JSONObject(gson.toJson(json)));
	}
	private void post(JSONObject json){		
		AccDAO accDAO = msSqlcontext.getBean(AccDAO.class);
		int accid = json.getInt("AccID");
		List<Acc> acclist = accDAO.selectOne(accid);
		Acc acc = new Acc();
		
		StDAO stDAO = msSqlcontext.getBean(StDAO.class);
		String pid = json.getString("PID");
		List<St> stlist = stDAO.selectOne(pid);
		
		acc = gson.fromJson(json.toString(), Acc.class);
		
		if(stlist.size() == 0){
			System.out.println(">>無該學生PID資料!!");
		}else{				
			if (acclist.size() == 0) {
				// insert
				accDAO.insert(acc);
				System.out.println("========== Acc insert");
			} else {
				// update
				acc.setAccID(accid);
				accDAO.update(acc);
				System.out.println("========== Acc update");
			}
		}
	}
}
