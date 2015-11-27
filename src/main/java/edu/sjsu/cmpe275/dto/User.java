package edu.sjsu.cmpe275.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

@Entity
@Table(name = "USER")
@Service
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	User() {
	}
	
	@Column(name="USER_NAME") 
	@NotNull
	private String userName;
	
	@Column(name="PASSWORD") 
	@NotNull
	private String password;
	
	@Id
	@Column(name="EMAIL")
	@NotNull
	private String email;
	
	@Column(name="HASHCODE")
	@NotNull
	private Integer hashCode;
	
	@Column(name="Validated")
	private String validated = "false";
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getHashCode() {
		return hashCode;
	}

	public void setHashCode(Integer hashCode) {
		this.hashCode = hashCode;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}


}
