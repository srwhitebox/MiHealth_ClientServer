package com.mitac.mihealth.client.dao;

import java.util.List;

import com.mitac.mihealth.client.model.St;

public interface StDAO {
	public List<St> selectAll();
	public List<St> selectOne(String pid);
	public void insert(St st);
	public void update(St st);
}
