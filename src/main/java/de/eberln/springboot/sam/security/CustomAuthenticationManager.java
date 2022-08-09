package de.eberln.springboot.sam.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws UsernameNotFoundException, BadCredentialsException{
		
		String username = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();

		UserDetails user = null;
		
		user = userDetailsService.loadUserByUsername(username);
		
		if(passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password);
		}
		
		throw new BadCredentialsException("Ungueltiges Passwort");
	}

	
	
}
