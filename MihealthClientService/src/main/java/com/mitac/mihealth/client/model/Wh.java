package com.mitac.mihealth.client.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WH")
public class Wh implements Serializable {
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
	
	@Column(name="Weight")
	private BigDecimal Weight;
	
	@Column(name="Height")
	private BigDecimal Height;
	
	@Column(name="Memos")
	private String Memos;
	
	@Column(name="Hospital")
	private String Hospital;
	
	@Column(name="Diag")
	private String Diag;
	
	@Column(name="DiagID")
	private String DiagID;
	
	@Column(name="isOK")
	private String isOK;
	
	@Column(name="Waist")
	private BigDecimal Waist;

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

	public BigDecimal getWeight() {
		return Weight;
	}

	public void setWeight(BigDecimal weight) {
		Weight = weight;
	}

	public BigDecimal getHeight() {
		return Height;
	}

	public void setHeight(BigDecimal height) {
		Height = height;
	}

	public String getMemos() {
		return Memos;
	}

	public void setMemos(String memos) {
		Memos = memos;
	}

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}

	public String getDiag() {
		return Diag;
	}

	public void setDiag(String diag) {
		Diag = diag;
	}

	public String getDiagID() {
		return DiagID;
	}

	public void setDiagID(String diagID) {
		DiagID = diagID;
	}

	public String getIsOK() {
		return isOK;
	}

	public void setIsOK(String isOK) {
		this.isOK = isOK;
	}

	public BigDecimal getWaist() {
		return Waist;
	}

	public void setWaist(BigDecimal waist) {
		Waist = waist;
	}		
}
