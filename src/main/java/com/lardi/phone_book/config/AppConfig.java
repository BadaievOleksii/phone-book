package com.lardi.phone_book.config;


import com.lardi.phone_book.model.persistence.HibernateUtil;
import com.lardi.phone_book.model.repository.RepositoryFactory;
import com.lardi.phone_book.model.repository.json.JsonRepositoryFactory;
import com.lardi.phone_book.model.repository.mysql.MysqlRepositoryFactory;
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

    private static String mysqlUrl;
    private static String mysqlUser;
    private static String mysqlPassword;

    private static String jsonUsersFile;



    @Bean(name = "repositoryFactory")
    public RepositoryFactory configureRepositoryFactory(){
        switch (dbType) {
            case "mysql":
                LOG.debug("MySQL db type");
                mysqlUrl = env.getRequiredProperty("lardi.mysql.url");
                mysqlUser = env.getRequiredProperty("lardi.mysql.user");
                mysqlPassword = env.getRequiredProperty("lardi.mysql.password");

                return new MysqlRepositoryFactory();
            case "json":
                LOG.debug("JSON db type");

                //JsonConfig.setFilesPath(env.getRequiredProperty("lardi.jsonFilesPath"));
                jsonUsersFile = env.getRequiredProperty("lardi.json.usersFile");

                return new JsonRepositoryFactory();
            default:
                LOG.error("Specified DB type is not supported");

                break;
        }
        return null;
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
}
