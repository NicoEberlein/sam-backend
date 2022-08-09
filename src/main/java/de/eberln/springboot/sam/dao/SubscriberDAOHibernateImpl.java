package de.eberln.springboot.sam.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.eberln.springboot.sam.orm.Subscriber;

@Repository
public class SubscriberDAOHibernateImpl implements SubscriberDAO{

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Subscriber> findAll() {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<Subscriber> query = session.createQuery("from Subscriber", Subscriber.class);
		
		List<Subscriber> subscribers = query.getResultList();
		
		return subscribers;
	}
	
	@Override
	@Transactional
	public Subscriber findById(String id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Subscriber sub = session.get(Subscriber.class, id);
		
		return sub;
		
	}

	@Override
	@Transactional
	public void save(Subscriber subscriber) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(subscriber);
		
	}
	

	@Override
	@Transactional
	public void deleteById(String username) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Subscriber subscriber = session.get(Subscriber.class, username);
		
		session.delete(subscriber);
		
	}
	
	
}
