package com.neu.store.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="book")
@PrimaryKeyJoinColumn(name = "personID")
public class Book{

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "customer"))
	@Column(name = "cartID", unique = true, nullable = false)
	private long id;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Customer customer;
	
	@ManyToMany
    @JoinTable (
       name="cart_and_advert",
       joinColumns = {@JoinColumn(name="cartID", nullable = false, updatable = false, referencedColumnName="cartID")},
       inverseJoinColumns = {@JoinColumn(name="advertID", referencedColumnName="advertID" )}
    )
	private Set<Advert> adverts = new HashSet<Advert>();
	
	@Column(name="title")
	private String title;
	
	@Column(name="category")
	private String category;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name="totalprice")
	private String totalprice;

	public Book(){
		
	}
	
	public Book(String title, String category, String filename, String totalprice) {
		this.title = title;
		this.category = category;
		this.filename = filename;
		this.totalprice = totalprice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getUser() {
		return customer;
	}

	public void setUser(Customer customer) {
		this.customer = customer;
	}

	public Set<Advert> getAdverts() {
		return adverts;
	}

	public void setAdverts(Set<Advert> adverts) {
		this.adverts = adverts;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
	
	
}
