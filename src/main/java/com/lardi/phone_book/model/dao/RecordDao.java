package com.lardi.phone_book.model.dao;


import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.entity.User;

import java.util.List;

public interface RecordDao extends GenericDao<Record> {
    public List<Record> getByOwnerId(int ownerId);
}
