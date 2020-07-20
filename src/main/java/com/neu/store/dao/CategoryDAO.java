package com.neu.store.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.HibernateException;

import com.neu.store.exception.CategoryException;
import com.neu.store.pojo.Category;


public class CategoryDAO extends DAO {

    public Category get(String title) throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category where title= :title");
            q.setString("title",title);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }

    public List<Category> list() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List<Category> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }

    public Category create(String title) throws CategoryException {
        try {
            begin();
            Category cat = new Category(title);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Exception while creating category: " + e.getMessage());
        }
    }

    public void update(Category category) throws CategoryException {
        try {
            begin();
            getSession().update(category);
             commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("category cannot be saved", e);
        }
    }

    public void delete(Category category) throws CategoryException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Category cannot be deleted", e);
        }
    }
}