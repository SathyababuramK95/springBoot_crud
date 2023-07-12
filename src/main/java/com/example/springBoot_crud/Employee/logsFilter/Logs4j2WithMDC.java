package com.example.springBoot_crud.Employee.logsFilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

//@WebFilter(filterName = "mdcFilter",urlPatterns = {"/*"})
@Component
public class Logs4j2WithMDC implements Filter {

    @Override
    public void destroy() {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        MDC.put("sessionId", servletRequest.getParameter("traceId"));
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            MDC.clear();
        }

    }

    @Override
    public void init( final FilterConfig filterConfig )
            throws ServletException {
    }


}
