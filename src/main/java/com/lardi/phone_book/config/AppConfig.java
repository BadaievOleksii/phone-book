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
/*
    @Value("${lardi.mysqlUrl}")
    private String mysqlUrl;
    @Value("${lardi.mysqlUser}")
    private String mysqlUser;
    @Value("${lardi.mysqlPassword}")
    private String mysqlPassword;
*/
    //@Value("${lardi.jsonFilePath}")
    //private String jsonFilePath;



    //@Bean(name = "dataSource")
    //public DataSource configureDatabase(){

/*
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
*/
    //    return null;
    //}


    @Bean(name = "repositoryFactory")
    public RepositoryFactory configureRepositoryFactory(){
        switch (dbType) {
            case "mysql":
                LOG.debug("MySQL db type");
                HibernateUtil.setDbUrl(env.getRequiredProperty("lardi.mysqlUrl"));
                HibernateUtil.setDbUser(env.getRequiredProperty("lardi.mysqlUser"));
                HibernateUtil.setDbPassword(env.getRequiredProperty("lardi.mysqlPassword"));
                return new MysqlRepositoryFactory();
            case "json":
                LOG.debug("JSON db type");

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
}
