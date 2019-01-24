package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mitac.mihealth.client.model.WhId;
import com.mitac.mihealth.client.dao.WhIdDAO;

public class WhIdDAOImpl implements WhIdDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	public List<WhId> list() {
		Session session = this.sessionFactory.openSession();
		List<WhId> list = session.createQuery("from WhId").list();
		session.close();
		return list;
	}
}
