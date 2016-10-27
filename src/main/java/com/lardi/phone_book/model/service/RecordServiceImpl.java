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

    public List<Record> getAll(){
        return recordDao.getList();
    }
}
