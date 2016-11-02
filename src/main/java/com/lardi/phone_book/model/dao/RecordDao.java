package com.lardi.phone_book.model.dao;


import com.lardi.phone_book.model.entity.Record;

import java.util.List;

public interface RecordDao extends GenericDao<Record> {
    public List<Record> getByOwnerId(int ownerId);
    public Record getByRecordId(int recordId);
}
