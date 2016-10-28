package com.lardi.phone_book.model.dao.json;

import com.lardi.phone_book.config.AppConfig;
import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.dao.mysql.MysqlBaseDao;
import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class JsonRecordDao extends JsonBaseDao<Record> implements RecordDao {

    protected static final Logger LOG = LogManager.getLogger(JsonRecordDao.class);

    public JsonRecordDao(){
        fileName = AppConfig.getJsonRecordsFile();
    }


    @Override
    public List<Record> getList() {
        return getEntitiesList();
    }

    public List<Record> getByOwnerId(int ownerId){
        List<Record> allRecords = getEntitiesList();
        List<Record> ownerRecords = new ArrayList<>();
        for(Record record : allRecords){
            if(record.getOwnerId() == ownerId){
                ownerRecords.add(record);
            }
        }

        return ownerRecords;
    }
}
