package com.lardi.phone_book.model.dao;

import com.lardi.phone_book.model.entity.User;

import java.util.List;


public interface UserDao extends GenericDao<User> {
    public User findByUsername(String username);
}
