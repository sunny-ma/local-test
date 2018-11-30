package com.springcloudtest.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "idgen")
public class IdTest {

    public Map<String, String> ips;

    public IdTest(){
        System.out.println(111);
    }



    class IdEntity{
        private String address;
        private String workid;
        private String datacenterid;
    }
}
