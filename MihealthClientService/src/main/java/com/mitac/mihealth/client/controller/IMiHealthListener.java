package com.mitac.mihealth.client.controller;

import java.util.Date;

import com.google.gson.JsonObject;
import com.mitac.mihealth.client.model.Student;

public interface IMiHealthListener {
	void onMeasurementData(Student student, Date date, JsonObject jData);
	void onCareData(Student student, Date date, JsonObject jData);
}
