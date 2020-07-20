package com.neu.store.pojo;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.HashSet;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;


@Entity
@Table(name="category")
public class Category {
	
@Column(name="title", unique=true, nullable = false)
    private String title;


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryID", unique = true, nullable = false)
    private long categoryId;
	
	    public Category() {
    }

    
	@ManyToMany
    @JoinTable (
       name="category_and_dvert",
       joinColumns = {@JoinColumn(name="categoryID", nullable = false, updatable = false, referencedColumnName="categoryID")},
       inverseJoinColumns = {@JoinColumn(name="advertID", referencedColumnName="advertID" )}
    )
	private Set<Advert> adverts = new HashSet<Advert>();

    public Category(String title) {
        this.title = title;
    }


	

	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public long getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}




	public Set<Advert> getAdverts() {
		return adverts;
	}




	public void setAdverts(Set<Advert> adverts) {
		this.adverts = adverts;
	}




	@Override 
	public String toString(){
		return title;
	}
}
