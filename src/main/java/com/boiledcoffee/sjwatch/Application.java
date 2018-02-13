package com.boiledcoffee.sjwatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 * Created by juha on 2018/1/10.
 */
@SpringBootApplication
@ServletComponentScan("com.boiledcoffee.sjwatch.servlet")
@MapperScan("com.boiledcoffee.sjwatch.dao")
public class Application extends SpringBootServletInitializer{
    private static Class<Application> applicationClass = Application.class;

    public static void main(String[] args) {
        SpringApplication.run(applicationClass,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(applicationClass);
    }
}
