package com.neu.store.pojo;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.FetchType;
import java.util.HashSet;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;

import javax.persistence.Table;

@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name = "personID")
public class Customer extends Person {

	@Column(name = "password")
	private String password;
	
	@Column(name = "userName")
	private String username;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Column(name = "usertype")
	private String usertype;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Email email;
	
	
	
	public Customer(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}

	public Customer() {
	
	}
	
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
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

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	
}

