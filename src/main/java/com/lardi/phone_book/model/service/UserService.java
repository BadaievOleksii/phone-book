package com.lardi.phone_book.model.service;

import com.lardi.phone_book.model.entity.User;

import java.util.List;

public interface UserService {
    public void add(User newUser);
    public List<User> getList();
}
