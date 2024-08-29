package com.yael.curso.springboot.interceptor.springboot_interceptor.Interceptors;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        HandlerMethod method = ((HandlerMethod)handler);
        this.logger.info("LoadingTimeInterceptor: preHandle() entrando ...." + method.getMethod().getName());

        long start = System.currentTimeMillis();

        // pasar data al request
        request.setAttribute("start", start);

        //Simular espera
        Random random = new Random();
        int delay = random.nextInt(500);
        Thread.sleep(delay);

        Map<String, Object> json = new HashMap<>();
        json.put("error", "No tienes acceso a esta api");
        json.put("date", new Date().toString());

        // ToJson
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);

        // Enviar el json
        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().write(jsonString);

        return false; // no continua la ejecucion del Api, no retorna nada.
        // return true // continua la REST API;
    }

    @Override
    public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler,
        @Nullable ModelAndView modelAndView) throws Exception {

        HandlerMethod method = ((HandlerMethod)handler);
        this.logger.info("LoadingTimeInterceptor: postHandle() saliendo ...." + method.getMethod().getName());
        long end = System.currentTimeMillis();

        //Leer data del request
        long start = (long)request.getAttribute("start");
        double resultado = end - start;

        this.logger.info("Tiempo transcurrido en segundos: " + resultado);
    }

}
