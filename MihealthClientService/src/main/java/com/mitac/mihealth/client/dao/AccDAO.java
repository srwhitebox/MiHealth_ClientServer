package com.mitac.mihealth.client.dao;

import java.util.List;

import com.mitac.mihealth.client.model.Acc;

public interface AccDAO {
	public List<Acc> selectAll();
	public List<Acc> selectOne(int accid);
	public void insert(Acc acc);
	public void update(Acc acc);
}
