package com.lardi.phone_book.test;

import com.lardi.phone_book.model.dao.json.JsonUserDao;
import com.lardi.phone_book.model.dao.mysql.MysqlUserDao;
import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.persistence.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MysqlUserDaoTest {
    protected static final Logger LOG = LogManager.getLogger(JsonUserDaoTest.class);

    private MysqlUserDao mysqlUserDao;

    private User userFoo;

    @Before
    public void setUp(){
        mysqlUserDao = new MysqlUserDao();
        HibernateUtil.setMysqlUrl("jdbc:mysql://localhost:3306/lardi_phonebook");
        HibernateUtil.setMysqlUser("root");
        HibernateUtil.setMysqlPassword("admin");

        userFoo = new User();
        userFoo.setUserId(-1);
        userFoo.setUsername("Foo");
        userFoo.setPassword("Foo");
        userFoo.setPasswordConfirm("Foo");
        userFoo.setFio("Foo");
    }

    @Test
    public void testGetByUsername()
    {
        mysqlUserDao.add(userFoo);

        User user = mysqlUserDao.getByUsername("Foo");
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo(userFoo.getUsername());
        assertThat(user.getPassword()).isEqualTo(userFoo.getPassword());
        assertThat(user.getFio()).isEqualTo(userFoo.getFio());

        mysqlUserDao.delete(userFoo);
    }
}
