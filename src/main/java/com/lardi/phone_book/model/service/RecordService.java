package com.lardi.phone_book.model.service;


import com.lardi.phone_book.model.entity.Record;

import java.util.List;

public interface RecordService {
    public void update(Record record);
    public void delete(Record record);
    public List<Record> getList();
    public List<Record> getByOwnerId(int ownerId);
    public Record getByRecordId(int recordId);

}
