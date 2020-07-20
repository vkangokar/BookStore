package com.neu.store.pojo;
import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.Inheritance;

import javax.persistence.InheritanceType;

import javax.persistence.Table;

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED) //table per subclass
public class Users {
	
	public Users() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userId",unique=true, nullable = false)
	private int userId;
	
	@Column(name="fName")
	private String fName;
	
	@Column(name="lName")
	private String lName;
	
	@Column(name="phoneNumber")
	private String phoneNumber;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFName() {
		return fName;
	}

	public void setFirstName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return lName;
	}

	public void setLastName(String lName) {
		this.lName = lName;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setUserPhone(String userPhone) {
		this.phoneNumber = userPhone;
	}

	public Users(int userId, String firstName, String lastName, String phoneNumber) {
		super();
		this.userId = userId;
		this.fName = firstName;
		this.lName = lastName;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
}
