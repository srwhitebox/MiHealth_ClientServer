package com.mitac.mihealth.client.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AccPlace")
public class AccPlace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private String ID;
	
	@Column(name="Context")
	private String Context;

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
}
