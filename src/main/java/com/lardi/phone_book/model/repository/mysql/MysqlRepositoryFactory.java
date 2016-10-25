package com.lardi.phone_book.model.repository.mysql;

import com.lardi.phone_book.model.repository.RepositoryFactory;
import com.lardi.phone_book.model.repository.UserRepository;


public class MysqlRepositoryFactory implements RepositoryFactory {
    public UserRepository getUserRepository(){
        return new MysqlUserRepository();
    }
}
