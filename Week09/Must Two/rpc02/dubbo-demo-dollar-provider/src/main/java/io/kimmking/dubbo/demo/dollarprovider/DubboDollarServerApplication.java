package io.kimmking.dubbo.demo.dollarprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DubboDollarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboDollarServerApplication.class, args);
	}

}
