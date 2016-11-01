package com.lardi.phone_book.model.dao.json;

import com.google.gson.Gson;
import com.lardi.phone_book.config.AppConfig;
import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class JsonUserDao extends JsonBaseDao<User> implements UserDao {

    protected static final Logger LOG = LogManager.getLogger(JsonUserDao.class);

    public JsonUserDao(){
        fileName = AppConfig.getJsonUsersFile();
    }


    @Override
    public void add(User newUser){
        User lastUser = getLastEntity();
        int id = 1;
        if(lastUser != null) {
            id = lastUser.getUserId() + 1;
        }
        newUser.setUserId(id);

        addEntity(newUser);
    }

    @Override
    public List<User> getList() {


        return getEntitiesList();



    }

    public User getByUsername(String username){
        List<User> users = getEntitiesList();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
