package com.lardi.phone_book.model.repository;

import com.lardi.phone_book.model.entity.User;

import java.util.List;

/**
 * Created by ALEX on 25.10.2016.
 */
public interface UserRepository {
    public List<User> getAll();
}
