package com.lardi.phone_book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setPrefix("/templates/");
        resolver.setSuffix(".jsp");
        return resolver;
    }


     /*
    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        //resolver.setPrefix("classpath:templates/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
*/


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/").setViewName("index");
        //registry.addViewController("/viewdata").setViewName("view_data");
        //registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/register").setViewName("register");

    }

}