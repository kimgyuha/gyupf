package com.example.gyupf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해
                .allowedOrigins("http://localhost:5173",
                		 "http://114.207.245.30",           // VPS에서 직접 IP 접속
                		 "http://www.gangnamspace.com",
                         "http://gangnamspace.com",
                         "https://www.gangnamspace.com",
                         "https://gangnamspace.com"
                		 )
                .allowedMethods("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // 요청 URL
                .addResourceLocations("file:/var/www/images/");
    }
}