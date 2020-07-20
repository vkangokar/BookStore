package com.neu.store.pojo;

import javax.persistence.GeneratedValue;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Table;

import javax.persistence.OneToOne;


@Entity
@Table(name = "email")
public class Email {
	
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "customer"))
	@Column(name = "emailID", unique = true, nullable = false)
	private long id;

	@Column(name = "email_address")
	private String emailAddress;
	


	@OneToOne
	@PrimaryKeyJoinColumn
	private Customer customer;

	public Email() {
	}

	public Email(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Customer getUser() {
		return customer;
	}

	public void setUser(Customer customer) {
		this.customer = customer;
	}

	



}


