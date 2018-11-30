package com.springcloudtest;

import com.springcloudtest.test.IdTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableEurekaServer
@SpringBootApplication
@Configuration
public class Eurekaserver {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(Eurekaserver.class).web(true).run(args);

    }
}
