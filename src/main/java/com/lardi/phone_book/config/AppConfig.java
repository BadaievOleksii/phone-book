package com.lardi.phone_book.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:${lardi.conf}")

public class AppConfig {


    protected static final Logger LOG = LogManager.getLogger(AppConfig.class);


    @Autowired
    Environment env;

    @Value("${lardi.dbType}")
    private String dbType;

    @Bean
    public String configureDatabase(){

        return "";
    }



    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
