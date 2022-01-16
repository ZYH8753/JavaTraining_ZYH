package com.zyh.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StudentProperties.class)
@ConditionalOnClass(Student.class)
@ConditionalOnProperty(prefix = "student",value = "enabled",matchIfMissing = true)
public class StudentConfiguration {
    @Autowired
    private StudentProperties studentProperties;

    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student student(){
        Student student = new Student();
        student.setName(studentProperties.getName());
        student.setId(studentProperties.getId());
        return student;
    }
}