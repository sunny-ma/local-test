package com.springcloudtest.service.b.web;

import org.apache.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {

    private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/b_server" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        System.out.println("你好");
        return "调用次数超限！";
    }

}