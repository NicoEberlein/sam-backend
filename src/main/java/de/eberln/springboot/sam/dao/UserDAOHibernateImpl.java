package de.eberln.springboot.sam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.eberln.springboot.sam.orm.User;

@Repository
public class UserDAOHibernateImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public List<User> findAll() {
		
		Session session = entityManager.unwrap(Session.class);
		
		List<User> list = session.createQuery("from User").getResultList();
		
		return list;
	}

	@Override
	@Transactional
	public User findById(int id) {
		
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(User.class, id);
	}

	@Override
	@Transactional
	public User findByEmail(String email) {
		
		Session session = entityManager.unwrap(Session.class);
		
		try {
			
			User user = (User) session.createQuery("from User where email=:mail").setParameter("mail", email).getSingleResult();
			return user;
			
		}catch(NoResultException e) {
			
			System.out.println("Exception");
			return null;
		}
		
	}


	@Override
	public void deleteByEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void save(User user) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.save(user);
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
