package com.neu.store.dao;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;

import com.neu.store.exception.AdvertException;
import com.neu.store.pojo.Advert;
import com.neu.store.pojo.Category;

import org.hibernate.HibernateException;


public class AdvertDAO extends DAO {

    public Advert create(Advert advert)
            throws AdvertException {
        try {
            begin();            
            getSession().save(advert);     
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Advert advert)
            throws AdvertException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    }
    
    public List<Advert> list() throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Advert");
            List<Advert> adverts = q.list();
            commit();
            return adverts;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    	
    }
}