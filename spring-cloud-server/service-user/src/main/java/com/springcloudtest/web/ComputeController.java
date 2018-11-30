package com.springcloudtest.web;

import com.springcloudtest.service.ServiceBService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComputeController {
    private final Logger logger = Logger.getLogger(getClass());

//    @Autowired
//    RestTemplate restTemplate;
    @Autowired
    private ServiceBService serviceBService;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return "From Service-A, Result is " + r;
    }

    @RequestMapping(value = "/call_server_b" ,method = RequestMethod.GET)
    public String callServerB(@RequestParam Integer a, @RequestParam Integer b) {
//        return serviceBService.bServer(String.valueOf(a), String.valueOf(b));
        return null;
    }
}
