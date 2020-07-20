package com.neu.store.dao;

import com.neu.store.exception.CartException;
import com.neu.store.exception.CategoryException;
import com.neu.store.pojo.Advert;
import com.neu.store.pojo.Book;
import com.neu.store.pojo.Category;
import com.neu.store.pojo.Customer;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

public class BookDAO extends DAO {
	
	public BookDAO(){
	
	}

	public Book insert(Book book) throws CartException {
		try{
			begin();            
			getSession().save(book);     
            commit();
            return book;
		} catch (HibernateException e){
			rollback();
            throw new CartException("book cannot be saved", e);
		}
	}
	
	public void update(Book book) throws CategoryException {
        try {
            begin();
            getSession().update(book);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("book cannot be saved", e);
        }
    }
	
	public Customer update(Customer customer) throws CategoryException {
        try {
            begin();
            getSession().update(customer);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("book cannot be saved", e);
        }
        return customer;
    }
	
	public List<Book> list(){
		begin();
		Query q = getSession().createQuery("from Book");
		List<Book> cart1 = q.list();
		commit();
		return cart1;
	}
	
	public Book updateCart(Book book) throws CategoryException {
        try {
            begin();
            getSession().update(book);
            commit();
            return book;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("book cannot be saved", e);
        }
    }
	

}

