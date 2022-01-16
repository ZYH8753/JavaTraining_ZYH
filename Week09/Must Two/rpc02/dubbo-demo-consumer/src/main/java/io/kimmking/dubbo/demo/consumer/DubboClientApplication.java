package io.kimmking.dubbo.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class DubboClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DubboClientApplication.class, args);
	}
}
