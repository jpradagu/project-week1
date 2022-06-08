package com.nttdata.bootcamp.productdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductdomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductdomainApplication.class, args);
	}

}
