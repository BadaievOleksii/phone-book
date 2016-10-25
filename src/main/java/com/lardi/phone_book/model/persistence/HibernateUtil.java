package com.lardi.phone_book.model.persistence;

import com.lardi.phone_book.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    static final Logger LOGGER = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;


    private static SessionFactory buildSessionFactory() {
        try {

            Configuration cfg = new Configuration();
            cfg.configure();
            cfg.setProperty("hibernate.hikari.dataSource.url", AppConfig.getMysqlUrl());
            cfg.setProperty("hibernate.hikari.dataSource.user", AppConfig.getMysqlUser());
            cfg.setProperty("hibernate.hikari.dataSource.password", AppConfig.getMysqlPassword());
            SessionFactory sessionFactory = cfg.buildSessionFactory();
            LOGGER.info("Initialized Hibernate: created SessionFactory");
            return sessionFactory;
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

}
