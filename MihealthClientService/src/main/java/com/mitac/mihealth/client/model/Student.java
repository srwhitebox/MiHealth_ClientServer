package com.mitac.mihealth.client.model;

import java.util.Date;

import com.google.gson.JsonObject;
import com.xmpl.lib.db.type.GENDER;

public class Student {
	private String name;
	private String nationalId;
	private Date birthDate;
	private String studentNo;
	private int schoolYear;
	private int grade;
	private GENDER gender;
	private JsonObject roles;
	private JsonObject registerProperties;
	private JsonObject userProperties;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNationalId() {
		return nationalId;
	}
	
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getStudentNo() {
		return studentNo;
	}
	
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	
	public int getSchoolYear() {
		return schoolYear;
	}
	
	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public GENDER getGender() {
		return gender;
	}
	
	public void setGender(GENDER gender) {
		this.gender = gender;
	}
	
	public JsonObject getRoles() {
		return roles;
	}
	
	public void setRoles(JsonObject roles) {
		this.roles = roles;
	}
	
	public JsonObject getRegisterProperties() {
		return registerProperties;
	}
	
	public void setRegisterProperties(JsonObject registerProperties) {
		this.registerProperties = registerProperties;
	}
	
	public JsonObject getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(JsonObject userProperties) {
		this.userProperties = userProperties;
	}
}
