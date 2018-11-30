package com.sunny;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Component
//@ConfigurationProperties(prefix = "snowflake")
public class Properties {

    public static String string;
    public static List<Entry> list;


    @Data
    @ToString
    public static class Entry{
        private Long workId;
        private Long datacenterId;
        private String ip;
    }

    public List<Entry> getList() {
        return list;
    }

    public void setList(List<Entry> list) {
        Properties.list = list;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
