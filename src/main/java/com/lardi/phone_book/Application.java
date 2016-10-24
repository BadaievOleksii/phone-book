package com.lardi.phone_book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class Application {

    protected static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws Exception {



        SpringApplication.run(Application.class, args);
    }

}