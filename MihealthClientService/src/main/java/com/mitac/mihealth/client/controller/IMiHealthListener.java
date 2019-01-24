package com.mitac.mihealth.client.controller;

import java.util.Date;

import com.google.gson.JsonObject;
import com.mitac.mihealth.client.model.Student;

import okhttp3.Response;

public interface IMiHealthListener {
	void onMeasurementData(Student student, Date date, JsonObject jData);
	void onCareData(Student student, Date date, JsonObject jData);
	void onOpen();
	void onClosing(int code, String reason);
	void onFailure(Response response);
}
