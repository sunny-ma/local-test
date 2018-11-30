package com.testjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class Application {
    /*@Autowired
    IUserService userService;

    @GetMapping("get")
    public String aa(){
        userService.findOne(111L);
        return "123";
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }*/
}
