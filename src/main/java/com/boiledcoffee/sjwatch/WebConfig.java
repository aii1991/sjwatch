package com.boiledcoffee.sjwatch;

import com.boiledcoffee.sjwatch.interceptor.AuthInterceptor;
import com.boiledcoffee.sjwatch.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.ui.ModelMap;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Created by juha on 2018/1/11.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    AuthInterceptor authInterceptor;
    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/rest/v1/**")
                .excludePathPatterns("/rest/v1/user/**")
                .excludePathPatterns("/rest/v1/sms/**")
                .excludePathPatterns("/rest/v1/mobile/**");

        interceptorRegistry.addInterceptor(logInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/AmazeUI-2.4.2/**").addResourceLocations("classpath:/templates/mall/AmazeUI-2.4.2/");
        registry.addResourceHandler("/AmazeUI-2.4.2/**").addResourceLocations("classpath:/templates/mall/AmazeUI-2.4.2/");
        registry.addResourceHandler("/basic/**").addResourceLocations("classpath:/templates/mall/basic/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/mall/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/mall/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/templates/mall/images/");
    }

}
