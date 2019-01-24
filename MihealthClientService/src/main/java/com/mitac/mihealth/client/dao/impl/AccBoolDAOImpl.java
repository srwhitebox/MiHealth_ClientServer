package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mitac.mihealth.client.model.AccBool;
import com.mitac.mihealth.client.dao.AccBoolDAO;

public class AccBoolDAOImpl implements AccBoolDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	public List<AccBool> list() {
		Session session = this.sessionFactory.openSession();
		List<AccBool> list = session.createQuery("from AccBool").list();
		session.close();
		return list;
	}
}
