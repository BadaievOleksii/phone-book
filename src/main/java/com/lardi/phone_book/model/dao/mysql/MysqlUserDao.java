package com.lardi.phone_book.model.dao.mysql;

import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class MysqlUserDao extends MysqlBaseDao<User> implements UserDao{

    protected static final Logger LOG = LogManager.getLogger(MysqlUserDao.class);

    private static final String LIST_ALL = "FROM User";
    private static final String FIND_BY_USERNAME = "FROM User WHERE username=";

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
        return getEntitiesList(LIST_ALL);
    }


    public User getByUsername(String username){
        List<User> users = getEntitiesList(FIND_BY_USERNAME + "'" + username + "'");
        if(users.isEmpty()){
            return null;
        } else {
            return users.get(0);
        }
    }
}
