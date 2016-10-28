package com.lardi.phone_book.model.dao.mysql;


import com.google.gson.Gson;
import com.lardi.phone_book.model.dao.BaseDao;
import com.lardi.phone_book.model.dao.GenericDao;
import com.lardi.phone_book.model.entity.BaseEntity;
import com.lardi.phone_book.model.persistence.HibernateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.core.GenericTypeResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MysqlBaseDao<T extends BaseEntity> extends BaseDao<T> implements GenericDao<T>{

    //protected static final Logger LOG = LogManager.getLogger(MysqlBaseDao.class);

    protected void addEntity(T entity){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            LOG.debug("Inserted " + entity.toString() + " to MySQL db");
        } catch (Exception e) {
            LOG.error("Could not ADD entity to MySQL db", e);
        }

    }

    protected void deleteEntity(T entity){
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
            LOG.debug("Deleted " + entity.toString() + " from MySQL db");
        } catch (Exception e) {
            LOG.error("Could not DELETE entity from MySQL db", e);
        }
    }

    protected List<T> getEntitiesList(String query){
        Transaction tx = null;

        List<T> list = new ArrayList<T>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            List dbList = session.createQuery(query).list();
            for (Object entry : dbList) {
                list.add((T) entry);
            }
            tx.commit();
            LOG.debug(this.getClass().getSimpleName()
                    + ": retrieved list of entities (" + list.size() + ") from MySQL db");
        } catch (Exception e) {
            LOG.error("Could not get list of entities from MySQL db", e);
        }
        return list;
    }
}
