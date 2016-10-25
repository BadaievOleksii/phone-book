package com.lardi.phone_book.model.repository.json;

import com.google.gson.Gson;
import com.lardi.phone_book.config.AppConfig;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.repository.UserRepository;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class JsonUserRepository implements UserRepository {

    protected static final Logger LOG = LogManager.getLogger(JsonUserRepository.class);

    private Gson gson = new Gson();

    public List<User> getAll() {



        User u = new User();
        u.setFio("12345");
        u.setPassword("asafsg");
        u.setUsername("user");
        u.setUserId(1);

        File f = new File(AppConfig.getJsonUsersFile());
/*
        String json = gson.toJson(u);
        try {
            FileUtils.writeStringToFile(f, json, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

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


        return users;



    }

}
