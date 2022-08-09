package de.eberln.springboot.sam.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="callprops")
public class Callprops {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cns_enabled")
	private boolean cnsEnabled;
	
	@Column(name="natEnabled")
	private boolean natEnabled;
	
	
	public Callprops() {
		
	}

	public Callprops(boolean cnsEnabled, boolean natEnabled) {
		this.cnsEnabled = cnsEnabled;
		this.natEnabled = natEnabled;
	}

	public boolean isCnsEnabled() {
		return cnsEnabled;
	}

	public void setCnsEnabled(boolean cnsEnabled) {
		this.cnsEnabled = cnsEnabled;
	}

	public boolean isNatEnabled() {
		return natEnabled;
	}

	public void setNatEnabled(boolean natEnabled) {
		this.natEnabled = natEnabled;
	}
	
}
