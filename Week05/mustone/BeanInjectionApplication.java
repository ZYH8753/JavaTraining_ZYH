package com.zyh.javatraining.weekfive.mustone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class BeanInjectionApplication {

    public static void main(String[] args) {
        readXml();
        SpringApplication.run(BeanInjectionApplication.class,args);
    }

    public static void readXml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person) context.getBean("person1");
        System.out.println(person.toString());
    }


}
