package com.lardi.phone_book.model.dao.mysql;

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
