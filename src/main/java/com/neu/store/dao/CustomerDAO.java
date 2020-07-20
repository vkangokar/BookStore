package com.neu.store.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.store.exception.UserException;
import com.neu.store.pojo.Email;
import com.neu.store.pojo.Customer;



public class CustomerDAO extends DAO {

	public CustomerDAO() {
	}

	public Customer get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			Customer customer = (Customer) q.uniqueResult();
			//System.out.println(user.getUsertype());
			commit();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Customer cannot be get " + username, e);
		}
	}
	
	public Customer get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where personID= :personID");
			q.setInteger("personID", userId);		
			Customer customer = (Customer) q.uniqueResult();
			commit();
			return customer;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Customer cannot be get" + userId, e);
		}
	}

	public Customer register(Customer u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(u.getEmail().getEmailAddress());
			Customer customer = new Customer(u.getUsername(), u.getPassword(), u.getUsertype());

			customer.setFirstName(u.getFirstName());
			customer.setLastName(u.getLastName());
			customer.setEmail(email);
			email.setUser(customer);
			getSession().save(customer);
			commit();
			return customer;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception creating customer: " + e.getMessage());
		}
	}

	public void delete(Customer customer) throws UserException {
		try {
			begin();
			getSession().delete(customer);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Cannot delete the customer " + customer.getUsername(), e);
		}
	}
}