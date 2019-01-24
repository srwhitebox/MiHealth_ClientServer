package com.mitac.mihealth.client.controller;

import java.util.Date;

import com.google.gson.JsonObject;
import com.mitac.mihealth.client.model.Student;

import okhttp3.Response;

public interface IMiHealthListener_2 {
	void onCareData(Student student, Date date, JsonObject jData);
	void onFailure_2(Response response);
	void onClosing_2(int code, String reason);
	void onMeasurementData_2(Student student, Date date, JsonObject jData);
	void onOpen_2();
}
