package de.eberln.springboot.sam.dao;

import java.util.List;

import de.eberln.springboot.sam.orm.User;

public interface UserDAO {

	public List<User> findAll();
	
	public User findById(int id);
	
	public User findByEmail(String email);
	
	public void save(User user);
	
	public void deleteById(int id);
	
	public void deleteByEmail(String email);
	
}
