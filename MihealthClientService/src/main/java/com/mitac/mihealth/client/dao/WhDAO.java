package com.mitac.mihealth.client.dao;

import java.util.List;

import com.mitac.mihealth.client.model.Wh;

public interface WhDAO {
	public List<Wh> selectAll();
	public List<Wh> selectOne(String pid, Integer gradeiD, Integer sem);
	public void insert(Wh wh);
	public void update(Wh wh);
}
