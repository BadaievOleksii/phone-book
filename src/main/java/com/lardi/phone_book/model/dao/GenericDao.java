package com.lardi.phone_book.model.dao;



import com.lardi.phone_book.model.entity.BaseEntity;

import java.util.List;


public interface GenericDao<T extends BaseEntity> {
    public void add(T entity);
    public void update(T entity);
    public void delete(T entity);
    public List<T> getList();
}
