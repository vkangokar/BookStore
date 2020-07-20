package com.neu.store.dao;

import java.util.logging.Level;
import org.hibernate.HibernateException;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;



public class DAO {
	
    private static final Logger log = Logger.getAnonymousLogger();
    
    private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    protected DAO() {
    }

    public static Session getSession()
    {
        Session session = (Session) DAO.sessionThread.get();
        
        if (session == null)
        {
            session = sessionFactory.openSession();
            DAO.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Rollback cannot be done", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "cannot be closed", e);
        }
        DAO.sessionThread.set(null);
    }

    public static void close() {
        getSession().close();
        DAO.sessionThread.set(null);
    }
}