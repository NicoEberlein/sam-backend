package de.eberln.springboot.sam.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwTokenProvider {

	@Value("${application.secret}")
	private String jwtSecret;
	
	
	public String generateToken(String email) {
		
		Instant now = Instant.now();
		Instant expiration = now.plus(2, ChronoUnit.HOURS);
		
		String jws = Jwts.builder()
				.setSubject(email)
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(expiration))
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
		
		return jws;
		
	}
	
	public boolean validateToken(String token) {
		
		try {
			
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			
			return true;
			
		}catch(UnsupportedJwtException e) {
			return false;
		}catch(MalformedJwtException e) {
			return false;
		}catch(SignatureException e) {
			return false;
		}catch(IllegalArgumentException e) {
			return false;
		}catch(ExpiredJwtException e) {
			return false;
		}
		
		
	}
	
	public String getUserMailFromToken(String token) {
		
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		
	}
	
}
