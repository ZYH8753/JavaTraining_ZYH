package io.kimmking.dubbo.demo.consumer;

import org.dromara.hmily.spring.annotation.RefererAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DubboClientApplication extends SpringBootServletInitializer {

//	@DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
//    private UserService userService;
//
//    @DubboReference(version = "1.0.0")
//    private DollarAccountService dollarAccountService;
//
//    @DubboReference(version = "1.0.0")
//    private RmbAccountService rmbAccountService;

	public static void main(String[] args) {
		SpringApplication.run(DubboClientApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}

	@Bean
	public BeanPostProcessor refererAnnotationBeanPostProcessor() {
		return new RefererAnnotationBeanPostProcessor();
	}

//	@PostConstruct
//	public void post() throws InterruptedException {
//		Order order = new Order(1,2,10,20);
//
//		System.out.println("start");
//		FreezeAccount dollarFreezeAccount = dollarAccountService.freezePrice(order.getDollarId(), order.getDollarPrice());
//		FreezeAccount rmbFreezeAccount = rmbAccountService.freezePrice(order.getRmbId(), order.getRmbPrice());
//		System.out.println("dollarFreezeAccount : " + dollarFreezeAccount);
//		System.out.println("rmbFreezeAccount : " + rmbFreezeAccount);
//
//		Thread.sleep(20000);
//		Integer dollarAns = dollarAccountService.addDollarPrice(dollarFreezeAccount, order.getRmbId());
//		Integer rmbAns = rmbAccountService.addRmbPrice(rmbFreezeAccount, order.getDollarId());
//		System.out.println("dollarAns:"+dollarAns);
//		System.out.println("rmbAns:"+rmbAns);
//	}

//	@Bean
//	public ApplicationRunner runner() {
//
//		return args -> {
//			Order order = new Order(1,2,10,20);
//
//
//			System.out.println("start");
//        	FreezeAccount dollarFreezeAccount = dollarAccountService.freezePrice(order.getDollarId(), order.getDollarPrice());
//        	FreezeAccount rmbFreezeAccount = rmbAccountService.freezePrice(order.getRmbId(), order.getRmbPrice());
//        	System.out.println("dollarFreezeAccount : " + dollarFreezeAccount);
//        	System.out.println("rmbFreezeAccount : " + rmbFreezeAccount);
//
//        	Thread.sleep(20000);
//        	Integer dollarAns = dollarAccountService.addDollarPrice(dollarFreezeAccount, order.getRmbId());
//        	Integer rmbAns = rmbAccountService.addRmbPrice(rmbFreezeAccount, order.getDollarId());
//			System.out.println("dollarAns:"+dollarAns);
//			System.out.println("rmbAns:"+rmbAns);
//		};
//	}

}
