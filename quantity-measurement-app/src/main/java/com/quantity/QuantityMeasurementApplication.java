package com.quantity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class QuantityMeasurementApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuantityMeasurementApplication.class, args);
		System.out.println("Quantity Measurement App is running...");
	}
}