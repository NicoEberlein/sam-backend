package de.eberln.springboot.sam.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.eberln.springboot.sam.dao.UserDAO;
import de.eberln.springboot.sam.dataclasses.AuthObject;
import de.eberln.springboot.sam.exceptions.UserAlreadyExistsException;
import de.eberln.springboot.sam.orm.User;
import de.eberln.springboot.sam.security.JwTokenProvider;

@RestController
public class AuthRestController {

	@Autowired
	private JwTokenProvider jwTokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserDAO userDao;
	
	@PostMapping("/auth/login")
	public ResponseEntity<String> login(@RequestBody AuthObject auth) {
		
		Authentication authenticated = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
		
		if(authenticated != null) {
			
			String token = jwTokenProvider.generateToken(auth.getEmail());
			return new ResponseEntity<String>(token, HttpStatus.OK);
			
		}else {
			
			return null;
			
		}
		
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<Object> register(@Valid @RequestBody AuthObject auth) throws UserAlreadyExistsException{
		
		try {
			userDetailsService.loadUserByUsername(auth.getEmail());
		}catch(UsernameNotFoundException e) {
			
			String passwordHash = passwordEncoder.encode(auth.getPassword());
			userDao.save(new User(auth.getEmail(), passwordHash));
			return new ResponseEntity<Object>(HttpStatus.OK);
			
		}
		
		throw new UserAlreadyExistsException("Der gewaehlte Benutzername existiert bereits");
		
	}
	
	@GetMapping("/auth/test/{pw}")
	public String test(@PathVariable String pw) {
		
		return passwordEncoder.encode(pw);
		
	}
	
}
