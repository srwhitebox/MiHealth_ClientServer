package com.mitac.mihealth.client.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mitac.mihealth.client.model.Sight;
import com.mitac.mihealth.client.dao.SightDAO;

public class SightDAOImpl implements SightDAO {
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Sight> selectAll() {
		Session session = getSessionFactory().openSession();
		List<Sight> list = session.createQuery("from Sight").list();
		session.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sight> selectOne(String pid, Integer gradeiD, Integer sem) {		
		Session session = getSessionFactory().openSession();
		List<Sight> list = session.createQuery("from Sight where PID=:pid and GradeID=:gradeiD and Sem=:sem")
				.setString("pid", pid)
				.setInteger("gradeiD", gradeiD)
				.setInteger("sem", sem)
				.list();
		session.close();
		return list;
	}
	
	public void insert(Sight sight) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(sight);
		tx.commit();
		session.close();		
	}
	
	public void update(Sight sight) {		
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(sight);
		tx.commit();
		session.close();		
	}
}
