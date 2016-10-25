package com.lardi.phone_book.config;


import com.lardi.phone_book.model.entity.User;
import com.lardi.phone_book.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected static final Logger LOG = LogManager.getLogger(WebSecurityConfig.class);


    //@Autowired
    //DataSource dataSource;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //LOG.debug(bean.hello);
        //UserService s = userServiceFactory.getUserService();

        List<User> a = userService.getAll();
        for(User b : a){
            LOG.debug(b.getUsername());
        }

        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");



    }
}
