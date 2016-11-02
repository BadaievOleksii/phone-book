package com.lardi.phone_book.model.dao;

import com.lardi.phone_book.model.entity.User;


public interface UserDao extends GenericDao<User> {
    public User getByUsername(String username);
}
