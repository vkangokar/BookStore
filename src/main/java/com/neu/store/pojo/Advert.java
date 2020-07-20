package com.neu.store.pojo;

import java.util.ArrayList;
import javax.persistence.Transient;
import java.util.Set;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name="advert")
public class Advert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="advertID", unique = true, nullable = false)
    private long id;
	
	@Column(name="title")// title of the advert
    private String title;
	
	@Column(name="message")//message if the advert
    private String message;
	

	
	@Column(name = "filename")//filename used
	private String filename; 
	
	@Column(name = "price")//required price
	private Float price;
	
	
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Float getPrice() {
		return price;
	}

	@ManyToOne
	private Customer customer; 
	
	@ManyToMany(mappedBy="adverts")
	private Set<Category> categories = new HashSet<Category>();

	@ManyToMany(mappedBy="adverts")
	private Set<Book> book = new HashSet<Book>();
	
	
	@Transient
	int postedBy;
	
	
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	@Transient
	private CommonsMultipartFile photo;

    public Advert(String title, String message, Customer customer, Category catergory, Book book) {
        this.title = title;
        this.message = message;
        this.customer = customer;      
        this.categories.add(catergory); 
        this.book.add(book);
    }

    public Advert() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Customer getUser() {
		return customer;
	}

	public void setUser(Customer customer) {
		this.customer = customer;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<Book> getCart() {
		return book;
	}

	public void setCart(Set<Book> book) {
		this.book = book;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

	

    
  

   
}
