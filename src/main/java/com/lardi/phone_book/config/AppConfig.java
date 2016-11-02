package com.lardi.phone_book.config;


import com.lardi.phone_book.model.dao.DaoFactory;
import com.lardi.phone_book.model.dao.RecordDao;
import com.lardi.phone_book.model.dao.UserDao;
import com.lardi.phone_book.model.dao.json.JsonDaoFactory;
import com.lardi.phone_book.model.dao.mysql.MysqlDaoFactory;
import com.lardi.phone_book.model.persistence.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;

@Configuration
@PropertySource("file:${lardi.conf}")
public class AppConfig {


    protected static final Logger LOG = LogManager.getLogger(AppConfig.class);


    @Autowired
    Environment env;

    @Value("${lardi.dbType}")
    private String dbType;

    private static String mysqlUrl;
    private static String mysqlUser;
    private static String mysqlPassword;

    private static String jsonUsersFile;
    private static String jsonRecordsFile;



    @Bean(name = "daoFactory")
    public DaoFactory configureDaoFactory(){
        switch (dbType) {
            case "mysql":
                LOG.debug("MySQL db type");
                mysqlUrl = env.getRequiredProperty("lardi.mysql.url");
                mysqlUser = env.getRequiredProperty("lardi.mysql.user");
                mysqlPassword = env.getRequiredProperty("lardi.mysql.password");

                HibernateUtil.setMysqlUrl(mysqlUrl);
                HibernateUtil.setMysqlUser(mysqlUser);
                HibernateUtil.setMysqlPassword(mysqlPassword);

                return new MysqlDaoFactory();
            case "json":
                LOG.debug("JSON db type");

                //JsonConfig.setFilesPath(env.getRequiredProperty("lardi.jsonFilesPath"));
                jsonUsersFile = env.getRequiredProperty("lardi.json.usersFile");
                jsonRecordsFile = env.getRequiredProperty("lardi.json.recordsFile");


                return new JsonDaoFactory();
            default:
                LOG.error("Specified DB type is not supported");

                break;
        }
        return null;
    }

    @Bean(name = "userDao")
    public UserDao configureUserDao() {
        if(dbType.equals("json")){
            File file = new File(env.getRequiredProperty("lardi.json.usersFile"));
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }

        return configureDaoFactory().getUserDao();
    }

    @Bean(name = "recordDao")
    public RecordDao configureRecordDao() {
        if(dbType.equals("json")){
            File file = new File(env.getRequiredProperty("lardi.json.recordsFile"));
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }

        return configureDaoFactory().getRecordDao();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static String getMysqlUrl() {
        return mysqlUrl;
    }

    public static String getMysqlUser() {
        return mysqlUser;
    }

    public static String getMysqlPassword() {
        return mysqlPassword;
    }

    public static String getJsonUsersFile() {
        return jsonUsersFile;
    }

    public static String getJsonRecordsFile() {
        return jsonRecordsFile;
    }
}
