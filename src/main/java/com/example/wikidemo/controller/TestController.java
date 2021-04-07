package com.example.wikidemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    
    @Value("${test.hello:TEST}")
    private String testHello;
    
    @GetMapping("/hello")
    public String hello(){
        return "hello " + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post，" + name;
    }
}
