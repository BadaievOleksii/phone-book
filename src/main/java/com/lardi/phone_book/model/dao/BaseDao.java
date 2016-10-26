package com.lardi.phone_book.model.dao;


import com.lardi.phone_book.model.entity.BaseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseDao<T extends BaseEntity> implements GenericDao<T> {

    protected static final Logger LOG = LogManager.getLogger(BaseDao.class);

    public void add(T entity){
        LOG.error(this.getClass().getSimpleName() + ": add is not supported for this DAO");
    }

    public void update(T entity){
        LOG.error(this.getClass().getSimpleName() + ": update is not supported for this DAO");
    }

    public void delete(T entity){
        LOG.error(this.getClass().getSimpleName() + ": delete is not supported for this DAO");
    }

    public List<T> getList(){
        LOG.error(this.getClass().getSimpleName() + ": getList is not supported for this DAO");
        return new ArrayList<>();
    }
}
