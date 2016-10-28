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


/*
        User u = new User();
        u.setFio("12345");
        u.setPassword("asafsg");
        u.setUsername("user");
        u.setUserId(1);

        File f = new File(AppConfig.getJsonUsersFile());

        String json = gson.toJson(u);
        try {
            FileUtils.writeStringToFile(f, json, true);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<User> users = new ArrayList<>();
        try {
            List<String> lines = FileUtils.readLines(f);
            for(String json : lines){
                users.add(new Gson().fromJson(json, User.class));
            }
            LOG.debug("Retrieved list of entities (" + users.size() + ") from JSON file");
        } catch (IOException e) {
            LOG.error("Could not read entities from JSON file", e);
        }

*/
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
