package de.eberln.springboot.sam.dao;

import java.util.List;

import de.eberln.springboot.sam.orm.Subscriber;

public interface SubscriberDAO {

	public List<Subscriber> findAll();
	
	public Subscriber findById(String id);
	
	public void save(Subscriber subscriber);
	
	public void deleteById(String id);
	
}
