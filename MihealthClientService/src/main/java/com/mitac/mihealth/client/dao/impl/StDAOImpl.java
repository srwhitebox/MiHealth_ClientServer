package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mitac.mihealth.client.model.St;
import com.mitac.mihealth.client.dao.StDAO;

public class StDAOImpl implements StDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<St> selectAll() {
		Session session = getSessionFactory().openSession();
		List<St> list = session.createQuery("from St").list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<St> selectOne(String pid) {		
		Session session = getSessionFactory().openSession();
		List<St> list = session.createQuery("from St where PID=:pid").setString("pid", pid).list();
		session.close();
		return list;
	}
	
	public void insert(St st) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(st);
		tx.commit();
		session.close();		
	}
	
	public void update(St st) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(st);
		tx.commit();
		session.close();		
	}
}
