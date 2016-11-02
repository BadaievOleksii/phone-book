package com.lardi.phone_book.test;

import com.lardi.phone_book.model.dao.json.JsonUserDao;
import com.lardi.phone_book.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JsonUserDaoTest {

    protected static final Logger LOG = LogManager.getLogger(JsonUserDaoTest.class);

    private JsonUserDao jsonUserDao;

    private User userFoo;

    @Before
    public void setUp(){
        jsonUserDao = new JsonUserDao();
        jsonUserDao.setFileName("C:/LardiData/users.json");

        userFoo = new User();
        userFoo.setUserId(-1);
        userFoo.setUsername("Foo");
        userFoo.setPassword("Foo");
        userFoo.setPasswordConfirm("Foo");
        userFoo.setFio("Foo");
    }

    @Test
    public void testGetByUsername() {

        jsonUserDao.add(userFoo);

        User user2 = jsonUserDao.getByUsername("Foo");


        assertThat(user2).isNotNull();
        assertThat(user2.getUsername()).isEqualTo(userFoo.getUsername());
        assertThat(user2.getPassword()).isEqualTo(userFoo.getPassword());
        assertThat(user2.getFio()).isEqualTo(userFoo.getFio());


        jsonUserDao.delete(userFoo);
    }

    @Test
    public void testGetList() {
        jsonUserDao.add(userFoo);

        List<User> users = jsonUserDao.getList();

        assertThat(!users.isEmpty());

        boolean contains = false;
        for(User u : users){
            if((u.getUsername().equals(userFoo.getUsername())) &&
                    (u.getPassword().equals(userFoo.getPassword())) &&
                    (u.getFio().equals(userFoo.getFio()))  )
            {
                contains = true;
                break;
            }
        }
        assertThat(contains);


        jsonUserDao.delete(userFoo);
    }

    @Test
    public void testAddAndDelete() {
        jsonUserDao.add(userFoo);
        User user2 = jsonUserDao.getByUsername("Foo");
        assertThat(user2).isNotNull();


        jsonUserDao.delete(userFoo);
        user2 = jsonUserDao.getByUsername("Foo");
        assertThat(user2).isNull();
    }
}
