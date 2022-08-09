package de.eberln.springboot.sam.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="emergency")
public class Emergency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="street")
	private String street;
	
	@Column(name="number")
	private int number;
	
	@Column(name="postalcode")
	private String postalCode;
	
	@Column(name="leitstelle")
	private String leitstelle;
	
	public Emergency() {
		
	}

	public Emergency(String street, int number, String postalCode, String leitstelle) {
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
		this.leitstelle = leitstelle;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLeitstelle() {
		return leitstelle;
	}

	public void setLeitstelle(String leitstelle) {
		this.leitstelle = leitstelle;
	}
	
	
	
	
}
