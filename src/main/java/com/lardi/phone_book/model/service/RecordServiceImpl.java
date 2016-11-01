package com.lardi.phone_book.model.service;


import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordDao recordDao;

    public void add(Record record){
        recordDao.add(record);
    }

    public void update(Record record){
        recordDao.update(record);
    }

    public void delete(Record record){
        recordDao.delete(record);
    }

    public List<Record> getList(){
        return recordDao.getList();
    }

    public List<Record> getByOwnerId(int ownerId){
        return recordDao.getByOwnerId(ownerId);
    }

    public Record getByRecordId(int recordId){
        return recordDao.getByRecordId(recordId);

    }
}
