package com.mitac.mihealth.client.dao;

import java.util.List;

import com.mitac.mihealth.client.model.SightManage;

public interface SightManageDAO {
	public List<SightManage> selectAll();
	public List<SightManage> selectOne(String manageid);
}
