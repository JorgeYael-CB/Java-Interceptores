package com.yael.curso.springboot.interceptor.springboot_interceptor.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api")
public class FooController {

    @GetMapping("/foo")
    public Map<String, Object> foo(){
        final Map<String, Object> map = new HashMap<>();

        return Collections.singletonMap("message", "Handler foo del controller.");
    }


    @GetMapping("/bar")
    public Map<String, Object> bar(){

        return Collections.singletonMap("message", "Handler bar del controller.");
    }


    @GetMapping("/baz")
    public Map<String, Object> baz(){

        return Collections.singletonMap("message", "Handler baz del controller.");
    }

}
