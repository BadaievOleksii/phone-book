package com.lardi.phone_book.test;


import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.entity.Record;
import com.lardi.phone_book.model.service.RecordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RecordServiceTest {

    @MockBean
    private RecordDao recordDao;

    @Autowired
    private RecordService recordService;

    List<Record> recordsList;
    Record recordFoo;

    @Before
    public void setUp(){
        recordsList = new ArrayList<Record>();

        given(this.recordDao.getList())
                .willReturn(recordsList);

        recordFoo = new Record();
        recordFoo.setRecordId(-1);
        recordFoo.setOwnerId(-1);
        recordFoo.setSurname("Foo");
        recordFoo.setPatronymic("Foo");
        recordFoo.setName("Foo");
        recordFoo.setMobilePhone("Foo");
        recordFoo.setHomePhone("Foo");
        recordFoo.setAddress("Foo");
    }

    @Test
    public void testUserService(){
        List<Record> records = recordService.getList();

        assertThat(records).isEmpty();

        recordsList.add(recordFoo);
        records = recordService.getList();

        assertThat(records).isNotEmpty();

        Record r = records.get(0);
        assertThat(r.getRecordId()).isEqualTo(recordFoo.getRecordId());
        assertThat(r.getAddress()).isEqualTo(recordFoo.getAddress());
        assertThat(r.getEmail()).isEqualTo(recordFoo.getEmail());
        assertThat(r.getHomePhone()).isEqualTo(recordFoo.getHomePhone());
        assertThat(r.getMobilePhone()).isEqualTo(recordFoo.getMobilePhone());
        assertThat(r.getName()).isEqualTo(recordFoo.getName());
        assertThat(r.getSurname()).isEqualTo(recordFoo.getSurname());
        assertThat(r.getOwnerId()).isEqualTo(recordFoo.getOwnerId());
        assertThat(r.getPatronymic()).isEqualTo(recordFoo.getPatronymic());



        recordsList.remove(0);
    }
}
