package com.lardi.phone_book.model.service;


import com.lardi.phone_book.model.entity.Record;

import java.util.List;

public interface RecordService {
    public List<Record> getAll();
    public List<Record> getByOwnerId(int ownerId);

}
