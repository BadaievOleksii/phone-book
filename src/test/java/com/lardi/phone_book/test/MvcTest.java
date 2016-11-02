package com.lardi.phone_book.test;

import com.lardi.phone_book.config.AppConfig;
import com.lardi.phone_book.config.MvcConfig;
import com.lardi.phone_book.config.WebSecurityConfig;
import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.entity.Record;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.jws.WebService;
import java.sql.SQLException;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
/*@ContextConfiguration(classes={AppConfig.class, MvcConfig.class},
        loader=AnnotationConfigWebContextLoader.class)*/
@SpringBootTest
public class MvcTest {

    protected static final Logger LOG = LogManager.getLogger(MvcTest.class);

    @Autowired
    private WebApplicationContext context;


    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails
    public void testIndexWithUser() throws Exception {

        mvc
                .perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("index.jsp"));
        mvc
                .perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("index.jsp"));

        mvc
                .perform(get("/viewdata"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewdata"))
                .andExpect(forwardedUrl("viewdata.jsp"))
                .andExpect(model().attributeExists("records"));

        mvc
                .perform(get("/addrecord"))
                .andExpect(status().isOk())
                .andExpect(view().name("addrecord"))
                .andExpect(forwardedUrl("addrecord.jsp"))
                .andExpect(model().attributeExists("recordForm"));
    }

    @Test
    public void testPagesWithoutUser() throws Exception{
        mvc
                .perform(get("/home"))
                .andExpect(status().is3xxRedirection());

        mvc
                .perform(get("/"))
                .andExpect(status().is3xxRedirection());
        mvc
                .perform(get("/viewdata"))
                .andExpect(status().is3xxRedirection());
        mvc
                .perform(get("/updaterecord"))
                .andExpect(status().is3xxRedirection());
        mvc
                .perform(get("/addrecord"))
                .andExpect(status().is3xxRedirection());
        mvc
                .perform(get("/deleterecord"))
                .andExpect(status().is3xxRedirection());




        mvc
                .perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl("login.jsp"));
        mvc
                .perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(forwardedUrl("register.jsp"))
                .andExpect(model().attributeExists("userForm"));
    }
}
