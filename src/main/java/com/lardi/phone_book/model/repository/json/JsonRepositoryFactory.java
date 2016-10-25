package com.lardi.phone_book.model.repository.json;

import com.lardi.phone_book.model.repository.RepositoryFactory;
import com.lardi.phone_book.model.repository.UserRepository;
import com.lardi.phone_book.model.repository.mysql.MysqlUserRepository;

/**
 * Created by ALEX on 25.10.2016.
 */
public class JsonRepositoryFactory implements RepositoryFactory {
    public UserRepository getUserRepository(){
        return new JsonUserRepository();
    }
}
