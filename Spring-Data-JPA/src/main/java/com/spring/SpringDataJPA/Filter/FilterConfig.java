package com.spring.SpringDataJPA.Filter;

import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import jakarta.servlet.Filter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> loggingFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(loggingFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1); // Set the order if you have multiple filters
        return registration;
    }

    @Bean
    public Filter loggingFilter() {
        return new LoggingFilter();
    }

    private static class LoggingFilter implements Filter {
        private Logger logger = LoggerFactory.getLogger(FilterConfig.class);

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            // Your filter logic here
            // For example:
            logger.info("Hello from: " + request.getLocalAddr());
            chain.doFilter(request, response);
        }


    }



}
