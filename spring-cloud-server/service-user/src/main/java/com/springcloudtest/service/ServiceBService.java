package com.springcloudtest.service;

import com.springcloudtest.service.impl.ServiceBServiceImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SERVICE-B", fallback = ServiceBServiceImpl.class)
public interface ServiceBService {

    @RequestMapping( value = "/b_server", method = RequestMethod.GET)
    String bServer(@RequestParam("a") String a, @RequestParam("b") String b);


}
