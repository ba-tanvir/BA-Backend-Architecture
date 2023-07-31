package com.example.demotest.config;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Handler;

@Log
@Component
public class ApiRequestInterceptor implements HandlerInterceptor {

    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        System.out.println("This preHandle Api is working");
        String uri=request.getRequestURI();
        if(uri.contains("car")||
                uri.contains("movie")||
                uri.contains("test")||
                uri.contains("game")||
                uri.contains("teacher")||
                uri.contains("employee")){
            return true;
        }

        return false;
    }
}
