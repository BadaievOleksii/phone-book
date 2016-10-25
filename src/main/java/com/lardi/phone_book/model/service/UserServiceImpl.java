package com.lardi.phone_book.model.service;


import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.repository.RepositoryFactory;
import com.lardi.phone_book.model.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    protected static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    RepositoryFactory repositoryFactory;

    UserRepository repository;

    public UserServiceImpl(){

    }



    private UserRepository getUserRepository(){
        if(repository == null){
            repository = repositoryFactory.getUserRepository();
        }
        return repository;
    }

    public List<User> getAll(){
        return getUserRepository().getAll();
    }
}
