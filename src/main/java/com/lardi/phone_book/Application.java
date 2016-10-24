package com.lardi.phone_book;

import com.lardi.phone_book.config.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class Application {

    protected static final Logger LOG = LogManager.getLogger(Application.class);

   //@Value("${lardi.conf}")
    //private static String teststr;

    public static void main(String[] args) throws Exception {

        //for(String s : args){
        //    LOGGER.debug(s);
        //}



        SpringApplication.run(Application.class, args);
    }

}