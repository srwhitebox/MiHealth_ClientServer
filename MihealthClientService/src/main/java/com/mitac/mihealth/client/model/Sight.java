package com.mitac.mihealth.client.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sight")
public class Sight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PID")
	private String PID;
	
	@Id
	@Column(name="GradeID")
	private Integer GradeID;
	
	@Id
	@Column(name="Sem")
	private Integer Sem;
	
	@Column(name="Sight0L")
	private Integer Sight0L = null;
	
	@Column(name="Sight0R")
	private Integer Sight0R = null;
	
	@Column(name="SightL")
	private Integer SightL = null;
	
	@Column(name="SightR")
	private Integer SightR = null;
	
	@Column(name="ENear")
	private String ENear = "0";
	
	@Column(name="EFar")
	private String EFar = "0";
	
	@Column(name="ESan")
	private String ESan = "0";
	
	@Column(name="EWeak")
	private String EWeak = "0";
	
	@Column(name="ESight99")
	private String ESight99 = "0";
	
	@Column(name="ESight99State")
	private String ESight99State = null;
	
	@Column(name="ManageID")
	private String ManageID = null;
	
	@Column(name="Manage")
	private String Manage = null;
	
	@Column(name="DiopL")
	private Integer DiopL = null;
	
	@Column(name="DiopR")
	private Integer DiopR = null;
	
	@Column(name="NoProblem")
	private String NoProblem = "0";
	
	@Column(name="isDilated")
	private String isDilated = "0";
	
	@Column(name="isDilating")
	private String isDilating = "0";

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public Integer getGradeID() {
		return GradeID;
	}

	public void setGradeID(Integer gradeID) {
		GradeID = gradeID;
	}

	public Integer getSem() {
		return Sem;
	}

	public void setSem(Integer sem) {
		Sem = sem;
	}

	public Integer getSight0L() {
		return Sight0L;
	}

	public void setSight0L(Integer sight0l) {
		Sight0L = sight0l;
	}

	public Integer getSight0R() {
		return Sight0R;
	}

	public void setSight0R(Integer sight0r) {
		Sight0R = sight0r;
	}

	public Integer getSightL() {
		return SightL;
	}

	public void setSightL(Integer sightL) {
		SightL = sightL;
	}

	public Integer getSightR() {
		return SightR;
	}

	public void setSightR(Integer sightR) {
		SightR = sightR;
	}

	public String getENear() {
		return ENear;
	}

	public void setENear(String eNear) {
		ENear = eNear;
	}

	public String getEFar() {
		return EFar;
	}

	public void setEFar(String eFar) {
		EFar = eFar;
	}

	public String getESan() {
		return ESan;
	}

	public void setESan(String eSan) {
		ESan = eSan;
	}

	public String getEWeak() {
		return EWeak;
	}

	public void setEWeak(String eWeak) {
		EWeak = eWeak;
	}

	public String getESight99() {
		return ESight99;
	}

	public void setESight99(String eSight99) {
		ESight99 = eSight99;
	}

	public String getESight99State() {
		return ESight99State;
	}

	public void setESight99State(String eSight99State) {
		ESight99State = eSight99State;
	}

	public String getManageID() {
		return ManageID;
	}

	public void setManageID(String manageID) {
		ManageID = manageID;
	}

	public String getManage() {
		return Manage;
	}

	public void setManage(String manage) {
		Manage = manage;
	}

	public Integer getDiopL() {
		return DiopL;
	}

	public void setDiopL(Integer diopL) {
		DiopL = diopL;
	}

	public Integer getDiopR() {
		return DiopR;
	}

	public void setDiopR(Integer diopR) {
		DiopR = diopR;
	}

	public String getNoProblem() {
		return NoProblem;
	}

	public void setNoProblem(String noProblem) {
		NoProblem = noProblem;
	}

	public String getIsDilated() {
		return isDilated;
	}

	public void setIsDilated(String isDilated) {
		this.isDilated = isDilated;
	}

	public String getIsDilating() {
		return isDilating;
	}

	public void setIsDilating(String isDilating) {
		this.isDilating = isDilating;
	}	
}
