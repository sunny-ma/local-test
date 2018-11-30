package com.springcloudtest.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = ComputeController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class ComputeControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void addGet() {
        Map<String,String> params = new HashMap<String, String>();
        params.put("a","1");
        params.put("b","1");
        String result = testRestTemplate.getForObject("/add?a={a}&b={b}",String.class, params);
        System.out.println(result);
    }
}