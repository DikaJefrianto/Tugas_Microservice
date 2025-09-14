package com.dika.pengembalian_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PengembalianServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PengembalianServiceApplication.class, args);
	}

}
