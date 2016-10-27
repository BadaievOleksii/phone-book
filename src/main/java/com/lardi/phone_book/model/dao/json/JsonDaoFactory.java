package com.lardi.phone_book.model.dao.json;

import com.lardi.phone_book.model.dao.DaoFactory;
import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.dao.UserDao;
import com.lardi.phone_book.model.dao.mysql.MysqlRecordDao;


public class JsonDaoFactory implements DaoFactory {
    public UserDao getUserDao(){
        return new JsonUserDao();
    }
    public RecordDao getRecordDao(){
        return new JsonRecordDao();
    }
    //...
}
