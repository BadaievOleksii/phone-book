package com.lardi.phone_book.model.dao.json;

import com.google.gson.Gson;
import com.lardi.phone_book.config.AppConfig;
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


    //TODO: proper user_id for new lines
    @Override
    public void add(User newUser){
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
