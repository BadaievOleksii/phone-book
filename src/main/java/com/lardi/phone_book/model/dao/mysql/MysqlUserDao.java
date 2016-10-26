package com.lardi.phone_book.model.dao.mysql;

import com.lardi.phone_book.model.dao.GenericDao;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.persistence.HibernateUtil;
import com.lardi.phone_book.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


public class MysqlUserDao extends MysqlBaseDao<User> implements UserDao{

    protected static final Logger LOG = LogManager.getLogger(MysqlUserDao.class);

    private static final String LIST_ALL = "FROM User";

    public void add(User newUser){
        addEntity(newUser);
    }

    public List<User> getList(){
        /*
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
        */
        return getEntityList(LIST_ALL);
    }
}
