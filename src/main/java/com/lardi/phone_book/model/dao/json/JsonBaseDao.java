package com.lardi.phone_book.model.dao.json;


import com.google.gson.Gson;
import com.lardi.phone_book.config.AppConfig;
import com.lardi.phone_book.model.dao.BaseDao;
import com.lardi.phone_book.model.dao.GenericDao;
import com.lardi.phone_book.model.entity.BaseEntity;
import com.lardi.phone_book.model.entity.User;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.GenericTypeResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonBaseDao<T extends BaseEntity> extends BaseDao<T> implements GenericDao<T>{

    //protected static final Logger LOG = LogManager.getLogger(JsonBaseDao.class);

    protected String fileName = "";

    protected Gson gson = new Gson();

    protected void addEntity(T entity){
        File f = new File(fileName);

        String json = gson.toJson(entity) + "\n";
        try {
            FileUtils.writeStringToFile(f, json, true);
            LOG.debug("Inserted " + entity.toString() + " to JSON file");
        } catch (IOException e) {
            LOG.error("Could not write new entity to JSON file", e);
        }
    }

    protected List<T> getEntitiesList() {

        File f = new File(fileName);


        List<T> entities = new ArrayList<>();
        try {
            List<String> lines = FileUtils.readLines(f);
            for(String json : lines){
                Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), JsonBaseDao.class);
                entities.add(gson.fromJson(json, entityType));
            }
            LOG.debug("Retrieved list of entities (" + entities.size() + ") from JSON file");
        } catch (IOException e) {
            LOG.error("Could not read entities from JSON file", e);
        }


        return entities;



    }
}
