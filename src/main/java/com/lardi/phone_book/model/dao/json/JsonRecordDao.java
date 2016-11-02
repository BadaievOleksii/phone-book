package com.lardi.phone_book.model.dao.json;

import com.lardi.phone_book.config.AppConfig;
import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.entity.Record;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JsonRecordDao extends JsonBaseDao<Record> implements RecordDao {

    protected static final Logger LOG = LogManager.getLogger(JsonRecordDao.class);

    public JsonRecordDao(){
        fileName = AppConfig.getJsonRecordsFile();
    }

    public void add(Record record){
        int id = 1;
        Record lastRecord = getLastEntity();
        if(lastRecord != null) {
            id = lastRecord.getRecordId() + 1;
        }
        record.setRecordId(id);


        addEntity(record);
    }
    public void update(Record record){
        File file = new File(fileName);

        try {
            List<String> lines = FileUtils.readLines(file);
            for(int i = 0; i < lines.size(); i++){
                Record fileRecord = gson.fromJson(lines.get(i), Record.class);
                if(fileRecord.getRecordId() == record.getRecordId()){
                    lines.set(i, gson.toJson(record, Record.class));
                    break;
                }
            }

            FileUtils.writeLines(file, lines);
        } catch (IOException e) {
            LOG.error("Could not update entity in JSON file", e);
        }
    }
    public void delete(Record record){
        File file = new File(fileName);

        try {
            List<String> lines = FileUtils.readLines(file);
            for(int i = 0; i < lines.size(); i++){
                Record fileRecord = gson.fromJson(lines.get(i), Record.class);
                if(fileRecord.getRecordId() == record.getRecordId()){
                    lines.remove(i);
                    break;
                }
            }

            FileUtils.writeLines(file, lines);
        } catch (IOException e) {
            LOG.error("Could not delete entity in JSON file", e);
        }
    }

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

    public Record getByRecordId(int recordId) {
        List<Record> records = getEntitiesList();
        for(Record record : records){
            if(record.getRecordId() == recordId){
                return record;
            }
        }
        return null;
    }
}
