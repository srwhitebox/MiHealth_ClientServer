package com.mitac.mihealth.client.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "St")
public class St implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PID")
	private String PID;
	
	@Column(name="Guy")
	private String Guy;
	
	@Column(name="SexID")
	private Integer SexID;
	
	@Column(name="Years")
	private Integer Years;
	
	@Column(name="ClassID")
	private Integer ClassID;
	
	@Column(name="Seat")
	private Integer Seat;
		
	@Column(name="Birthday")
	private Date Birthday;
	
	@Column(name="Dad")
	private String Dad;
	
	@Column(name="Mom")
	private String Mom;
	
	@Column(name="Guardian")
	private String Guardian;
	
	@Column(name="Zip")
	private String Zip;
	
	@Column(name="Tel1")
	private String Tel1;
	
	@Column(name="Address")
	private String Address;
	
	@Column(name="EmergencyCall")
	private String EmergencyCall;
	
	@Column(name="NewClassID")
	private Integer NewClassID;
	
	@Column(name="NewSeat")
	private Integer NewSeat;
	
	@Column(name="Blood")
	private String Blood;
	
	@Column(name="GuyID")
	private String GuyID;
	
	@Column(name="Aborigine")
	private Integer Aborigine;
	
	@Column(name="StMemos")
	private String StMemos;
	
	@Column(name="Deled")
	private Integer Deled;

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getGuy() {
		return Guy;
	}

	public void setGuy(String guy) {
		Guy = guy;
	}

	public Integer getSexID() {
		return SexID;
	}

	public void setSexID(Integer sexID) {
		SexID = sexID;
	}

	public Integer getYears() {
		return Years;
	}

	public void setYears(Integer years) {
		Years = years;
	}

	public Integer getClassID() {
		return ClassID;
	}

	public void setClassID(Integer classID) {
		ClassID = classID;
	}

	public Integer getSeat() {
		return Seat;
	}

	public void setSeat(Integer seat) {
		Seat = seat;
	}

	public Date getBirthday() {
		return Birthday;
	}
	
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public String getDad() {
		return Dad;
	}

	public void setDad(String dad) {
		Dad = dad;
	}

	public String getMom() {
		return Mom;
	}

	public void setMom(String mom) {
		Mom = mom;
	}

	public String getGuardian() {
		return Guardian;
	}

	public void setGuardian(String guardian) {
		Guardian = guardian;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getTel1() {
		return Tel1;
	}

	public void setTel1(String tel1) {
		Tel1 = tel1;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmergencyCall() {
		return EmergencyCall;
	}

	public void setEmergencyCall(String emergencyCall) {
		EmergencyCall = emergencyCall;
	}

	public Integer getNewClassID() {
		return NewClassID;
	}

	public void setNewClassID(Integer newClassID) {
		NewClassID = newClassID;
	}

	public Integer getNewSeat() {
		return NewSeat;
	}

	public void setNewSeat(Integer newSeat) {
		NewSeat = newSeat;
	}

	public String getBlood() {
		return Blood;
	}

	public void setBlood(String blood) {
		Blood = blood;
	}

	public String getGuyID() {
		return GuyID;
	}

	public void setGuyID(String guyID) {
		GuyID = guyID;
	}

	public Integer getAborigine() {
		return Aborigine;
	}

	public void setAborigine(Integer aborigine) {
		Aborigine = aborigine;
	}

	public String getStMemos() {
		return StMemos;
	}

	public void setStMemos(String stMemos) {
		StMemos = stMemos;
	}

	public Integer getDeled() {
		return Deled;
	}

	public void setDeled(Integer deled) {
		Deled = deled;
	}	
}
