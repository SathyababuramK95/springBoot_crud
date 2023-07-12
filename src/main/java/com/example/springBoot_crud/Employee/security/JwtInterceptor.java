package com.example.springBoot_crud.Employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("authorization");
        System.out.println(auth);
        if(!(request.getRequestURI().contains("login") || request.getRequestURI().contains("saveEmployee") ||
                request.getRequestURI().contains("/refreshToken"))){
            jwtUtils.validateJwtToken(auth);
        }
        return super.preHandle(request, response, handler);
    }
}
