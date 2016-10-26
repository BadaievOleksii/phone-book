package com.lardi.phone_book.model.service;


import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.dao.DaoFactory;
import com.lardi.phone_book.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    protected static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);


    //@Autowired
    //DaoFactory daoFactory;
    @Autowired
    UserDao userDao;

    private UserDao getUserDao(){
        //if(userDao == null){
        //    userDao = daoFactory.getUserDao();
        //}
        return userDao;
    }

    public void add(User newUser){
        getUserDao().add(newUser);
    }


    public List<User> getList(){
        return getUserDao().getList();
    }
}
