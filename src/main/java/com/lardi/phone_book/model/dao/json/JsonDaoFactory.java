package com.lardi.phone_book.model.dao.json;

import com.lardi.phone_book.model.dao.DaoFactory;
import com.lardi.phone_book.model.dao.UserDao;


public class JsonDaoFactory implements DaoFactory {
    public UserDao getUserDao(){
        return new JsonUserDao();
    }
    //...
}
