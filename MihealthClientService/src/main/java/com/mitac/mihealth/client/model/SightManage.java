package com.mitac.mihealth.client.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SightManage")
public class SightManage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ManageID")
	private String ManageID;
	
	@Column(name="Manage")
	private String Manage;

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
}
