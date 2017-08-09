package com.ums.management.web;

import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;

public class WebApp implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext ctx) {
        System.out.println("===start===");
    }
}
