package com.nttdata.bootcamp.customerdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CustomerdomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerdomainApplication.class, args);
	}

}
