package com.lardi.phone_book.model.repository.mysql;

import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.persistence.HibernateUtil;
import com.lardi.phone_book.model.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class MysqlUserRepository implements UserRepository {

    protected static final Logger LOG = LogManager.getLogger(MysqlUserRepository.class);


    public List<User> getAll(){
        LOG.debug("Getting all");




        Transaction tx = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> list = new ArrayList<User>();
        try {
            tx = session.beginTransaction();
            List dbList = session.createQuery("FROM User").list();
            for (Object entry : dbList) {
                list.add((User) entry);
            }
            tx.commit();
            LOG.debug("Retrieved list of entities (" + list.size() + ") from MySQL db");
        } catch (Exception e) {
            LOG.error("Could not get list of entities", e);
        } finally {
            session.close();
        }
        return list;
    }
}
