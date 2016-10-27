package com.lardi.phone_book;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

@SpringBootApplication
public class Application {

    protected static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws Exception {


        Locale.setDefault(Locale.ENGLISH);
        //LOG.debug(Locale.getDefault().toString());

        SpringApplication.run(Application.class, args);

    }

}