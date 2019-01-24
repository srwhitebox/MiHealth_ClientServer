package com.mitac.mihealth.client.dao;

import java.util.List;

import com.mitac.mihealth.client.model.Sight;

public interface SightDAO {
	public List<Sight> selectAll();
	public List<Sight> selectOne(String pid, Integer gradeiD, Integer sem);
	public void insert(Sight sight);
	public void update(Sight sight);
}
