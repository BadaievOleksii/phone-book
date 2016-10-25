package com.lardi.phone_book.model.repository;

import com.lardi.phone_book.model.entity.User;

import java.util.List;


public interface UserRepository {
    public List<User> getAll();
}
