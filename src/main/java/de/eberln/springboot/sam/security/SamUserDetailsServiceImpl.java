package de.eberln.springboot.sam.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import de.eberln.springboot.sam.dao.UserDAO;

@Service
public class SamUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		de.eberln.springboot.sam.orm.User loadedUser = userDao.findByEmail(username);
		
		if(loadedUser != null) {

			UserDetails user = new User(loadedUser.getUsername(), loadedUser.getPassword(), Collections.emptyList());
			
			return user;
		
		}else {
		
			throw new UsernameNotFoundException("User with Mail " + username + " not found");
			
		}
	}

	
	
}
