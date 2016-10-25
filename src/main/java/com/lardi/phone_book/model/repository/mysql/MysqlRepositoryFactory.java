package com.lardi.phone_book.model.repository.mysql;

import com.lardi.phone_book.model.repository.RepositoryFactory;
import com.lardi.phone_book.model.repository.UserRepository;

/**
 * Created by ALEX on 25.10.2016.
 */
public class MysqlRepositoryFactory implements RepositoryFactory {
    public UserRepository getUserRepository(){
        return new MysqlUserRepository();
    }
}
