package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mitac.mihealth.client.model.Acc;
import com.mitac.mihealth.client.dao.AccDAO;

public class AccDAOImpl implements AccDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Acc> selectAll() {
		Session session = getSessionFactory().openSession();
		List<Acc> list = session.createQuery("from Acc").list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Acc> selectOne(String pid) {		
		Session session = getSessionFactory().openSession();
		List<Acc> list = session.createQuery("from Acc where PID=:pid")
				.setString("pid", pid)
				.list();
		session.close();
		return list;
	}
	
	public void insert(Acc acc) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(acc);
		tx.commit();
		session.close();		
	}
	
	public void update(Acc acc) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(acc);
		tx.commit();
		session.close();		
	}
}
