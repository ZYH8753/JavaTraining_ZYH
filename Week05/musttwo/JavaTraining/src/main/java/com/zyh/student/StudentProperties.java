package com.zyh.student;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "student")
public class StudentProperties {
    private static final String DEFAULT_NAME = "ZYH";
    private static final int DEFAULT_ID = 1;
    private String name = DEFAULT_NAME;
    private int id = DEFAULT_ID;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
