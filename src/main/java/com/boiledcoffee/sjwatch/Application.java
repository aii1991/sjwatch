package com.boiledcoffee.sjwatch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by juha on 2018/1/10.
 */
@SpringBootApplication
@ServletComponentScan("com.boiledcoffee.sjwatch.servlet")
@MapperScan("com.boiledcoffee.sjwatch.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
