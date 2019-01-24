package com.mitac.mihealth.client.api;

import java.util.List;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.mitac.mihealth.client.model.Acc;
import com.mitac.mihealth.client.model.St;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mitac.mihealth.client.dao.AccDAO;
import com.mitac.mihealth.client.dao.StDAO;

public class A004 {
	private ApplicationContext msSqlcontext;
	private Gson gson;

	public A004(ApplicationContext msSqlcontext, Gson gson) {
		this.msSqlcontext = msSqlcontext;
		this.gson = gson;
	}

	/** 傳送教育部傷病資料 */
	public void post(JsonObject json) {
		post(new JSONObject(gson.toJson(json)));
	}

	private void post(JSONObject json) {
		AccDAO accDAO = msSqlcontext.getBean(AccDAO.class);
		//System.out.println("------------HERE-3---------");
		//String pid = json.getString("PID");
		//List<Acc> acclist = accDAO.selectOne(pid);
		Acc acc;
		try {
			acc = gson.fromJson(json.toString(), Acc.class);
			//System.out.println("========== Acc SUS ===="+acc.getHead().toString());
			accDAO.insert(acc);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("========== Acc ===="+e.toString());
		}
		
		//StDAO stDAO = msSqlcontext.getBean(StDAO.class);

		// List<St> stlist = stDAO.selectOne(pid);

		//if (acclist.size() == 0) {
			// insert
			
			//System.out.println("========== Acc insert");
		//} else {
			// update
			// acc.setAccID(accid);
			//accDAO.update(acc);
			//System.out.println("========== Acc update");
		//}

	}
}
