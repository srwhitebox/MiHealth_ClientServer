package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mitac.mihealth.client.model.Wh;
import com.mitac.mihealth.client.dao.WhDAO;

public class WhDAOImpl implements WhDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Wh> selectAll() {
		Session session = getSessionFactory().openSession();
		List<Wh> list = session.createQuery("from Wh").list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Wh> selectOne(String pid, Integer gradeiD, Integer sem) {		
		Session session = getSessionFactory().openSession();
		List<Wh> list = session.createQuery("from Wh where PID=:pid and GradeID=:gradeiD and Sem=:sem")
				.setString("pid", pid)
				.setInteger("gradeiD", gradeiD)
				.setInteger("sem", sem)
				.list();
		session.close();
		return list;
	}
	
	public void insert(Wh wh) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(wh);
		tx.commit();
		session.close();		
	}
	
	public void update(Wh wh) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(wh);
		tx.commit();
		session.close();		
	}
}
