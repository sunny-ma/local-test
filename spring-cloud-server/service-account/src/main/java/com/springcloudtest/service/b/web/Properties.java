package com.springcloudtest.service.b.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Properties {

    @Value("${string}")
    private String string;

    @Value("${list}")
    private List<String> lisat;

}
