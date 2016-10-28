package com.lardi.phone_book.model.dao.mysql;

import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.dao.UserDao;
import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class MysqlRecordDao extends MysqlBaseDao<Record> implements RecordDao {

    protected static final Logger LOG = LogManager.getLogger(MysqlRecordDao.class);

    private static final String LIST_ALL = "FROM Record";
    private static final String GET_BY_OWNER_ID = "FROM Record WHERE ownerId=";

    public List<Record> getList(){
        return getEntitiesList(LIST_ALL);
    }

    public List<Record> getByOwnerId(int ownerId){
        return getEntitiesList(GET_BY_OWNER_ID+"'"+ownerId+"'");
    }
}
