package com.zyh.javatraining.weekfive.mustone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Component
public class Person implements InitializingBean, BeanNameAware {
    int id;
    String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        id = 3;
        name = "Test 3";
    }

    @Override
    public void setBeanName(String s) {
        setName("person3");
    }
}
