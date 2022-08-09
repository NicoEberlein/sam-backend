package de.eberln.springboot.sam.dataclasses;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AuthObject {

	@Email(message = "Bitte geben Sie eine gueltige Email an")
	private String email;
	
	@NotNull(message = "Das Passwort darf nicht leer sein")
	@NotBlank(message = "Das Passwort darf nicht leer sein")
	@Length(min = 8, message = "Das Passwort muss aus mindestens 8 Zeichen bestehen")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
