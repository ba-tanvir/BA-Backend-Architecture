package com.example.demotest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ApiMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    ApiRequestInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePaths=new ArrayList<>();
        excludePaths.add("/commons/none");
        registry.addInterceptor(interceptor)
                .order(1)
                .excludePathPatterns(excludePaths)
                .addPathPatterns("/**");
    }
}
