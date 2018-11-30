package com.sunny;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {
//    @Autowired
//    Properties properties;
    @Test
    public void aa(){
        for (Properties.Entry item : Properties.list) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void aaa(){
        System.out.println(SnowflakeIdWorker.nextId());
    }
}
