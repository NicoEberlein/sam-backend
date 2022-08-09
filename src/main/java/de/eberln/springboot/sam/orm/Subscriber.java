package de.eberln.springboot.sam.orm;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="subscriber")
public class Subscriber {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="user_agent")
	private String userAgent;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="emergency_id")
	@Valid
	private Emergency emergencyInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="callprops_id")
	@Valid
	private Callprops callProps;
	
	public Subscriber() {
		
	}

	public Subscriber(String username, String password, String userAgent, Emergency emergencyInfo,
			Callprops callProps) {
		this.username = username;
		this.password = password;
		this.userAgent = userAgent;
		this.emergencyInfo = emergencyInfo;
		this.callProps = callProps;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Emergency getEmergencyInfo() {
		return emergencyInfo;
	}

	public void setEmergencyInfo(Emergency emergencyInfo) {
		this.emergencyInfo = emergencyInfo;
	}

	public Callprops getCallProps() {
		return callProps;
	}

	public void setCallProps(Callprops callProps) {
		this.callProps = callProps;
	}
		
	
}
