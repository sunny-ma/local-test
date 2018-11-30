package com.springcloudtest.dao.user;

import com.netflix.discovery.converters.Auto;
import com.springcloudtest.Application;
import com.springcloudtest.web.ComputeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = Application.class)
public class UserJpaRepositoryTest {
    @Autowired
    UserJpaRepository userJpaRepository;

    @Test
    public void delete(){
        userJpaRepository.delete(1L);
    }
}
