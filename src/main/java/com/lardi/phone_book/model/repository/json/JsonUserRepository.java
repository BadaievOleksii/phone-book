package com.lardi.phone_book.model.repository.json;

import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by ALEX on 25.10.2016.
 */
public class JsonUserRepository implements UserRepository {

    protected static final Logger LOG = LogManager.getLogger(JsonUserRepository.class);

    public List<User> getAll(){
        LOG.debug("Getting all");
        return null;
    }

}
