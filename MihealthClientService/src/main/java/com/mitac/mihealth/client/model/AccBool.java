package com.mitac.mihealth.client.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccBool")
public class AccBool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private String ID;
	
	@Column(name="Context")
	private String Context;
	
	@Column(name="OrderID")
	private Integer OrderID;
	
	@Column(name="KindID")
	private String KindID;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}

	public Integer getOrderID() {
		return OrderID;
	}

	public void setOrderID(Integer orderID) {
		OrderID = orderID;
	}

	public String getKindID() {
		return KindID;
	}

	public void setKindID(String kindID) {
		KindID = kindID;
	}	
}
