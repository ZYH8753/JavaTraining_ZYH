package com.zyh.javatraining.weekfive.mustone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Bean(name = "person2")
    public Person person2() {
        return new Person(2,"Test 2");
    }
}
