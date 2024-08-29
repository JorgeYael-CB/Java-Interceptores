package com.yael.curso.springboot.interceptor.springboot_interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;





@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("timeInterceptor")
    private HandlerInterceptor timeInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.timeInterceptor).addPathPatterns("/api/bar", "/api/foo"); // solo a estas rutas
        // registry.addInterceptor(this.timeInterceptor).excludePathPatterns("/api/bar", "/api/foo"); // excluye estas rutas
        // registry.addInterceptor(this.timeInterceptor).addPathPatterns("/api/**"); // agrega todas las rutas que inicien con /api/
    }

}
