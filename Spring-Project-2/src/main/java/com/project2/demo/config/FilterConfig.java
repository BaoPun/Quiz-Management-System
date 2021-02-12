package com.project2.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project2.demo.controllers.SecurityFilter;

@Configuration
public class FilterConfig {

    // uncomment this and comment the @Component in the filter class definition to register only for a url pattern
    @Bean
    public FilterRegistrationBean<SecurityFilter> loginFilter() {
        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<SecurityFilter>();

        registrationBean.setFilter(new SecurityFilter());

        registrationBean.addUrlPatterns("/s/*");

        return registrationBean;

    }

}
