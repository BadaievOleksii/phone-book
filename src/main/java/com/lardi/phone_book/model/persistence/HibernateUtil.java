package com.lardi.phone_book.model.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    static final Logger LOGGER = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;


    private static SessionFactory buildSessionFactory() {
        try {

            Configuration cfg = new Configuration();
            cfg.configure();
            cfg.setProperty("hibernate.hikari.dataSource.url", dbUrl);
            cfg.setProperty("hibernate.hikari.dataSource.user", dbUser);
            cfg.setProperty("hibernate.hikari.dataSource.password", dbPassword);
            SessionFactory sessionFactory = cfg.buildSessionFactory();
            LOGGER.info("Initialized Hibernate: created SessionFactory");
            return sessionFactory;

            /*
            SessionFactory factory = new Configuration().configure().buildSessionFactory();
            LOGGER.info("Initialized Hibernate: created SessionFactory");
            return factory;*/
        }
        catch (Exception ex) {
            LOGGER.fatal("Initial SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }





    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
        LOGGER.info("Shutting down Hibernate: closing SessionFactory");
    }

    public static void setDbUrl(String dbUrl) {
        HibernateUtil.dbUrl = dbUrl;
    }

    public static void setDbUser(String dbUser) {
        HibernateUtil.dbUser = dbUser;
    }

    public static void setDbPassword(String dbPassword) {
        HibernateUtil.dbPassword = dbPassword;
    }
}
