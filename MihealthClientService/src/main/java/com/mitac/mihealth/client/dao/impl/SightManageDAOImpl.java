package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mitac.mihealth.client.model.SightManage;
import com.mitac.mihealth.client.dao.SightManageDAO;

public class SightManageDAOImpl implements SightManageDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

    @SuppressWarnings("unchecked")
	public List<SightManage> selectAll() {
		Session session = getSessionFactory().openSession();
		List<SightManage> list = session.createQuery("from SightManage").list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SightManage> selectOne(String manageid) {		
		Session session = getSessionFactory().openSession();
		List<SightManage> list = session.createQuery("from SightManage where ManageID=:manageid").setString("manageid", manageid).list();
		session.close();
		return list;
	}
}
