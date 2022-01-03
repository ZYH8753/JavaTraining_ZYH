package io.kimmking.dubbo.demo.rmbprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DubboRmbServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboRmbServerApplication.class, args);
	}

}
