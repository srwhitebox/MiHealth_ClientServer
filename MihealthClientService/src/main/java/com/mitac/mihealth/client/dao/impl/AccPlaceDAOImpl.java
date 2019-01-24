package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mitac.mihealth.client.model.AccPlace;
import com.mitac.mihealth.client.dao.AccPlaceDAO;

public class AccPlaceDAOImpl implements AccPlaceDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@SuppressWarnings("unchecked")
	public List<AccPlace> list() {
		Session session = this.sessionFactory.openSession();
		List<AccPlace> list = session.createQuery("from AccPlace").list();
		session.close();
		return list;
	}
}
