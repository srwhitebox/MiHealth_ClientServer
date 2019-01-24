package com.mitac.mihealth.client.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WHID")
public class WhId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="WHID")
	private Integer WHID;
	
	@Column(name="WHResult")
	private String WHResult;

	public Integer getWHID() {
		return WHID;
	}

	public void setWHID(Integer wHID) {
		WHID = wHID;
	}

	public String getWHResult() {
		return WHResult;
	}

	public void setWHResult(String wHResult) {
		WHResult = wHResult;
	}	
}
