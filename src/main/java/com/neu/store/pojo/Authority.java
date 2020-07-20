package com.neu.store.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long userId;
	
	@Column(name = "userName")
	private String userName;
	

	@Column(name = "password")
	private String password;
	
	@Column(name = "authorityType")
	private String authorityType;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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

	public String getAuthorityType() {
		return authorityType;
	}

	public void setAuthorityType(String authorityType) {
		this.authorityType = authorityType;
	}

	public Authority(long userId, String userName, String password, String authorityType) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.authorityType = authorityType;
	}
	
	
	

}

