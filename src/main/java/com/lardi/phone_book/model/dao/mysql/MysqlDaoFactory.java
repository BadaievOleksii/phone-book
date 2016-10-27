package com.lardi.phone_book.model.dao.mysql;

import com.lardi.phone_book.model.dao.DaoFactory;
import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.dao.UserDao;


public class MysqlDaoFactory implements DaoFactory {
    public UserDao getUserDao(){
        return new MysqlUserDao();
    }
    public RecordDao getRecordDao(){
        return new MysqlRecordDao();
    }
    //...
}
